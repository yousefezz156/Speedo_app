package com.example.bm_app.api.loginApi

import com.example.bm_app.modelApi.LoginRequest
import com.example.bm_app.modelApi.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {

    @POST("/api/login")
    fun loginUser (@Body loginRequest: LoginRequest) : Call<LoginResponse>
}