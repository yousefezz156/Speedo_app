package com.example.bm_app.api.TransactionsApi

import com.example.bm_app.modelApi.Transaction
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface TransactionApiService {

    @Headers("bypass-tunnel-reminder:1")
    @GET("/api/transactions?page=0&&size=20")
   suspend fun getTransaction(@Header("Authorization") token : String):List<Transaction>
}