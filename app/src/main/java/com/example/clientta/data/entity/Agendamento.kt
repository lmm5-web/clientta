package com.example.clientta.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
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
    val status: String = STATUS_AGENDADO,
    val observacoes: String? = null,
    val motivoCancelamento: String? = null
) {
    /**
     * Retorna a data e o horário formatados para exibição na UI (ex: "15/10/2026 às 14:30").
     */
    @get:Ignore
    val dataEHorarioFormatados: String
        get() = "$data às $horario"

    /**
     * Indica se o agendamento está em aberto/pendente de atendimento.
     */
    @get:Ignore
    val estaPendente: Boolean
        get() = (status == STATUS_PENDENTE || status == STATUS_AGENDADO)

    /**
     * Indica se o agendamento já foi concluído.
     */
    @get:Ignore
    val estaConcluido: Boolean
        get() = status == STATUS_CONCLUIDO

    /**
     * Indica se o agendamento foi cancelado.
     */
    @get:Ignore
    val estaCancelado: Boolean
        get() = status == STATUS_CANCELADO

    companion object {
        const val STATUS_PENDENTE = "Pendente"
        const val STATUS_AGENDADO = "Agendado"
        const val STATUS_EM_ANDAMENTO = "Em Andamento"
        const val STATUS_CONCLUIDO = "Concluído"
        const val STATUS_CANCELADO = "Cancelado"
    }
}
