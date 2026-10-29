package com.example.clientta.data.remote.dto

import com.example.clientta.data.entity.PreAtendimento
import com.google.gson.annotations.SerializedName

/**
 * Data Transfer Object (DTO) para formulários de pré-atendimento (anamnese).
 */
data class PreAtendimentoDto(
    @SerializedName("id") val id: Long? = null,
    @SerializedName("agendamentoId") val agendamentoId: Long,
    @SerializedName("respostas") val respostas: String,
    @SerializedName("termoAceito") val termoAceito: Boolean = true,
    @SerializedName("dataPreenchimento") val dataPreenchimento: String,
    @SerializedName("alergiasOuCuidados") val alergiasOuCuidados: String? = null
) {
    fun toEntity(): PreAtendimento = PreAtendimento(
        id = id ?: 0,
        agendamentoId = agendamentoId,
        respostas = respostas,
        termoAceito = termoAceito,
        dataPreenchimento = dataPreenchimento,
        alergiasOuCuidados = alergiasOuCuidados
    )
}
