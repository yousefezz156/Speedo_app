package com.example.bm_app.mycard

import com.microblink.blinkcard.core.result.DateResult

data class CardDetails(
    val cardNumber: String,
    val cardHolderName: String?,
    var expiryDate: String?,
    val cvv: String?
)
