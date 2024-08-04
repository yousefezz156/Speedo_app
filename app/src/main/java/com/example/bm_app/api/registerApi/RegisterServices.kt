package com.example.bm_app.api.registerApi

import com.example.bm_app.modelApi.Register
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface RegisterServices {
    @Headers("bypass-tunnel-reminder:1")
    @POST("/api/register")
fun createuser(@Body register: Register): Call<Void>
}

