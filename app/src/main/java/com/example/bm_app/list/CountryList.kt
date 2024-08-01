package com.example.bm_app.list

import androidx.compose.ui.res.painterResource
import com.example.bm_app.R
import com.example.bm_app.model.Country

class CountryList {
    fun getCountryList(): List<Country>{
        val counrtyList = mutableListOf<Country>()
        counrtyList.add(
            Country("united States" ,  R.drawable.united_states )
        )
        counrtyList.add(
            Country("united States" , R.drawable.united_states )
        )
        counrtyList.add(
            Country("united States" , R.drawable.united_states )
        )
        counrtyList.add(
            Country("united States" , R.drawable.united_states)
        )
        counrtyList.add(
            Country("united States" , R.drawable.united_states)
        )

        return counrtyList
    }
}