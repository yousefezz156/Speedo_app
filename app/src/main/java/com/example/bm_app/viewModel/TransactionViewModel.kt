package com.example.bm_app.viewModel

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bm_app.api.TransactionsApi.TransactionClient
import com.example.bm_app.modelApi.Transaction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TransactionViewModel(application: Application) : AndroidViewModel(application) {

    private val _transactions = MutableStateFlow<List<Transaction>>(emptyList())
    val transaction : StateFlow<List<Transaction>> get() = _transactions

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> get() = _error


    init {
        fetchTransactions()
    }
    private fun fetchTransactions(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val context =getApplication<Application>().applicationContext
                val sharedPreferences =context.getSharedPreferences("MyAppPreferences" , Context.MODE_PRIVATE)
                val token = sharedPreferences.getString("auth_token" , null)
                if(token!=null) {
                    val transactionss =
                        TransactionClient.transactionApiService.getTransaction("Bearer $token")
                    _transactions.value = transactionss
                    _error.value = null
                }else{
                    _error.value ="no token"

                }
            }catch (e:Exception){
                _error.value = e.message
            }
        }
    }
}