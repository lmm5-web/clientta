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
    var cepState = MutableStateFlow("")
    var logradouroState = MutableStateFlow("")
    var bairroState = MutableStateFlow("")
    var cidadeState = MutableStateFlow("")
    var estadoState = MutableStateFlow("")
    var enderecoState = MutableStateFlow("")

    private val _isBuscandoCep = MutableStateFlow(false)
    val isBuscandoCep: StateFlow<Boolean> = _isBuscandoCep.asStateFlow()

    private val _successMessage = MutableStateFlow<String?>(null)
    val successMessage: StateFlow<String?> = _successMessage.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    init {
        currentCliente.value?.let { cliente ->
            telefoneState.value = cliente.telefone
            emailState.value = cliente.email
            cepState.value = cliente.cep ?: ""
            logradouroState.value = cliente.logradouro ?: ""
            bairroState.value = cliente.bairro ?: ""
            cidadeState.value = cliente.cidade ?: ""
            estadoState.value = cliente.estado ?: ""
            enderecoState.value = cliente.endereco ?: cliente.enderecoCompleto
        }
    }

    fun buscarCep() {
        val cep = cepState.value.trim()
        val cepNumerico = cep.replace("\\D".toRegex(), "")
        if (cepNumerico.length != 8) {
            _errorMessage.value = "Informe um CEP válido com 8 dígitos."
            return
        }

        viewModelScope.launch {
            _isBuscandoCep.value = true
            _errorMessage.value = null
            val result = clienteRepository.buscarEnderecoPorCep(cep)
            result.onSuccess { viaCep ->
                logradouroState.value = viaCep.logradouro ?: ""
                bairroState.value = viaCep.bairro ?: ""
                cidadeState.value = viaCep.localidade ?: ""
                estadoState.value = viaCep.uf ?: ""
                enderecoState.value = viaCep.toEnderecoCompleto()
            }.onFailure { exception ->
                _errorMessage.value = exception.message ?: "Erro ao buscar CEP."
            }
            _isBuscandoCep.value = false
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
            cep = cepState.value.ifBlank { null },
            logradouro = logradouroState.value.ifBlank { null },
            bairro = bairroState.value.ifBlank { null },
            cidade = cidadeState.value.ifBlank { null },
            estado = estadoState.value.ifBlank { null },
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
