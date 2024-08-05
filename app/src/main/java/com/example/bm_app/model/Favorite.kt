package com.example.bm_app.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.painter.Painter

data class Favorite( @DrawableRes val image : Int ,val id :Int, val name: String , val account : String)