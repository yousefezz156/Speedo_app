package com.example.bm_app.api.TransactionsApi

import com.example.bm_app.BaseUrl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object TransactionClient {
    val instance:Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BaseUrl.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val transactionApiService: TransactionApiService by lazy {
        instance.create(TransactionApiService::class.java)
    }
}