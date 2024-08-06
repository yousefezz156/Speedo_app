package com.example.bm_app.api.getUserApi

import com.example.bm_app.BaseUrl
import com.example.bm_app.api.TransactionsApi.TransactionApiService
import com.example.bm_app.api.TransactionsApi.TransactionClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CurrentUserApiClient {

    val instance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BaseUrl.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val getCurrentUserApiService : CurrentUserApiService by lazy {
        instance.create(CurrentUserApiService::class.java)
    }
}