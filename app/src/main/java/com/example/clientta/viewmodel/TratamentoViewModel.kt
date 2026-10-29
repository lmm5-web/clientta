package com.example.clientta.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clientta.data.entity.Tratamento
import com.example.clientta.data.repository.TratamentoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TratamentoViewModel(
    private val tratamentoRepository: TratamentoRepository
) : ViewModel() {

    private val _isCarregando = MutableStateFlow(false)
    val isCarregando: StateFlow<Boolean> = _isCarregando.asStateFlow()

    private val _syncMessage = MutableStateFlow<String?>(null)
    val syncMessage: StateFlow<String?> = _syncMessage.asStateFlow()

    val tratamentosAtivos: StateFlow<List<Tratamento>> =
        tratamentoRepository.tratamentosAtivos
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )

    fun getTratamentoById(id: Long) = tratamentoRepository.getById(id)

    fun sincronizarComServidor() {
        viewModelScope.launch {
            _isCarregando.value = true
            val result = tratamentoRepository.sincronizarTratamentosRemotos()
            result.onSuccess { qtd ->
                _syncMessage.value = "Sincronizados $qtd tratamentos."
            }.onFailure {
                _syncMessage.value = "Falha ao sincronizar tratamentos."
            }
            _isCarregando.value = false
        }
    }

    fun clearSyncMessage() {
        _syncMessage.value = null
    }
}
