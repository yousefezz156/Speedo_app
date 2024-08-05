package com.example.bm_app.api.AddCardApi

import com.example.bm_app.modelApi.AddCardRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AddCardService {
    @Headers("bypass-tunnel-reminder:1")
    @POST("/api/cards")
    fun addCard(@Body addCardRequest: AddCardRequest): Call<Void>
}