package com.example.clientta.data.remote.dto

import com.example.clientta.data.entity.Agendamento
import com.google.gson.annotations.SerializedName

/**
 * Data Transfer Object (DTO) para deserialização dos agendamentos remotos.
 */
data class AgendamentoDto(
    @SerializedName("id") val id: Long? = null,
    @SerializedName("clienteId") val clienteId: Long,
    @SerializedName("tratamentoId") val tratamentoId: Long,
    @SerializedName("data") val data: String,
    @SerializedName("horario") val horario: String,
    @SerializedName("status") val status: String = "Agendado",
    @SerializedName("observacoes") val observacoes: String? = null
) {
    fun toEntity(): Agendamento = Agendamento(
        id = id ?: 0,
        clienteId = clienteId,
        tratamentoId = tratamentoId,
        data = data,
        horario = horario,
        status = status,
        observacoes = observacoes
    )
}
