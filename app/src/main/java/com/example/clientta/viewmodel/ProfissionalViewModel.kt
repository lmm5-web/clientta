package com.example.clientta.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clientta.data.entity.Agendamento
import com.example.clientta.data.repository.AgendamentoRepository
import com.example.clientta.data.repository.ClienteRepository
import com.example.clientta.data.repository.PreAtendimentoRepository
import com.example.clientta.data.repository.TratamentoRepository
import com.example.clientta.data.repository.ProfissionalRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProfissionalViewModel(
    private val agendamentoRepository: AgendamentoRepository,
    private val clienteRepository: ClienteRepository,
    private val tratamentoRepository: TratamentoRepository,
    private val preAtendimentoRepository: PreAtendimentoRepository,
    private val profissionalRepository: ProfissionalRepository? = null
) : ViewModel() {

    private val _isCarregando = MutableStateFlow(false)
    val isCarregando: StateFlow<Boolean> = _isCarregando.asStateFlow()

    fun sincronizarEquipe() {
        val repo = profissionalRepository ?: return
        viewModelScope.launch {
            _isCarregando.value = true
            repo.sincronizarProfissionaisRemotos()
            _isCarregando.value = false
        }
    }

    val todosAgendamentos = agendamentoRepository.allAgendamentos
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val todosClientes = clienteRepository.allClientes
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val todosTratamentos = tratamentoRepository.allTratamentos
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun getClienteById(id: Long) = clienteRepository.getById(id)

    fun getTratamentoById(id: Long) = tratamentoRepository.getById(id)

    fun getPreAtendimentoByAgendamentoId(agendamentoId: Long) =
        preAtendimentoRepository.getByAgendamentoId(agendamentoId)

    fun concluirAgendamento(agendamento: Agendamento) {
        viewModelScope.launch {
            agendamentoRepository.update(agendamento.copy(status = Agendamento.STATUS_CONCLUIDO))
        }
    }

    fun cancelarAgendamento(agendamento: Agendamento) {
        viewModelScope.launch {
            agendamentoRepository.update(agendamento.copy(status = Agendamento.STATUS_CANCELADO))
        }
    }
}
