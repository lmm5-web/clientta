package com.example.clientta.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clientta.data.entity.PreAtendimento
import com.example.clientta.data.repository.PreAtendimentoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PreAtendimentoViewModel(
    private val preAtendimentoRepository: PreAtendimentoRepository
) : ViewModel() {

    var alergiaState = MutableStateFlow("")
    var doencaPeleState = MutableStateFlow("")
    var medicamentoState = MutableStateFlow("")
    var observacoesState = MutableStateFlow("")
    var termoAceitoState = MutableStateFlow(false)

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    private val _salvarSuccess = MutableStateFlow(false)
    val salvarSuccess: StateFlow<Boolean> = _salvarSuccess.asStateFlow()

    fun getByAgendamentoId(agendamentoId: Long) = preAtendimentoRepository.getByAgendamentoId(agendamentoId)

    fun salvarPreAtendimento(agendamentoId: Long) {
        if (!termoAceitoState.value) {
            _errorMessage.value = "Confirme que você leu o termo para continuar."
            return
        }

        val respostasFormatadas = buildString {
            append("Alergias: ").append(alergiaState.value.ifBlank { "Nenhuma" }).append("\n")
            append("Doenças de pele: ").append(doencaPeleState.value.ifBlank { "Nenhuma" }).append("\n")
            append("Medicamentos em uso: ").append(medicamentoState.value.ifBlank { "Nenhum" }).append("\n")
            append("Observações: ").append(observacoesState.value.ifBlank { "Nenhuma" })
        }

        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        val dataPreenchimento = sdf.format(Date())

        val preAtendimento = PreAtendimento(
            agendamentoId = agendamentoId,
            respostas = respostasFormatadas,
            termoAceito = true,
            dataPreenchimento = dataPreenchimento
        )

        viewModelScope.launch {
            val result = preAtendimentoRepository.insert(preAtendimento)
            result.onSuccess {
                _errorMessage.value = null
                _salvarSuccess.value = true
            }.onFailure { ex ->
                _errorMessage.value = ex.message ?: "Não foi possível salvar o pré-atendimento."
            }
        }
    }

    fun clearError() {
        _errorMessage.value = null
    }

    fun resetSuccess() {
        _salvarSuccess.value = false
    }
}
