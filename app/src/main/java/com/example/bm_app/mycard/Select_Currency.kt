package com.example.bm_app.mycard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bm_app.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldCurrency(modifier: Modifier = Modifier) {
    Scaffold (topBar = {
        TopAppBar(navigationIcon = {
            IconButton(onClick = { /*TODO*/ }){
                Icon(Icons.Filled.KeyboardArrowLeft, contentDescription = null)
        }
        },title = { Box (modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
            Text(text = "Select Currency")

        } }, actions = { TextButton(onClick = { /*TODO*/ }) {
            Text(text = "Cancel" , fontWeight = FontWeight.Thin)
        }})
    }){ innerpadding ->
        Box (modifier= modifier.padding(innerpadding)){
            CurrencyCard()
        }


    }
}

@Composable
fun CurrencyCard(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()) {

            Box(modifier = modifier.fillMaxSize()) 
            {
                Row(modifier = modifier.fillMaxWidth()) {
                    Spacer(modifier = modifier.padding(8.dp))
                    Image(painter = painterResource(id = R.drawable.united_states), contentDescription =null )
                    Spacer(modifier = modifier.padding(12.dp))
                    Text(text = "United Status")
                    Spacer(modifier = modifier.padding(8.dp))
                }




            }
        }
    }


@Preview(showBackground = true)
@Composable
private fun prevcurr() {
    ScaffoldCurrency()
}