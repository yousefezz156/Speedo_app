package com.example.bm_app.mycard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bm_app.R
import com.example.bm_app.approutes.AppRoutes
import com.example.bm_app.approutes.AppRoutes.SIGNUP2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Scaffold_AddCard(navController: NavController, modifier: Modifier = Modifier) {
    Scaffold(topBar = {
        TopAppBar(navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.KeyboardArrowLeft, contentDescription = null)

            }
        }, title = {
            Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center)
            {
                Text(text = "Add Card")
            }
        }, actions = {
            TextButton(onClick = { /*TODO*/ }) {
                Text(text = "cancel", fontWeight = FontWeight.Thin)
            }
        })
    }) {
            innerpadding ->

        Box(modifier = modifier.padding(innerpadding)) {
            Add_CardScreen(rememberNavController())
        }
    }
}

@Composable
fun Add_CardScreen(navController: NavController,modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        ) {
        Text(
            text = "Sign on your Speedo Transfer Account",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            maxLines = 2
        )
        Spacer(modifier = modifier.padding(22.dp))

        Column(modifier = modifier.padding(horizontal = 16.dp)) {
            Text(text = "Cardholder Name")
            Spacer(modifier = modifier.padding(8.dp))
            OutlinedTextField(
                value = "",
                onValueChange = { it },
                modifier.fillMaxWidth(),
                placeholder = {
                    Text(
                        text = "Enter Cardholder Name"
                    )
                })
            Spacer(modifier = modifier.padding(8.dp))
            Text(text = "Card No")
            Spacer(modifier = modifier.padding(8.dp))
            OutlinedTextField(
                value = "",
                onValueChange = { it },

                modifier.fillMaxWidth(), placeholder = {
                    Text(
                        text = "Card No"
                    )
                })
            Spacer(modifier = modifier.padding(8.dp))
            Row {
                Column {
                    Text(text = "MM/YY")
                    Spacer(modifier = modifier.padding(8.dp))
                    OutlinedTextField(value = "", onValueChange = { it },
                        modifier
                            .width(168.dp)
                            .height(49.dp),
                        placeholder = { Text(text = "MM/YY") })

                }
                Spacer(modifier = modifier.padding(8.dp))
                Column {
                    Text(text = "CVV")
                    Spacer(modifier = modifier.padding(8.dp))

                    OutlinedTextField(
                        value = "",
                        onValueChange = { it },
                        modifier
                            .width(168.dp)
                            .height(49.dp),
                        placeholder = { Text(text = "CVV") })
                }
            }

        }
        Spacer(modifier = modifier.padding(32.dp))
        Button(
            onClick = { navController.navigate(AppRoutes.MYCARDS_OTP) },
            modifier
                .fillMaxWidth()
                .padding(16.dp)
                .size(56.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(
                    id = R.color.reddd
                )
            )
        ) {
            Text(text = "Sign on")
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun AddCardPrev() {
    Add_CardScreen(rememberNavController())
}