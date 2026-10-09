package com.example.clientta.data.repository

import com.example.clientta.data.dao.ClienteDao
import com.example.clientta.data.entity.Cliente
import kotlinx.coroutines.flow.Flow

class ClienteRepository(private val clienteDao: ClienteDao) {

    val allClientes: Flow<List<Cliente>> = clienteDao.getAll()

    fun getById(id: Long): Flow<Cliente?> = clienteDao.getById(id)

    suspend fun getByEmail(email: String): Cliente? = clienteDao.getByEmail(email)

    suspend fun getByCpf(cpf: String): Cliente? = clienteDao.getByCpf(cpf)

    suspend fun login(email: String, senha: String): Cliente? = clienteDao.login(email, senha)

    suspend fun insert(cliente: Cliente): Result<Long> {
        return try {
            if (clienteDao.getByCpf(cliente.cpf) != null) {
                Result.failure(IllegalArgumentException("Este CPF já está cadastrado."))
            } else if (clienteDao.getByEmail(cliente.email) != null) {
                Result.failure(IllegalArgumentException("Este e-mail já está cadastrado."))
            } else {
                val id = clienteDao.insert(cliente)
                Result.success(id)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun update(cliente: Cliente): Result<Unit> {
        return try {
            clienteDao.update(cliente)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun delete(cliente: Cliente): Result<Unit> {
        return try {
            clienteDao.delete(cliente)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
