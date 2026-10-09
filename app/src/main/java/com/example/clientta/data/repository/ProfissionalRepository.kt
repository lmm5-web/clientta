package com.example.clientta.data.repository

import com.example.clientta.data.dao.ProfissionalDao
import com.example.clientta.data.entity.Profissional
import kotlinx.coroutines.flow.Flow

class ProfissionalRepository(private val profissionalDao: ProfissionalDao) {

    val allProfissionais: Flow<List<Profissional>> = profissionalDao.getAll()

    fun getById(id: Long): Flow<Profissional?> = profissionalDao.getById(id)

    suspend fun getByEmail(email: String): Profissional? = profissionalDao.getByEmail(email)

    suspend fun login(email: String, senha: String): Profissional? = profissionalDao.login(email, senha)

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
