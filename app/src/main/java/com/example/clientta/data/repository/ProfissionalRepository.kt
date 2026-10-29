package com.example.clientta.data.repository

import com.example.clientta.data.dao.ProfissionalDao
import com.example.clientta.data.entity.Profissional
import com.example.clientta.data.remote.RetrofitClient
import com.example.clientta.data.remote.api.ProfissionalApiService
import kotlinx.coroutines.flow.Flow

class ProfissionalRepository(
    private val profissionalDao: ProfissionalDao,
    private val apiService: ProfissionalApiService = RetrofitClient.profissionalApiService
) {

    val allProfissionais: Flow<List<Profissional>> = profissionalDao.getAll()
    val profissionaisAtivos: Flow<List<Profissional>> = profissionalDao.getAtivos()

    fun getById(id: Long): Flow<Profissional?> = profissionalDao.getById(id)

    suspend fun getByEmail(email: String): Profissional? = profissionalDao.getByEmail(email)

    fun getByEspecialidade(especialidade: String): Flow<List<Profissional>> =
        profissionalDao.getByEspecialidade(especialidade)

    fun buscarProfissionais(query: String): Flow<List<Profissional>> =
        profissionalDao.buscarProfissionais(query)

    suspend fun login(email: String, senha: String): Profissional? = profissionalDao.login(email, senha)

    /**
     * Sincroniza a equipe de profissionais com o serviço remoto Retrofit e salva no banco Room local.
     */
    suspend fun sincronizarProfissionaisRemotos(): Result<Int> {
        return try {
            val response = apiService.getProfissionaisRemotos()
            if (response.isSuccessful) {
                val dtos = response.body() ?: emptyList()
                var count = 0
                dtos.forEach { dto ->
                    profissionalDao.insert(dto.toEntity())
                    count++
                }
                Result.success(count)
            } else {
                Result.failure(Exception("Erro na resposta do servidor de profissionais: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun insert(profissional: Profissional): Result<Long> {
        return try {
            val id = profissionalDao.insert(profissional)
            Result.success(id)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun update(profissional: Profissional): Result<Unit> {
        return try {
            profissionalDao.update(profissional)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
