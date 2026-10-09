package com.example.clientta.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Entidade que representa o formulário de pré-atendimento preenchido pelo cliente para um agendamento.
 */
@Entity(
    tableName = "pre_atendimentos",
    foreignKeys = [
        ForeignKey(
            entity = Agendamento::class,
            parentColumns = ["id"],
            childColumns = ["agendamentoId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("agendamentoId", unique = true)
    ]
)
data class PreAtendimento(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val agendamentoId: Long,
    val respostas: String, // Respostas do questionário
    val termoAceito: Boolean,
    val dataPreenchimento: String
)
