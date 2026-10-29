package com.example.clientta.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.clientta.data.entity.Profissional
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfissionalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(profissional: Profissional): Long

    @Update
    suspend fun update(profissional: Profissional)

    @Delete
    suspend fun delete(profissional: Profissional)

    @Query("SELECT * FROM profissionais ORDER BY nome ASC")
    fun getAll(): Flow<List<Profissional>>

    @Query("SELECT * FROM profissionais WHERE id = :id LIMIT 1")
    fun getById(id: Long): Flow<Profissional?>

    @Query("SELECT * FROM profissionais WHERE email = :email LIMIT 1")
    suspend fun getByEmail(email: String): Profissional?

    @Query("SELECT * FROM profissionais WHERE email = :email AND senha = :senha LIMIT 1")
    suspend fun login(email: String, senha: String): Profissional?

    @Query("SELECT * FROM profissionais WHERE ativo = 1 ORDER BY nome ASC")
    fun getAtivos(): Flow<List<Profissional>>

    @Query("SELECT * FROM profissionais WHERE especialidade LIKE '%' || :especialidade || '%' AND ativo = 1 ORDER BY nome ASC")
    fun getByEspecialidade(especialidade: String): Flow<List<Profissional>>

    @Query("SELECT * FROM profissionais WHERE nome LIKE '%' || :query || '%' OR especialidade LIKE '%' || :query || '%' ORDER BY nome ASC")
    fun buscarProfissionais(query: String): Flow<List<Profissional>>
}
