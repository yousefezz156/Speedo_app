package com.example.bm_app.modelApi

data class Transaction(
    val id: Int,
    val senderCardNumber: String,
    val recipientCardNumber: String,
    val senderUserName: String,
    val recipientUserName: String,
    val senderEmail: String,
    val recipientEmail: String,
    val amount: Int,
    val date: String,
    val status: String
)
