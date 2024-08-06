package com.example.bm_app.api.loginApi

import com.example.bm_app.BaseUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object loginUserClient {
    private val client: OkHttpClient by lazy {
        val logging = HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
        OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    val instance: LoginService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(LoginService::class.java)
    }
}