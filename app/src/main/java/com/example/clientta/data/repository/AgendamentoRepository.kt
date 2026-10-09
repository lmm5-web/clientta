package com.example.clientta.data.repository

import com.example.clientta.data.dao.AgendamentoDao
import com.example.clientta.data.entity.Agendamento
import kotlinx.coroutines.flow.Flow

class AgendamentoRepository(private val agendamentoDao: AgendamentoDao) {

    val allAgendamentos: Flow<List<Agendamento>> = agendamentoDao.getAll()

    fun getByClienteId(clienteId: Long): Flow<List<Agendamento>> = agendamentoDao.getByClienteId(clienteId)

    fun getById(id: Long): Flow<Agendamento?> = agendamentoDao.getById(id)

    suspend fun insert(agendamento: Agendamento): Result<Long> {
        return try {
            val existente = agendamentoDao.getByDataEHorario(agendamento.data, agendamento.horario)
            if (existente != null) {
                Result.failure(IllegalArgumentException("Esse horário não está disponível."))
            } else {
                val id = agendamentoDao.insert(agendamento)
                Result.success(id)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun update(agendamento: Agendamento): Result<Unit> {
        return try {
            agendamentoDao.update(agendamento)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun delete(agendamento: Agendamento): Result<Unit> {
        return try {
            agendamentoDao.delete(agendamento)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
