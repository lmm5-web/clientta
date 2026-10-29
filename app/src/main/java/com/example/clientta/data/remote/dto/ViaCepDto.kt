package com.example.clientta.data.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * Data Transfer Object (DTO) para deserialização do JSON retornado pela API ViaCEP.
 */
data class ViaCepDto(
    @SerializedName("cep") val cep: String? = null,
    @SerializedName("logradouro") val logradouro: String? = null,
    @SerializedName("complemento") val complemento: String? = null,
    @SerializedName("bairro") val bairro: String? = null,
    @SerializedName("localidade") val localidade: String? = null,
    @SerializedName("uf") val uf: String? = null,
    @SerializedName("ibge") val ibge: String? = null,
    @SerializedName("gia") val gia: String? = null,
    @SerializedName("ddd") val ddd: String? = null,
    @SerializedName("siafi") val siafi: String? = null,
    @SerializedName("erro") val erro: Boolean? = null
) {
    /**
     * Retorna o endereço formatado em uma única String para exibição fácil na UI.
     */
    fun toEnderecoCompleto(): String {
        val partes = listOfNotNull(
            logradouro.takeIf { !it.isNullOrBlank() },
            bairro.takeIf { !it.isNullOrBlank() },
            localidade?.let { loc -> uf?.let { "$loc - $it" } ?: loc }
        )
        return if (partes.isNotEmpty()) partes.joinToString(", ") else ""
    }
}
