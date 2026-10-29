package com.example.clientta.data.repository

import com.example.clientta.data.dao.AgendamentoDao
import com.example.clientta.data.entity.Agendamento
import com.example.clientta.data.remote.RetrofitClient
import com.example.clientta.data.remote.api.AgendamentoApiService
import com.example.clientta.data.remote.dto.AgendamentoDto
import kotlinx.coroutines.flow.Flow

class AgendamentoRepository(
    private val agendamentoDao: AgendamentoDao,
    private val apiService: AgendamentoApiService = RetrofitClient.agendamentoApiService
) {

    val allAgendamentos: Flow<List<Agendamento>> = agendamentoDao.getAll()

    fun getByClienteId(clienteId: Long): Flow<List<Agendamento>> = agendamentoDao.getByClienteId(clienteId)

    fun getById(id: Long): Flow<Agendamento?> = agendamentoDao.getById(id)

    fun getByStatus(status: String): Flow<List<Agendamento>> = agendamentoDao.getByStatus(status)

    fun getByData(data: String): Flow<List<Agendamento>> = agendamentoDao.getByData(data)

    suspend fun atualizarStatus(id: Long, novoStatus: String): Result<Unit> {
        return try {
            agendamentoDao.atualizarStatus(id, novoStatus)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun enviarAgendamentoParaServidor(agendamento: Agendamento): Result<Unit> {
        return try {
            val dto = AgendamentoDto(
                id = if (agendamento.id > 0) agendamento.id else null,
                clienteId = agendamento.clienteId,
                tratamentoId = agendamento.tratamentoId,
                data = agendamento.data,
                horario = agendamento.horario,
                status = agendamento.status,
                observacoes = agendamento.observacoes
            )
            val response = apiService.criarAgendamento(dto)
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Erro ao enviar agendamento ao servidor: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

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
