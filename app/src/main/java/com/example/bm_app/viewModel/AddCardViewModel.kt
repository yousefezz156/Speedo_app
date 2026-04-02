package com.example.bm_app.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.bm_app.mycard.CardDetails
import kotlinx.coroutines.flow.MutableStateFlow

class AddCardViewModel :ViewModel() {


    var cardHolderName = mutableStateOf("yousef E")
    var cardNumber  = mutableStateOf("23458997")

    private val _cardDet = MutableStateFlow<CardDetails?>(null)
    val cardDetails get() = _cardDet.value

    fun editCard():String{
        var realExpiryDate=""
        var count=0
        for (i in cardDetails?.expiryDate.toString() ){
            if(i.isDigit()){
                realExpiryDate+=i;
                count++;
            }
            if(count==2){
                realExpiryDate+="/"
                count++;
            }
        }

        return realExpiryDate
    }

    fun addCard(card: CardDetails){
        _cardDet.value = card
        _cardDet.value?.expiryDate=editCard()

        Log.d("addCard", "Card added successfully ${card.cardHolderName} ${_cardDet.value}")
    }




}