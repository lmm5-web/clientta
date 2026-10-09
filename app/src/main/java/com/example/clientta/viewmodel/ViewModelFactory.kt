package com.example.clientta.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.clientta.data.database.ClienttaDatabase
import com.example.clientta.data.repository.AgendamentoRepository
import com.example.clientta.data.repository.ClienteRepository
import com.example.clientta.data.repository.PreAtendimentoRepository
import com.example.clientta.data.repository.ProfissionalRepository
import com.example.clientta.data.repository.TratamentoRepository

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    private val db by lazy { ClienttaDatabase.getDatabase(context) }
    private val clienteRepository by lazy { ClienteRepository(db.clienteDao()) }
    private val profissionalRepository by lazy { ProfissionalRepository(db.profissionalDao()) }
    private val tratamentoRepository by lazy { TratamentoRepository(db.tratamentoDao()) }
    private val agendamentoRepository by lazy { AgendamentoRepository(db.agendamentoDao()) }
    private val preAtendimentoRepository by lazy { PreAtendimentoRepository(db.preAtendimentoDao()) }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(clienteRepository, profissionalRepository) as T
            }
            modelClass.isAssignableFrom(CadastroViewModel::class.java) -> {
                CadastroViewModel(clienteRepository) as T
            }
            modelClass.isAssignableFrom(TratamentoViewModel::class.java) -> {
                TratamentoViewModel(tratamentoRepository) as T
            }
            modelClass.isAssignableFrom(AgendamentoViewModel::class.java) -> {
                AgendamentoViewModel(agendamentoRepository) as T
            }
            modelClass.isAssignableFrom(PreAtendimentoViewModel::class.java) -> {
                PreAtendimentoViewModel(preAtendimentoRepository) as T
            }
            modelClass.isAssignableFrom(PerfilViewModel::class.java) -> {
                PerfilViewModel(clienteRepository) as T
            }
            modelClass.isAssignableFrom(ProfissionalViewModel::class.java) -> {
                ProfissionalViewModel(agendamentoRepository, clienteRepository, tratamentoRepository, preAtendimentoRepository) as T
            }
            else -> throw IllegalArgumentException("ViewModel desconhecido: ${modelClass.name}")
        }
    }
}
