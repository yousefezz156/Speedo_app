package com.example.bm_app.mycard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bm_app.R
import com.example.bm_app.approutes.AppRoutes
import com.example.bm_app.list.Currency_List
import com.example.bm_app.model.Currency

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldCurrency(navController: NavController,modifier: Modifier = Modifier) {
    Scaffold(topBar = {
        TopAppBar(navigationIcon = {
        }, title = {
            Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text(text = "Select Currency")

            }
        },
        )
    }) { innerpadding ->
        Box(modifier = modifier.padding(innerpadding)) {
            lazycol(rememberNavController(),currencyList = Currency_List().getList())
        }


    }
}

@Composable
fun lazycol(navController: NavController ,currencyList: List<Currency>, modifier: Modifier = Modifier) {
    var isselected by remember {
        mutableStateOf(false)
    }

    LazyColumn {
        items(currencyList) { currencyy ->
            CurrencyCard(currencyy)
        }
    }
    Column(modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom)
    {
        Button(
            onClick = { navController.navigate(AppRoutes.MYCARDS_ADDCARDS)},
            modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .size(56.dp),
            colors = if(false){
                ButtonDefaults.buttonColors(Color.LightGray)
            }else {ButtonDefaults.buttonColors(colorResource(id = R.color.reddd))},
            shape = RoundedCornerShape(6.dp),
        )
        {

            Text(text = "confirm that")
        }
        Spacer(modifier = modifier.padding(77.dp))
    }
}

@Composable
fun CurrencyCard(currency: Currency, modifier: Modifier = Modifier) {
    var isselected by remember {
        mutableStateOf(false)
    }
    Row(modifier = modifier
        .fillMaxWidth()
        .padding(16.dp)
        .clickable {
            if (!isselected) {
                isselected = true
            } else {
                isselected = false
            }
        }) {
        Image(painter = painterResource(id = currency.image), contentDescription = null)
        Spacer(modifier = modifier.padding(8.dp))
        Text(text = currency.text)

        if (isselected) {
            Spacer(modifier = modifier.weight(1f))
            Icon(Icons.Filled.Check, contentDescription = null)
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun prevcurr() {
    ScaffoldCurrency(rememberNavController())
}