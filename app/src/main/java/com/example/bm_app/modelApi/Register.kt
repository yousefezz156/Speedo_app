package com.example.bm_app.modelApi

data class Register(
    val email:  String ,
    val userName:  String ,
    val password:  String ,
    val gender :String ="",
    val dateOfBirth: String ="" ,
    val country: String="",
    var isActive: Int =1
)
