package com.example.bm_app.api.TransactionsApi

import com.example.bm_app.modelApi.Transaction
import retrofit2.http.Headers
import retrofit2.http.POST

interface TransactionApiService {

    @Headers()
    @POST("/api/gettransaction")
    fun getTransaction():List<Transaction>
}