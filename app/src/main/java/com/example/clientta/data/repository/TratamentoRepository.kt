package com.example.clientta.data.repository

import com.example.clientta.data.dao.TratamentoDao
import com.example.clientta.data.entity.Tratamento
import kotlinx.coroutines.flow.Flow

class TratamentoRepository(private val tratamentoDao: TratamentoDao) {

    val allTratamentos: Flow<List<Tratamento>> = tratamentoDao.getAll()
    val tratamentosAtivos: Flow<List<Tratamento>> = tratamentoDao.getAtivos()

    fun getById(id: Long): Flow<Tratamento?> = tratamentoDao.getById(id)

    suspend fun insert(tratamento: Tratamento): Result<Long> {
        return try {
            val id = tratamentoDao.insert(tratamento)
            Result.success(id)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun update(tratamento: Tratamento): Result<Unit> {
        return try {
            tratamentoDao.update(tratamento)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun delete(tratamento: Tratamento): Result<Unit> {
        return try {
            tratamentoDao.delete(tratamento)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
