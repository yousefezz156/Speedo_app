package com.example.bm_app.list

import com.example.bm_app.R
import com.example.bm_app.model.Currency

class Currency_List
{
 fun getList() : List<Currency>{
     val currency = mutableListOf<Currency>()

     currency.add(Currency("United State" , R.drawable.united_states))
     currency.add(Currency("United State" , R.drawable.united_states))
     currency.add(Currency("United State" , R.drawable.united_states))
     currency.add(Currency("United State" , R.drawable.united_states))
     currency.add(Currency("United State" , R.drawable.united_states))

     return currency
 }
}