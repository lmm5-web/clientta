package com.example.clientta.data.repository

import com.example.clientta.data.dao.ClienteDao
import com.example.clientta.data.entity.Cliente
import com.example.clientta.data.remote.RetrofitClient
import com.example.clientta.data.remote.api.ViaCepApi
import com.example.clientta.data.remote.dto.ViaCepDto
import kotlinx.coroutines.flow.Flow

class ClienteRepository(
    private val clienteDao: ClienteDao,
    private val viaCepApi: ViaCepApi = RetrofitClient.viaCepApi
) {

    val allClientes: Flow<List<Cliente>> = clienteDao.getAll()

    fun getById(id: Long): Flow<Cliente?> = clienteDao.getById(id)

    suspend fun getByEmail(email: String): Cliente? = clienteDao.getByEmail(email)

    suspend fun getByCpf(cpf: String): Cliente? = clienteDao.getByCpf(cpf)

    suspend fun login(email: String, senha: String): Cliente? = clienteDao.login(email, senha)

    fun getByCep(cep: String): Flow<List<Cliente>> = clienteDao.getByCep(cep)

    fun buscarClientes(query: String): Flow<List<Cliente>> = clienteDao.buscarClientes(query)

    /**
     * Consulta um CEP na API remota ViaCEP.
     * Retorna [Result.success] com o [ViaCepDto] se o CEP for válido e encontrado,
     * ou [Result.failure] caso contrário.
     */
    suspend fun buscarEnderecoPorCep(cep: String): Result<ViaCepDto> {
        return try {
            val cepLimpo = cep.replace("\\D".toRegex(), "")
            if (cepLimpo.length != 8) {
                return Result.failure(IllegalArgumentException("CEP deve conter exatamente 8 dígitos."))
            }

            val response = viaCepApi.buscarEnderecoPorCep(cepLimpo)
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null && body.erro != true) {
                    Result.success(body)
                } else {
                    Result.failure(IllegalArgumentException("CEP não encontrado."))
                }
            } else {
                Result.failure(Exception("Erro na resposta do servidor ViaCEP: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

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
