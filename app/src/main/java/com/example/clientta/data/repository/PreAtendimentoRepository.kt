package com.example.clientta.data.repository

import com.example.clientta.data.dao.PreAtendimentoDao
import com.example.clientta.data.entity.PreAtendimento
import kotlinx.coroutines.flow.Flow

class PreAtendimentoRepository(private val preAtendimentoDao: PreAtendimentoDao) {

    fun getByAgendamentoId(agendamentoId: Long): Flow<PreAtendimento?> =
        preAtendimentoDao.getByAgendamentoId(agendamentoId)

    fun getById(id: Long): Flow<PreAtendimento?> = preAtendimentoDao.getById(id)

    suspend fun insert(preAtendimento: PreAtendimento): Result<Long> {
        return try {
            if (!preAtendimento.termoAceito) {
                Result.failure(IllegalArgumentException("Confirme que você leu o termo para continuar."))
            } else {
                val id = preAtendimentoDao.insert(preAtendimento)
                Result.success(id)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun update(preAtendimento: PreAtendimento): Result<Unit> {
        return try {
            preAtendimentoDao.update(preAtendimento)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
