package com.example.bm_app.api.FavoriteApi

import com.example.bm_app.modelApi.AddFavorite
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface FavoriteApiServices {
    @POST("/api/favorites")
    @Headers("bypass-tunnel-reminder:1")
    fun AddFavorite(@Header("Authorization") token : String,@Body addFavorite: AddFavorite): Response<ResponseBody>

    @DELETE("/api/favorite")
    @Headers("bypass-tunnel-reminder:1")
    fun DeleteFavorite(@Header("Authorization") token : String):Response<ResponseBody>
}