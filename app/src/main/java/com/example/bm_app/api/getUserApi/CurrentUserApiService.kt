package com.example.bm_app.api.getUserApi

import com.example.bm_app.modelApi.CurrentUser
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface CurrentUserApiService {
    @Headers("bypass-tunnel-reminder:1")
    @GET("/api/user")
    suspend fun GetCurrenUser(@Header("Authorization") token :String ): CurrentUser
}