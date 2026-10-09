package com.example.clientta.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Entidade que representa um agendamento efetuado por um cliente para determinado tratamento.
 */
@Entity(
    tableName = "agendamentos",
    foreignKeys = [
        ForeignKey(
            entity = Cliente::class,
            parentColumns = ["id"],
            childColumns = ["clienteId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Tratamento::class,
            parentColumns = ["id"],
            childColumns = ["tratamentoId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("clienteId"),
        Index("tratamentoId")
    ]
)
data class Agendamento(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val clienteId: Long,
    val tratamentoId: Long,
    val data: String, // Formato AAAA-MM-DD ou DD/MM/AAAA
    val horario: String, // Formato HH:mm
    val status: String = STATUS_AGENDADO
) {
    companion object {
        const val STATUS_AGENDADO = "Agendado"
        const val STATUS_CONCLUIDO = "Concluído"
        const val STATUS_CANCELADO = "Cancelado"
    }
}
