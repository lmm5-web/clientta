package com.example.clientta.data.remote

import com.example.clientta.data.remote.api.ProfissionalApiService
import com.example.clientta.data.remote.api.TratamentoApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Cliente HTTP Singleton configurado com Retrofit e Gson para APIs remotas.
 */
object RetrofitClient {

    private const val CLIENTTA_API_BASE_URL = "https://api.clientta.com.br/"

    val tratamentoApiService: TratamentoApiService by lazy {
        Retrofit.Builder()
            .baseUrl(CLIENTTA_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TratamentoApiService::class.java)
    }

    val profissionalApiService: ProfissionalApiService by lazy {
        Retrofit.Builder()
            .baseUrl(CLIENTTA_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProfissionalApiService::class.java)
    }
}
