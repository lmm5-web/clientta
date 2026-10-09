package com.example.clientta.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.clientta.data.entity.Cliente
import kotlinx.coroutines.flow.Flow

@Dao
interface ClienteDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(cliente: Cliente): Long

    @Update
    suspend fun update(cliente: Cliente)

    @Delete
    suspend fun delete(cliente: Cliente)

    @Query("SELECT * FROM clientes ORDER BY nome ASC")
    fun getAll(): Flow<List<Cliente>>

    @Query("SELECT * FROM clientes WHERE id = :id LIMIT 1")
    fun getById(id: Long): Flow<Cliente?>

    @Query("SELECT * FROM clientes WHERE email = :email LIMIT 1")
    suspend fun getByEmail(email: String): Cliente?

    @Query("SELECT * FROM clientes WHERE cpf = :cpf LIMIT 1")
    suspend fun getByCpf(cpf: String): Cliente?

    @Query("SELECT * FROM clientes WHERE email = :email AND senha = :senha LIMIT 1")
    suspend fun login(email: String, senha: String): Cliente?
}
