package com.example.bm_app.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bm_app.api.TransactionsApi.TransactionClient
import com.example.bm_app.modelApi.Transaction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TransactionViewModel : ViewModel() {

    private val _transactions = MutableStateFlow<List<Transaction>>(emptyList())
    val transaction : StateFlow<List<Transaction>> get() = _transactions

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> get() = _error


    init {
        fetchTransactions()
    }
    private fun fetchTransactions(){
        viewModelScope.launch {
            try {
                val transactionss = TransactionClient.transactionApiService.getTransaction()
                _transactions.value=transactionss
                _error.value = null
            }catch (e:Exception){
                _error.value = e.message
            }
        }
    }
}