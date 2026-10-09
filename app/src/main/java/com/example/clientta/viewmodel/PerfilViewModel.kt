package com.example.clientta.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clientta.data.repository.ClienteRepository
import com.example.clientta.data.session.UserSession
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PerfilViewModel(
    private val clienteRepository: ClienteRepository
) : ViewModel() {

    val currentCliente = UserSession.currentCliente

    var telefoneState = MutableStateFlow("")
    var emailState = MutableStateFlow("")
    var enderecoState = MutableStateFlow("")

    private val _successMessage = MutableStateFlow<String?>(null)
    val successMessage: StateFlow<String?> = _successMessage.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    init {
        currentCliente.value?.let { cliente ->
            telefoneState.value = cliente.telefone
            emailState.value = cliente.email
            enderecoState.value = cliente.endereco ?: ""
        }
    }

    fun salvarPerfil() {
        val cliente = currentCliente.value ?: return
        val novoTelefone = telefoneState.value.trim()
        val novoEmail = emailState.value.trim()
        val novoEndereco = enderecoState.value.trim()

        if (novoTelefone.isEmpty() || novoEmail.isEmpty()) {
            _errorMessage.value = "Preencha telefone e e-mail."
            return
        }

        val clienteAtualizado = cliente.copy(
            telefone = novoTelefone,
            email = novoEmail,
            endereco = novoEndereco.ifBlank { null }
        )

        viewModelScope.launch {
            val result = clienteRepository.update(clienteAtualizado)
            result.onSuccess {
                UserSession.updateCliente(clienteAtualizado)
                _errorMessage.value = null
                _successMessage.value = "Perfil atualizado com sucesso!"
            }.onFailure {
                _errorMessage.value = "Não foi possível atualizar o perfil."
            }
        }
    }

    fun maskCpf(cpf: String): String {
        return if (cpf.length >= 11) {
            "${cpf.substring(0, 3)}.***.***-${cpf.takeLast(2)}"
        } else {
            cpf
        }
    }

    fun clearMessages() {
        _errorMessage.value = null
        _successMessage.value = null
    }
}
