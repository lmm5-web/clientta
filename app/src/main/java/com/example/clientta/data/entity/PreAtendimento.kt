package com.example.clientta.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
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
    val termoAceito: Boolean = false,
    val dataPreenchimento: String,
    val alergiasOuCuidados: String? = null
) {
    /**
     * Indica se o pré-atendimento foi devidamente concluído (termo aceito e data de preenchimento válida).
     */
    @get:Ignore
    val concluido: Boolean
        get() = termoAceito && dataPreenchimento.isNotBlank()

    /**
     * Indica se o cliente relatou alguma alergia ou cuidado especial de saúde.
     */
    @get:Ignore
    val possuiAlergiasOuCuidados: Boolean
        get() = !alergiasOuCuidados.isNullOrBlank()
}
