package com.example.clientta.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.clientta.data.entity.Agendamento
import kotlinx.coroutines.flow.Flow

@Dao
interface AgendamentoDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(agendamento: Agendamento): Long

    @Update
    suspend fun update(agendamento: Agendamento)

    @Delete
    suspend fun delete(agendamento: Agendamento)

    @Query("SELECT * FROM agendamentos ORDER BY data ASC, horario ASC")
    fun getAll(): Flow<List<Agendamento>>

    @Query("SELECT * FROM agendamentos WHERE clienteId = :clienteId ORDER BY data DESC, horario DESC")
    fun getByClienteId(clienteId: Long): Flow<List<Agendamento>>

    @Query("SELECT * FROM agendamentos WHERE id = :id LIMIT 1")
    fun getById(id: Long): Flow<Agendamento?>

    @Query("SELECT * FROM agendamentos WHERE data = :data AND horario = :horario AND status != 'Cancelado' LIMIT 1")
    suspend fun getByDataEHorario(data: String, horario: String): Agendamento?
}
