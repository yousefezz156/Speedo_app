package com.example.bm_app.api.TransferApi

import com.example.bm_app.BaseUrl
import com.example.bm_app.api.loginApi.LoginService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TransferApiClient {
    private val client: OkHttpClient by lazy {
        val logging = HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
        OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    val instance: TransferApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(TransferApiService::class.java)
    }
}