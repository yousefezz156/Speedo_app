package com.example.bm_app.list

import com.example.bm_app.R
import com.example.bm_app.model.Favorite

class favoriteList {
    fun getFavoriteList(): List<Favorite> {
        val favorite = mutableListOf<Favorite>()
        favorite.add(
            Favorite(
                R.drawable.bank,
                1,
                "Rashed",
                "6548489"
            )
        )
        favorite.add(
            Favorite(
                R.drawable.bank,
                2,
                "Ahmed Fathi",
                "54648489890"
            )
        )
        favorite.add(
            Favorite(
                R.drawable.bank,
                2,
                "Ahmed Galal",
                "5465454654"
            )
        )
        return favorite
    }

}