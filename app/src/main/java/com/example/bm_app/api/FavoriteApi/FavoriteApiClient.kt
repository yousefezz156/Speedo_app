package com.example.bm_app.api.FavoriteApi

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object FavoriteApiClient {
    private val client: OkHttpClient by lazy {
        val logging = HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
        OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    val instance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://localhost")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    val favoriteApiService: FavoriteApiServices by lazy {
        instance.create(FavoriteApiServices::class.java)
    }
}