package com.example.bm_app.buttonbar

import androidx.annotation.DrawableRes
import com.example.bm_app.R

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Transfer : Screen("transfer")
    object Transactions : Screen("transactions")
    object MyCards : Screen("my_cards")
    object More : Screen("more")
}
