package com.example.clientta.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.clientta.data.entity.Tratamento
import kotlinx.coroutines.flow.Flow

@Dao
interface TratamentoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tratamento: Tratamento): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(tratamentos: List<Tratamento>)

    @Update
    suspend fun update(tratamento: Tratamento)

    @Delete
    suspend fun delete(tratamento: Tratamento)

    @Query("SELECT * FROM tratamentos ORDER BY nome ASC")
    fun getAll(): Flow<List<Tratamento>>

    @Query("SELECT * FROM tratamentos WHERE ativo = 1 ORDER BY nome ASC")
    fun getAtivos(): Flow<List<Tratamento>>

    @Query("SELECT * FROM tratamentos WHERE id = :id LIMIT 1")
    fun getById(id: Long): Flow<Tratamento?>

    @Query("SELECT COUNT(*) FROM tratamentos")
    suspend fun getCount(): Int
}
