package com.example.clientta.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clientta.data.entity.Cliente
import com.example.clientta.data.repository.ClienteRepository
import com.example.clientta.data.session.UserSession
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CadastroViewModel(
    private val clienteRepository: ClienteRepository
) : ViewModel() {

    var nomeState = MutableStateFlow("")
    var cpfState = MutableStateFlow("")
    var dataNascimentoState = MutableStateFlow("")
    var telefoneState = MutableStateFlow("")
    var emailState = MutableStateFlow("")
    var senhaState = MutableStateFlow("")
    var enderecoState = MutableStateFlow("")
    var observacoesState = MutableStateFlow("")

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    private val _cadastroSuccess = MutableStateFlow(false)
    val cadastroSuccess: StateFlow<Boolean> = _cadastroSuccess.asStateFlow()

    fun cadastrar() {
        val nome = nomeState.value.trim()
        val cpf = cpfState.value.trim()
        val dataNasc = dataNascimentoState.value.trim()
        val telefone = telefoneState.value.trim()
        val email = emailState.value.trim()
        val senha = senhaState.value.trim()

        if (nome.isEmpty() || cpf.isEmpty() || dataNasc.isEmpty() ||
            telefone.isEmpty() || email.isEmpty() || senha.isEmpty()
        ) {
            _errorMessage.value = "Preencha todos os campos obrigatórios."
            return
        }

        if (!email.contains("@") || !email.contains(".")) {
            _errorMessage.value = "Digite um e-mail válido."
            return
        }

        val novoCliente = Cliente(
            nome = nome,
            cpf = cpf,
            dataNascimento = dataNasc,
            telefone = telefone,
            email = email,
            senha = senha,
            endereco = enderecoState.value.ifBlank { null },
            observacoes = observacoesState.value.ifBlank { null }
        )

        viewModelScope.launch {
            val result = clienteRepository.insert(novoCliente)
            result.onSuccess { id ->
                val clienteSalvo = novoCliente.copy(id = id)
                UserSession.loginCliente(clienteSalvo)
                _errorMessage.value = null
                _cadastroSuccess.value = true
            }.onFailure { exception ->
                _errorMessage.value = exception.message ?: "Não foi possível salvar o cadastro. Tente novamente."
            }
        }
    }

    fun clearError() {
        _errorMessage.value = null
    }

    fun resetSuccess() {
        _cadastroSuccess.value = false
    }
}
