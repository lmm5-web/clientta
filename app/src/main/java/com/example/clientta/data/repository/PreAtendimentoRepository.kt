package com.example.clientta.data.repository

import com.example.clientta.data.dao.PreAtendimentoDao
import com.example.clientta.data.entity.PreAtendimento
import com.example.clientta.data.remote.RetrofitClient
import com.example.clientta.data.remote.api.PreAtendimentoApiService
import com.example.clientta.data.remote.dto.PreAtendimentoDto
import kotlinx.coroutines.flow.Flow

class PreAtendimentoRepository(
    private val preAtendimentoDao: PreAtendimentoDao,
    private val apiService: PreAtendimentoApiService = RetrofitClient.preAtendimentoApiService
) {

    fun getByAgendamentoId(agendamentoId: Long): Flow<PreAtendimento?> =
        preAtendimentoDao.getByAgendamentoId(agendamentoId)

    fun getById(id: Long): Flow<PreAtendimento?> = preAtendimentoDao.getById(id)

    suspend fun hasPreAtendimentoConcluido(agendamentoId: Long): Boolean =
        preAtendimentoDao.hasPreAtendimentoConcluido(agendamentoId)

    suspend fun enviarPreAtendimentoRemoto(preAtendimento: PreAtendimento): Result<Unit> {
        return try {
            val dto = PreAtendimentoDto(
                id = if (preAtendimento.id > 0) preAtendimento.id else null,
                agendamentoId = preAtendimento.agendamentoId,
                respostas = preAtendimento.respostas,
                termoAceito = preAtendimento.termoAceito,
                dataPreenchimento = preAtendimento.dataPreenchimento,
                alergiasOuCuidados = preAtendimento.alergiasOuCuidados
            )
            val response = apiService.enviarPreAtendimento(dto)
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Erro ao sincronizar anamnese com o servidor: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

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
