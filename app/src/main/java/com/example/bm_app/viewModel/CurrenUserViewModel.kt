package com.example.bm_app.viewModel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.bm_app.api.getUserApi.CurrentUserApiClient
import com.example.bm_app.modelApi.CurrentUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class CurrenUserViewModel(application: Application) : AndroidViewModel(application) {
    val _currentUser = MutableStateFlow<CurrentUser?>(null)
    val currentUser: StateFlow<CurrentUser?> get() = _currentUser


    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> get() = _error

    init {
        fetchCurrentUser()
    }

    fun fetchCurrentUser() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val context = getApplication<Application>().applicationContext
                val sharedPreferences =
                    context.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
                val token = sharedPreferences.getString("auth_token", null)
                Log.d("Token", "Retrieved token: $token")
                if (token != null) {
                    val usercurrent =
                        CurrentUserApiClient.getCurrentUserApiService.GetCurrenUser("Bearer $token")
                    withContext(Dispatchers.Main) {
                        _currentUser.value = usercurrent
                        _error.value = null
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        _error.value = "no token"
                    }
                }
            } catch (e: HttpException) {
                withContext(Dispatchers.Main) {
                    when (e.code()) {
                        403 -> _error.value = "Access denied. Please check your permissions."
                        else -> _error.value = "Failed to fetch user data. Error code: ${e.code()}"
                    }
                }
            } catch (e: IOException) {
                withContext(Dispatchers.Main) {
                    _error.value = "Network error. Please check your connection."
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _error.value = "An unexpected error occurred: ${e.message}"
                }
            }

        }
    }
}
