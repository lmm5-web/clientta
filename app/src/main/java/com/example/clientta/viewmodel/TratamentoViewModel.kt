// Fronteira: Gerencia o UiState e executa a lógica de carregamento/sincronização de tratamentos no ViewModel.
// Motivo: Centraliza a tomada de decisão de estado (Carregando, Vazio, Erro, Sucesso) usando try/catch e Coroutines.
// Se sumir: A tela principal de tratamentos perde a capacidade de reagir a erros de rede ou dados vazios.

package com.example.clientta.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clientta.data.entity.Tratamento
import com.example.clientta.data.repository.TratamentoRepository
import com.example.clientta.ui.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.io.IOException

class TratamentoViewModel(
    private val tratamentoRepository: TratamentoRepository
) : ViewModel() {

    private val _estado = MutableStateFlow<UiState>(UiState.Carregando)
    val estado: StateFlow<UiState> = _estado.asStateFlow()

    private val _syncMessage = MutableStateFlow<String?>(null)
    val syncMessage: StateFlow<String?> = _syncMessage.asStateFlow()

    init {
        carregar()
    }

    fun carregar() {
        viewModelScope.launch {
            _estado.value = UiState.Carregando
            try {
                // Tenta sincronizar com o servidor remoto (pode falhar se offline)
                val syncResult = tratamentoRepository.sincronizarTratamentosRemotos()

                // Obtém a lista atual do banco local (Room)
                val lista = tratamentoRepository.tratamentosAtivos.first()

                if (syncResult.isFailure && lista.isEmpty()) {
                    val ex = syncResult.exceptionOrNull()
                    if (ex is IOException || ex?.message?.contains("Unable to resolve host") == true) {
                        _estado.value = UiState.Erro("Sem conexão. Verifique a internet e tente novamente.")
                    } else {
                        _estado.value = UiState.Erro("Não foi possível carregar os dados do servidor.")
                    }
                } else {
                    _estado.value = if (lista.isEmpty()) {
                        UiState.Vazio
                    } else {
                        UiState.Sucesso(lista)
                    }
                }
            } catch (e: IOException) {
                _estado.value = UiState.Erro("Sem conexão. Verifique a internet e tente novamente.")
            } catch (e: Exception) {
                _estado.value = UiState.Erro("Não foi possível carregar os dados.")
            }
        }
    }

    fun getTratamentoById(id: Long) = tratamentoRepository.getById(id)

    fun clearSyncMessage() {
        _syncMessage.value = null
    }
}
