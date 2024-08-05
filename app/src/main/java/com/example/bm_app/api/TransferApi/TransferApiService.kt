package com.example.bm_app.api.TransferApi

import com.example.bm_app.modelApi.Transfer
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface TransferApiService {

    @Headers("bypass-tunnel-reminder:1")
    @POST("api/transfer")
    fun transferService(@Body transfer: Transfer) : Call<Void>
}