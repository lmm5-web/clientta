package com.example.clientta.data.remote.api

import com.example.clientta.data.remote.dto.AgendamentoDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Interface Retrofit para endpoints de agendamento de consultas.
 */
interface AgendamentoApiService {

    @GET("api/agendamentos/cliente/{clienteId}")
    suspend fun getAgendamentosPorCliente(@Path("clienteId") clienteId: Long): Response<List<AgendamentoDto>>

    @POST("api/agendamentos")
    suspend fun criarAgendamento(@Body agendamentoDto: AgendamentoDto): Response<AgendamentoDto>
}
