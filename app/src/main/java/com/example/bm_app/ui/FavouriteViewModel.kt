package com.example.bm_app.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.bm_app.list.favoriteList
import com.example.bm_app.model.Favorite

class FavouriteViewModel (app: Application, favorite: List<Favorite>) : AndroidViewModel(app) {
    var favorite = favorite
    fun getFavourites() = favorite
}