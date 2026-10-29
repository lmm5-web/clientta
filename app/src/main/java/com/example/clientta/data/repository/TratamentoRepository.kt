package com.example.clientta.data.repository

import com.example.clientta.data.dao.TratamentoDao
import com.example.clientta.data.entity.Tratamento
import com.example.clientta.data.remote.RetrofitClient
import com.example.clientta.data.remote.api.TratamentoApiService
import kotlinx.coroutines.flow.Flow

class TratamentoRepository(
    private val tratamentoDao: TratamentoDao,
    private val apiService: TratamentoApiService = RetrofitClient.tratamentoApiService
) {

    val allTratamentos: Flow<List<Tratamento>> = tratamentoDao.getAll()
    val tratamentosAtivos: Flow<List<Tratamento>> = tratamentoDao.getAtivos()

    fun getById(id: Long): Flow<Tratamento?> = tratamentoDao.getById(id)

    fun getTratamentosPorPrecoMaximo(precoMaximo: Double): Flow<List<Tratamento>> =
        tratamentoDao.getTratamentosPorPrecoMaximo(precoMaximo)

    fun buscarTratamentos(query: String): Flow<List<Tratamento>> =
        tratamentoDao.buscarTratamentos(query)

    /**
     * Sincroniza os tratamentos com o serviço remoto Retrofit e salva no banco Room local.
     */
    suspend fun sincronizarTratamentosRemotos(): Result<Int> {
        return try {
            val response = apiService.getTratamentosRemotos()
            if (response.isSuccessful) {
                val dtos = response.body() ?: emptyList()
                val entidades = dtos.map { it.toEntity() }
                if (entidades.isNotEmpty()) {
                    tratamentoDao.insertAll(entidades)
                }
                Result.success(entidades.size)
            } else {
                Result.failure(Exception("Erro na resposta do servidor de tratamentos: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

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
