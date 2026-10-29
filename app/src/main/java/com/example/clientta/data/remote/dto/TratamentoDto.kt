package com.example.clientta.data.remote.dto

import com.example.clientta.data.entity.Tratamento
import com.google.gson.annotations.SerializedName

/**
 * Data Transfer Object (DTO) para deserialização dos dados remotos de Tratamentos.
 */
data class TratamentoDto(
    @SerializedName("id") val id: Long? = null,
    @SerializedName("nome") val nome: String,
    @SerializedName("descricao") val descricao: String,
    @SerializedName("preco") val preco: Double,
    @SerializedName("duracao") val duracao: Int,
    @SerializedName("ativo") val ativo: Boolean = true
) {
    fun toEntity(): Tratamento = Tratamento(
        id = id ?: 0,
        nome = nome,
        descricao = descricao,
        preco = preco,
        duracao = duracao,
        ativo = ativo
    )
}
