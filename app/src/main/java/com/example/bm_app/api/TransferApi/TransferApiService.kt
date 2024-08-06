package com.example.bm_app.api.TransferApi

import com.example.bm_app.modelApi.Transfer
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface TransferApiService {

    @Headers("bypass-tunnel-reminder:1")
    @POST("api/transfer")
    fun transferService(@Header("Authorization") token :String, @Body transfer: Transfer) : Call<Void>
}