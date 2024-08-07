package com.example.bm_app.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class AddCardViewModel :ViewModel() {

    var cardHolderName = mutableStateOf("yousef E")
    var cardNumber  = mutableStateOf("23458997")
}