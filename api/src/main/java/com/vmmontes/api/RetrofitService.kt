package com.vmmontes.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    private lateinit var service: BaseAppApiService
    private var initService = false
    val appBaseApiURL = "https://api.exchangeratesapi.io/"
    val a = "https://api.exchangeratesapi.io/"
    val accesKey = "4f78e5893a5c64cddf5b91d4363619f2"

    fun getService(): BaseAppApiService {
        if (!initService) {
            val httpClient = OkHttpClient.Builder().build()
            val GsonConverterFactory = GsonConverterFactory.create()

            val retrofit = Retrofit.Builder().baseUrl(appBaseApiURL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory)
                .build()

            service = retrofit.create(BaseAppApiService::class.java)

            initService = true
        }

        return service
    }
}