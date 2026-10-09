package com.example.clientta.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clientta.data.entity.Tratamento
import com.example.clientta.data.repository.TratamentoRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class TratamentoViewModel(
    private val tratamentoRepository: TratamentoRepository
) : ViewModel() {

    val tratamentosAtivos: StateFlow<List<Tratamento>> =
        tratamentoRepository.tratamentosAtivos
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )

    fun getTratamentoById(id: Long) = tratamentoRepository.getById(id)
}
