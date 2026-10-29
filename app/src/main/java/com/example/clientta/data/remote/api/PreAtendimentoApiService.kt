package com.example.clientta.data.remote.api

import com.example.clientta.data.remote.dto.PreAtendimentoDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Interface Retrofit para envio e consulta da ficha de pré-atendimento/anamnese.
 */
interface PreAtendimentoApiService {

    @GET("api/preatendimento/agendamento/{agendamentoId}")
    suspend fun getPreAtendimento(@Path("agendamentoId") agendamentoId: Long): Response<PreAtendimentoDto>

    @POST("api/preatendimento")
    suspend fun enviarPreAtendimento(@Body dto: PreAtendimentoDto): Response<PreAtendimentoDto>
}
