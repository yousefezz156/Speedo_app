package com.example.bm_app.mycard

import android.widget.Toast
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bm_app.R
import com.example.bm_app.api.AddCardApi.AddCardClient
import com.example.bm_app.approutes.AppRoutes
import com.example.bm_app.approutes.AppRoutes.SIGNUP2
import com.example.bm_app.modelApi.AddCardRequest
import com.example.bm_app.viewModel.AddCardViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
fun Add_CardScreen(navController: NavController,modifier: Modifier = Modifier , addCardViewModel: AddCardViewModel = viewModel()) {

    var cardHolderName by remember {
        mutableStateOf("yousef")
    }
    var cardNumber by remember {
        mutableStateOf("")
    }
    var monthYear by remember {
        mutableStateOf("")
    }
    var cvv by remember {
        mutableStateOf("")
    }
    var balance by remember {
        mutableIntStateOf(250000)
    }
    val context = LocalContext.current
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
                value = cardHolderName,
                onValueChange = { cardHolderName=it },
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
                value = cardNumber,
                onValueChange = { cardNumber=it },

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
                    OutlinedTextField(value = monthYear, onValueChange = { monthYear=it },
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
                        value = cvv,
                        onValueChange = { cvv=it },
                        modifier
                            .width(168.dp)
                            .height(49.dp),
                        placeholder = { Text(text = "CVV") })
                }
            }

        }
        Spacer(modifier = modifier.padding(32.dp))
        Button(
            onClick = { val addCardRequest = AddCardRequest(
                cardNumber = cardNumber,
                cardholderName = cardHolderName,
                monthYear = monthYear ,
                cvv = cvv,
                pin = "",
                balance = balance,
                currency = "",
                accountType = ""
            )

                AddCardClient.instance.addCard(addCardRequest).enqueue(object : Callback<Void> {
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        if (response.isSuccessful) {
                            addCardViewModel.cardHolderName.value = cardHolderName
                            addCardViewModel.cardNumber.value = cardNumber
                            Toast.makeText(context, "Card added successfully", Toast.LENGTH_SHORT).show()
                            navController.navigate(AppRoutes.MYCARDS_OTP)
                        } else {
                            Toast.makeText(context, "Failed to add card", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                    }
                }) },
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