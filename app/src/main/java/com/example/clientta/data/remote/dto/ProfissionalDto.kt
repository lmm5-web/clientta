package com.example.clientta.data.remote.dto

import com.example.clientta.data.entity.Profissional
import com.google.gson.annotations.SerializedName

/**
 * Data Transfer Object (DTO) para deserialização dos dados remotos de Profissionais.
 */
data class ProfissionalDto(
    @SerializedName("id") val id: Long? = null,
    @SerializedName("nome") val nome: String,
    @SerializedName("email") val email: String,
    @SerializedName("telefone") val telefone: String,
    @SerializedName("especialidade") val especialidade: String,
    @SerializedName("nomeClinica") val nomeClinica: String? = null,
    @SerializedName("descricao") val descricao: String? = null,
    @SerializedName("ativo") val ativo: Boolean = true
) {
    fun toEntity(): Profissional = Profissional(
        id = id ?: 0,
        nome = nome,
        email = email,
        telefone = telefone,
        especialidade = especialidade,
        senha = "admin",
        nomeClinica = nomeClinica,
        descricao = descricao,
        ativo = ativo
    )
}
