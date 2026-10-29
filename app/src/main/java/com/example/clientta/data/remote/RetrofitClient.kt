package com.example.clientta.data.remote

import com.example.clientta.data.remote.api.ViaCepApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Cliente HTTP Singleton configurado com Retrofit e Gson.
 */
object RetrofitClient {

    private const val VIACEP_BASE_URL = "https://viacep.com.br/"

    /**
     * Instância do serviço ViaCepApi.
     */
    val viaCepApi: ViaCepApi by lazy {
        Retrofit.Builder()
            .baseUrl(VIACEP_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ViaCepApi::class.java)
    }
}
