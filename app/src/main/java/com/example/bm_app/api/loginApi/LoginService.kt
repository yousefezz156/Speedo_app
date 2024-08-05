package com.example.bm_app.api.loginApi

import com.example.bm_app.modelApi.LoginRequest
import com.example.bm_app.modelApi.LoginResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginService {
    @Headers("bypass-tunnel-reminder:1")
    @POST("/api/login")
    fun loginUser (@Body loginRequest: LoginRequest) : Call<ResponseBody>
}