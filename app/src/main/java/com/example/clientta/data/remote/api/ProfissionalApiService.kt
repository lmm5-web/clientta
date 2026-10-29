package com.example.clientta.data.remote.api

import com.example.clientta.data.remote.dto.ProfissionalDto
import retrofit2.Response
import retrofit2.http.GET

/**
 * Interface Retrofit para consumo do serviço de profissionais da clínica.
 */
interface ProfissionalApiService {

    @GET("api/profissionais")
    suspend fun getProfissionaisRemotos(): Response<List<ProfissionalDto>>
}
