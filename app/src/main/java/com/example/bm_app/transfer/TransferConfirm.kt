package com.example.bm_app.transfer

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bm_app.NotificationHelper
import com.example.bm_app.R
import com.example.bm_app.api.TransferApi.TransferApiClient
import com.example.bm_app.approutes.AppRoutes
import com.example.bm_app.approutes.AppRoutes.TRANSFER_CONFIRMATION
import com.example.bm_app.modelApi.Transfer
import com.example.bm_app.viewModel.AddCardViewModel
import okhttp3.Callback
import org.xmlpull.v1.sax2.Driver
import retrofit2.Call
import retrofit2.Response

data class data3(
    val route: String,
    val title: String,
    val SelectedIcon: Painter,
    val unselectedItem: Painter
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun scaffoldConfirm(
    navController: NavController,
    recipientname: String,
    recipientaccount: String,
    addCardViewModel: AddCardViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    var selectedItem by rememberSaveable {
        mutableStateOf(1)
    }
    val items = listOf(
        data3(
            route = "home",
            title = "Home",
            SelectedIcon = painterResource(id = R.drawable.home),
            unselectedItem = painterResource(id = R.drawable.home)
        ),
        data3(
            route = "no",
            title = "Transfer",
            SelectedIcon = painterResource(id = R.drawable.transfer_figma),
            unselectedItem = painterResource(id = R.drawable.transfer_figma)
        ),
        data3(
            route = "transactions",
            title = "Transactions",
            SelectedIcon = painterResource(id = R.drawable.transaction_figma),
            unselectedItem = painterResource(id = R.drawable.transaction_figma)
        ),
        data3(
            route = "my_cards",
            title = "My cards",
            SelectedIcon = painterResource(id = R.drawable.cards_figma),
            unselectedItem = painterResource(id = R.drawable.cards_figma)
        ),
        data3(
            route = "more",
            title = "More",
            SelectedIcon = painterResource(id = R.drawable.more),
            unselectedItem = painterResource(id = R.drawable.more)
        )
    )
    Scaffold(
        topBar = {
            TopAppBar(navigationIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Filled.KeyboardArrowLeft, contentDescription = null)

                }
            }, title = {
                Box(contentAlignment = Alignment.Center, modifier = modifier.fillMaxWidth()) {
                    Text(text = "Transfer")
                }
            })
        }, bottomBar = {
            NavigationBar(modifier = modifier.clip(RoundedCornerShape(24.dp))) {
                items.forEachIndexed { index, data ->
                    NavigationBarItem(
                        selected = selectedItem == index,
                        onClick = {
                            selectedItem = index
                            if (data.route != "no") {
                                navController.navigate(data.route)
                            }
                        },
                        icon = {
                            Icon(
                                painter = if (selectedItem == index) data.SelectedIcon else data.unselectedItem,
                                contentDescription = null
                            )
                        },
                        label = {
                            Text(
                                text = data.title,
                                fontSize = 11.sp,
                                textAlign = TextAlign.Center,
                                maxLines = 1
                            )
                        })
                }
            }
        }
    )
    { innerpadding ->
        Box(modifier = modifier.padding(innerpadding)) {
            TransferConfirmation(
                navController = navController,
                recipientname,
                recipientaccount,
                addCardViewModel
            )
        }
    }
}

@Composable
fun TransferConfirmation(
    navController: NavController,
    recipientname: String,
    recipientaccount: String,
    addCardViewModel: AddCardViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val cardHolderName = addCardViewModel.cardHolderName.value
    val cardNumber = addCardViewModel.cardNumber.value
    val context = LocalContext.current
    val notificationHelper = remember { NotificationHelper(context) }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
            .verticalScroll(state = ScrollState(1), true),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier.fillMaxWidth()) {
            stepintext(
                painter = painterResource(id = R.drawable.step_text___horizontal),
                text = " Amount"
            )
            stepintext(painter = painterResource(id = R.drawable.step_trail), text = "")
            stepintext(
                painter = painterResource(id = R.drawable.step_text_2_red),
                text = " Confirmation"
            )
            stepintext(painter = painterResource(id = R.drawable.step_trail_gray), text = "")
            stepintext(
                painter = painterResource(id = R.drawable.step_text_2_gray),
                text = "payment"
            )
        }
        Spacer(modifier = modifier.padding(8.dp))
        Text(text = "1000 USD", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Spacer(modifier = modifier.padding(4.dp))
        Text(text = "Transfer Amount")

        Spacer(modifier = modifier.padding(8.dp))
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        {
            Text(text = "Total amount")
            Row(horizontalArrangement = Arrangement.End, modifier = modifier.fillMaxWidth())
            {
                Text(text = "48,4220")
            }
        }
        Spacer(modifier = modifier.padding(6.dp))
        Divider(
            color = Color.Gray,
            thickness = 0.2.dp,
            modifier = modifier.padding(horizontal = 8.dp)
        )
        Spacer(modifier = modifier.padding(8.dp))

        Box() {
            Column() {


                Card(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(150.dp)
                )
                {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = modifier
                            .fillMaxSize()
                            .padding(horizontal = 32.dp)
                    )
                    {
                        //card(painter = R.drawable.bank, name = "Asmaa Dosuky" , accountB ="Account xxxx7890" )
                        Image(
                            painter = painterResource(id = R.drawable.bank),
                            contentDescription = null
                        )
                        Spacer(modifier = modifier.padding(12.dp))
                        Column {
                            Text(text = "From")
                            Text(
                                text = cardHolderName,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                modifier = modifier.padding(top = 16.dp)
                            )

                            Text(
                                text = "Account ${
                                    cardNumber.takeLast(4).padStart(cardNumber.length, '*')
                                }",
                                modifier = modifier.padding(top = 12.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = modifier.padding(8.dp))
                Card(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(150.dp)
                )
                {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = modifier
                            .fillMaxSize()
                            .padding(32.dp)
                    )
                    {
                        //card(painter = R.drawable.bank, name = "Asmaa Dosuky" , accountB ="Account xxxx7890" )
                        Image(
                            painter = painterResource(id = R.drawable.bank),
                            contentDescription = null
                        )
                        Spacer(modifier = modifier.padding(12.dp))
                        Column {
                            Text(text = "From")
                            Text(
                                text = "$recipientname",
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                modifier = modifier.padding(top = 16.dp)
                            )

                            Text(
                                text = "Account $recipientaccount", fontSize = 12.sp,
                                // modifier = modifier.padding(top = 12.dp)
                            )
                        }
                    }
                }
            }
            //chatGpt
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .background(color = colorResource(id = R.color.brown), shape = CircleShape)
                    .size(40.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_check), // Replace with your actual icon resource
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
        Spacer(modifier = modifier.padding(12.dp))
        Button(
            onClick = {notificationHelper.sendNotification(
                "Transfer Successful",
                "Your transfer of $500 to $recipientname was successful."
            )
                val transfer = Transfer(
                    senderCardNumber = "123",
                    recipientCardNumber = recipientaccount,
                    senderUserName = "",
                    senderEmail = "",
                    recipientUserName = recipientname,
                    recipientEmail = "",
                    amount = 500,
                    date = ""
                )

                val sharedPreferences = context.getSharedPreferences("MyAppPreferences" , Context.MODE_PRIVATE)
                val token = sharedPreferences.getString("auth_token", null)

                token?.let {
                    TransferApiClient.instance.transferService("Bearer $it",transfer).enqueue(object : retrofit2.Callback<Void> {
                        override fun onResponse(call: Call<Void>, response: Response<Void>) {
                            if(response.isSuccessful){
                                Toast.makeText(context , "Transfer succesfully" , Toast.LENGTH_SHORT).show()
                                notificationHelper.sendNotification(
                                    "Transfer Successful",
                                    "Your transfer of $500 to $recipientname was successful."
                                )
                                navController.navigate(AppRoutes.TRANSFER_PAYMENT)
                            }else{
                                val error = response.errorBody()?.string() ?: "Unknown error"
                                Toast.makeText(context, "Transfer failed: ${response.code()} - $error", Toast.LENGTH_SHORT).show()
                            }
                        }

                        override fun onFailure(call: Call<Void>, t: Throwable) {
                            Toast.makeText(context, "Transfer failed: ${t.message}", Toast.LENGTH_SHORT).show()
                        }

                    })}
            },
            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.reddd)),
            modifier = modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(6.dp)

        ) {
            Text(text = "Confirm")
        }
        Spacer(modifier = modifier.padding(8.dp))
        Button(onClick = {navController.navigate(AppRoutes.TRANSFER_AMOUNT)},
            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.white)),
            modifier = modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(6.dp),
            border = BorderStroke(width = 0.5.dp, color = colorResource(id = R.color.reddd)))
            {
            Text(text = "Previous", color = colorResource(id = R.color.reddd))
        }
    }
}



@Preview(showBackground = true)
@Composable
private fun prevs() {
    //TransferConfirmation()
    scaffoldConfirm(navController = rememberNavController(), "", "")
}