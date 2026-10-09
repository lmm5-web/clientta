package com.example.clientta.data.session

import com.example.clientta.data.entity.Cliente
import com.example.clientta.data.entity.Profissional
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object UserSession {
    private val _currentCliente = MutableStateFlow<Cliente?>(null)
    val currentCliente: StateFlow<Cliente?> = _currentCliente.asStateFlow()

    private val _currentProfissional = MutableStateFlow<Profissional?>(null)
    val currentProfissional: StateFlow<Profissional?> = _currentProfissional.asStateFlow()

    fun loginCliente(cliente: Cliente) {
        _currentCliente.value = cliente
        _currentProfissional.value = null
    }

    fun loginProfissional(profissional: Profissional) {
        _currentProfissional.value = profissional
        _currentCliente.value = null
    }

    fun updateCliente(cliente: Cliente) {
        _currentCliente.value = cliente
    }

    fun logout() {
        _currentCliente.value = null
        _currentProfissional.value = null
    }
}
