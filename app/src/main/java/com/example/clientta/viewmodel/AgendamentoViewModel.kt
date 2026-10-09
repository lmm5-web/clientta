package com.example.clientta.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clientta.data.entity.Agendamento
import com.example.clientta.data.repository.AgendamentoRepository
import com.example.clientta.data.session.UserSession
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AgendamentoViewModel(
    private val agendamentoRepository: AgendamentoRepository
) : ViewModel() {

    var dataSelecionada = MutableStateFlow("")
    var horarioSelecionado = MutableStateFlow("")

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    private val _agendamentoSuccess = MutableStateFlow(false)
    val agendamentoSuccess: StateFlow<Boolean> = _agendamentoSuccess.asStateFlow()

    val meusAgendamentos: StateFlow<List<Agendamento>> =
        UserSession.currentCliente.flatMapLatest { cliente ->
            if (cliente != null) {
                agendamentoRepository.getByClienteId(cliente.id)
            } else {
                flowOf(emptyList())
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun agendar(tratamentoId: Long) {
        val cliente = UserSession.currentCliente.value
        if (cliente == null) {
            _errorMessage.value = "Usuário não autenticado."
            return
        }

        val data = dataSelecionada.value.trim()
        val horario = horarioSelecionado.value.trim()

        if (data.isEmpty() || horario.isEmpty()) {
            _errorMessage.value = "Selecione a data e o horário para o agendamento."
            return
        }

        val novoAgendamento = Agendamento(
            clienteId = cliente.id,
            tratamentoId = tratamentoId,
            data = data,
            horario = horario,
            status = Agendamento.STATUS_AGENDADO
        )

        viewModelScope.launch {
            val result = agendamentoRepository.insert(novoAgendamento)
            result.onSuccess {
                _errorMessage.value = null
                _agendamentoSuccess.value = true
            }.onFailure { ex ->
                _errorMessage.value = ex.message ?: "Não foi possível realizar o agendamento."
            }
        }
    }

    fun cancelar(agendamento: Agendamento) {
        viewModelScope.launch {
            agendamentoRepository.update(agendamento.copy(status = Agendamento.STATUS_CANCELADO))
        }
    }

    fun getAgendamentoById(id: Long) = agendamentoRepository.getById(id)

    fun clearError() {
        _errorMessage.value = null
    }

    fun resetSuccess() {
        _agendamentoSuccess.value = false
    }
}
