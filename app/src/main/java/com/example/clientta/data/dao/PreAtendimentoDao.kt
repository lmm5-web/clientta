package com.example.clientta.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.clientta.data.entity.PreAtendimento
import kotlinx.coroutines.flow.Flow

@Dao
interface PreAtendimentoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(preAtendimento: PreAtendimento): Long

    @Update
    suspend fun update(preAtendimento: PreAtendimento)

    @Delete
    suspend fun delete(preAtendimento: PreAtendimento)

    @Query("SELECT * FROM pre_atendimentos WHERE agendamentoId = :agendamentoId LIMIT 1")
    fun getByAgendamentoId(agendamentoId: Long): Flow<PreAtendimento?>

    @Query("SELECT * FROM pre_atendimentos WHERE id = :id LIMIT 1")
    fun getById(id: Long): Flow<PreAtendimento?>
}
