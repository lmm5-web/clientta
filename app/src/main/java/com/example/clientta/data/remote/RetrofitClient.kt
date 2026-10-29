package com.example.clientta.data.remote

import com.example.clientta.data.remote.api.ProfissionalApiService
import com.example.clientta.data.remote.api.TratamentoApiService
import com.example.clientta.data.remote.api.ViaCepApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Cliente HTTP Singleton configurado com Retrofit e Gson para todas as APIs remotas do Clientta.
 */
object RetrofitClient {

    private const val VIACEP_BASE_URL = "https://viacep.com.br/"
    private const val CLIENTTA_API_BASE_URL = "https://api.clientta.com.br/"

    /**
     * Serviço público para consulta de CEP.
     */
    val viaCepApi: ViaCepApi by lazy {
        Retrofit.Builder()
            .baseUrl(VIACEP_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ViaCepApi::class.java)
    }

    /**
     * Serviço de tratamentos/catálogo da clínica.
     */
    val tratamentoApiService: TratamentoApiService by lazy {
        Retrofit.Builder()
            .baseUrl(CLIENTTA_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TratamentoApiService::class.java)
    }

    /**
     * Serviço de profissionais da equipe.
     */
    val profissionalApiService: ProfissionalApiService by lazy {
        Retrofit.Builder()
            .baseUrl(CLIENTTA_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProfissionalApiService::class.java)
    }
}
