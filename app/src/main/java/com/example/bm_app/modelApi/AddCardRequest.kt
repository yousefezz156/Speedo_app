package com.example.bm_app.modelApi

data class AddCardRequest(
val cardNumber: String,
val cardholderName: String,
val monthYear: String,
val cvv: String,
val pin: String,
val balance: Int,
val currency: String,
val accountType: String
)

