package com.example.clientta.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clientta.data.repository.ClienteRepository
import com.example.clientta.data.repository.ProfissionalRepository
import com.example.clientta.data.session.UserSession
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val clienteRepository: ClienteRepository,
    private val profissionalRepository: ProfissionalRepository
) : ViewModel() {

    var emailState = MutableStateFlow("")
    var senhaState = MutableStateFlow("")

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    private val _loginSuccessCliente = MutableStateFlow(false)
    val loginSuccessCliente: StateFlow<Boolean> = _loginSuccessCliente.asStateFlow()

    private val _loginSuccessProfissional = MutableStateFlow(false)
    val loginSuccessProfissional: StateFlow<Boolean> = _loginSuccessProfissional.asStateFlow()

    fun login() {
        val email = emailState.value.trim()
        val senha = senhaState.value.trim()

        if (email.isEmpty() || senha.isEmpty()) {
            _errorMessage.value = "Preencha todos os campos obrigatórios."
            return
        }

        viewModelScope.launch {
            // Tenta login como cliente
            val cliente = clienteRepository.login(email, senha)
            if (cliente != null) {
                UserSession.loginCliente(cliente)
                _errorMessage.value = null
                _loginSuccessCliente.value = true
                return@launch
            }

            // Tenta login como profissional
            val profissional = profissionalRepository.login(email, senha)
            if (profissional != null) {
                UserSession.loginProfissional(profissional)
                _errorMessage.value = null
                _loginSuccessProfissional.value = true
                return@launch
            }

            _errorMessage.value = "E-mail ou senha incorretos."
        }
    }

    fun clearError() {
        _errorMessage.value = null
    }

    fun resetSuccess() {
        _loginSuccessCliente.value = false
        _loginSuccessProfissional.value = false
    }
}
