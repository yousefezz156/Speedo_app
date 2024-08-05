package com.example.bm_app.modelApi

data class Transfer(
    val senderCardNumber: String,
    val recipientCardNumber: String,
    val senderUserName: String,
    val senderEmail: String,
    val recipientUserName: String,
    val recipientEmail: String,
    val amount : Int,
    val date: String)
