package com.example.bm_app.api.registerApi

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RegisterApiPost {
    private val client: OkHttpClient by lazy {
        val logging = HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
        OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    val instance: RegisterServices by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://chubby-sloths-taste.loca.lt")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

      retrofit.create(RegisterServices::class.java)
    }
}
