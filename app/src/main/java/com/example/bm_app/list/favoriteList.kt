package com.example.bm_app.list

import com.example.bm_app.R
import com.example.bm_app.model.Favorite

class favoriteList {
    fun getFavoriteList(): List<Favorite> {
        val favorite = mutableListOf<Favorite>()
        favorite.add(
            Favorite(
                R.drawable.bank,
                "asmaa dosuky",
                "Account xxxx7890"
            )
        )
        favorite.add(
            Favorite(
                R.drawable.bank,
                "asmaa dosuky",
                "Account xxxx7890"
            )
        )
        return favorite
    }

}