package com.example.clientta.data.remote.api

import com.example.clientta.data.remote.dto.TratamentoDto
import retrofit2.Response
import retrofit2.http.GET

/**
 * Interface Retrofit para consumo do serviço de tratamentos da clínica.
 */
interface TratamentoApiService {

    @GET("api/tratamentos")
    suspend fun getTratamentosRemotos(): Response<List<TratamentoDto>>
}
