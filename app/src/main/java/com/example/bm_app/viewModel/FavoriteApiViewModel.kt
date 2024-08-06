package com.example.bm_app.viewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.bm_app.api.FavoriteApi.FavoriteApiClient
import com.example.bm_app.modelApi.AddFavorite
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Response

class FavoriteApiViewModel(application: Application): AndroidViewModel(application) {
    private val _addFavoriteResult = MutableStateFlow<Response<ResponseBody>?>(null)
    val addFavoriteResult: StateFlow<Response<ResponseBody>?> get() = _addFavoriteResult

    private val _deleteFavoriteResult = MutableStateFlow<Response<ResponseBody>?>(null)
    val deleteFavoriteResult: StateFlow<Response<ResponseBody>?> get() = _deleteFavoriteResult

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> get() = _error

    fun addFavorite(addFavorite: AddFavorite) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val context = getApplication<Application>().applicationContext
                val sharedPreferences = context.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
                val token = sharedPreferences.getString("auth_token", null)
                if (token != null) {
                    val response = FavoriteApiClient.favoriteApiService.AddFavorite("Bearer $token", addFavorite)
                    _addFavoriteResult.value = response
                    _error.value = null
                } else {
                    _error.value = "No token"
                }
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun deleteFavorite() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val context = getApplication<Application>().applicationContext
                val sharedPreferences = context.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
                val token = sharedPreferences.getString("auth_token", null)
                if (token != null) {
                    val response = FavoriteApiClient.favoriteApiService.DeleteFavorite("Bearer $token")
                    _deleteFavoriteResult.value = response
                    _error.value = null
                } else {
                    _error.value = "No token"
                }
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }
}