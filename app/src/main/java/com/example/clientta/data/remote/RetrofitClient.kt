package com.example.clientta.data.remote

import com.example.clientta.data.remote.api.AgendamentoApiService
import com.example.clientta.data.remote.api.PreAtendimentoApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Cliente HTTP Singleton configurado com Retrofit e Gson para Agendamentos e Pré-Atendimento.
 */
object RetrofitClient {

    private const val CLIENTTA_API_BASE_URL = "https://api.clientta.com.br/"

    val agendamentoApiService: AgendamentoApiService by lazy {
        Retrofit.Builder()
            .baseUrl(CLIENTTA_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AgendamentoApiService::class.java)
    }

    val preAtendimentoApiService: PreAtendimentoApiService by lazy {
        Retrofit.Builder()
            .baseUrl(CLIENTTA_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PreAtendimentoApiService::class.java)
    }
}
