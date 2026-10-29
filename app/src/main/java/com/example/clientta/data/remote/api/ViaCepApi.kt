package com.example.clientta.data.remote.api

import com.example.clientta.data.remote.dto.ViaCepDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Interface Retrofit para consumo do serviço público de busca de CEP (ViaCEP).
 * Base URL: https://viacep.com.br/
 */
interface ViaCepApi {

    /**
     * Busca o endereço correspondente ao CEP informado.
     * @param cep CEP com 8 dígitos (pode conter hífen ou apenas números).
     */
    @GET("ws/{cep}/json/")
    suspend fun buscarEnderecoPorCep(
        @Path("cep") cep: String
    ): Response<ViaCepDto>
}
