package com.example.bm_app.validation

fun createPasswordvalidation(password: String): Boolean {
    val passwordRequirement = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+=])(?=\\S+\$).{6,}$"

    return password.matches(passwordRequirement.toRegex())
}