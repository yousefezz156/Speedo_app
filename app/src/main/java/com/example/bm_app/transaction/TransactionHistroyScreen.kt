package com.example.bm_app.transaction

import android.view.SurfaceControl.Transaction
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bm_app.R
import com.example.bm_app.transfer.data4
import androidx.compose.foundation.lazy.items


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffololdTransactionScreen(navController: NavController, modifier: Modifier = Modifier) {

    var selectedItem by rememberSaveable {
        mutableStateOf(2)
    }
    val items = listOf(
        data4(
            routes = "home",
            title = "Home",
            SelectedIcon = painterResource(id = R.drawable.home),
            unselectedItem = painterResource(id = R.drawable.home)
        ),
        data4(
            routes = "transfer",
            title = "Tranfer",
            SelectedIcon = painterResource(id = R.drawable.transfer_figma),
            unselectedItem = painterResource(id = R.drawable.transfer_figma)
        ),
        data4(
            routes = "transactions",
            title = "Transactions",
            SelectedIcon = painterResource(id = R.drawable.transaction_figma),
            unselectedItem = painterResource(id = R.drawable.transaction_figma)
        ),
        data4(
            routes = "my_cards",
            title = "My cards",
            SelectedIcon = painterResource(id = R.drawable.cards_figma),
            unselectedItem = painterResource(id = R.drawable.cards_figma)
        ),
        data4(
            routes = "more",
            title = "More",
            SelectedIcon = painterResource(id = R.drawable.more),
            unselectedItem = painterResource(id = R.drawable.more)
        )
    )
    Scaffold(bottomBar = {
        NavigationBar(modifier = modifier.clip(RoundedCornerShape(24.dp))) {
            items.forEachIndexed { index, data ->
                NavigationBarItem(
                    selected = selectedItem == index,
                    onClick = {
                        selectedItem = index
                        navController.navigate(data.routes)
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
    }, modifier = modifier.background(color = colorResource(id = R.color.main))) { innerpadding ->
        Box(modifier = modifier.padding(innerpadding)) {
            TransactionHistoryScreen()
        }

    }
}

@Composable
fun TransactionHistoryScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
            .background(Color(0xFFFFF5E1)),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Transactions",
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(vertical = 16.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 1.dp, vertical = 8.dp)
            ) {
                Text(
                    text = "Your Last Transactions",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF24221E)
                )
            }
            val transactions = listOf(
                "Ahmed Rasshed" to "Account xxxx7890",
                "Ahmed Fathy" to "Account xxxx7890",

                )
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn {
                items(transactions) { transaction ->
                    TransactionItem(
                        name = "Rashed",
                        accountNumber = "1235",
                        accountType = "card",
                        date = "51515",
                        transactionType = "Sent",
                        amount = 1520,
                        successful = false,
                        onClick = {},
                        modifier = Modifier
                    )

                }
            }
        }
    }
}

@Composable
fun TransactionItem(
    name: String,
    accountNumber: String,
    accountType: String,
    date: String,
    transactionType: String,
    amount: Int,
    successful: Boolean,
    onClick: () -> Unit,
    modifier: Modifier
) {
    Card(
        onClick = { onClick() },
        modifier = modifier
            .background(color = Color.White),
    )
    {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
            //.background(color = Color.White)
        ) {
            IconandBackground(
                icon = painterResource(
                    id =
                    if (accountType == "bank") R.drawable.bankredcolor
                    else R.drawable.cardred
                )
            )
            Spacer(modifier = modifier.padding(12.dp))
            Column() {
                Text(text = name, fontWeight = FontWeight.Bold)
                Text(
                    text = "${if (accountNumber[0] == '4') "Visa" else "Master Card"} .$accountNumber",
                    textAlign = TextAlign.Center,
                    modifier = modifier.padding(top = 4.dp)
                )
                Text(
                    text = "$date - $transactionType",
                    modifier = modifier.padding(top = 4.dp)
                )
                Spacer(modifier = modifier.padding(8.dp))
                Text(text = "$$amount", color = colorResource(id = R.color.reddd))
            }
            Spacer(modifier = Modifier.weight(1f))
            Column(horizontalAlignment = Alignment.End) {
                IconButton(onClick = { /*TODO*/ })
                {
                    Icon(Icons.Filled.KeyboardArrowRight, contentDescription = null)
                    Spacer(modifier = modifier.padding(11.dp))

                }
                Column() {
                    Box(
                        modifier = modifier
                            .clip(shape = RoundedCornerShape(8.dp))
                            .background(
                                color = colorResource(
                                    id = R.color.white
                                )
                            )
                            .padding(4.dp),
                        contentAlignment = Alignment.Center
                    )
                    {
                        if (successful) {
                            Text(
                                text = "successful",
                                fontSize = 10.sp,
                                color = colorResource(id = R.color.green),
                                maxLines = 1
                            )
                        } else {
                            Text(
                                text = "Failed",
                                fontSize = 10.sp,
                                color = colorResource(id = R.color.reddd),
                                maxLines = 1
                            )
                        }
                    }
                }

            }
        }

    }
    Spacer(modifier = modifier.padding(16.dp))
}


@Composable
fun IconandBackground(icon: Painter, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(48.dp)
            .clip(RectangleShape)
            .clip(shape = RoundedCornerShape(8.dp))
            .background(
                colorResource(id = R.color.white)
            ),
        contentAlignment = Alignment.Center

    )
    {
        Image(painter = icon, contentDescription = null)
    }
}

@Preview(showBackground = true)
@Composable
private fun TransactionScreenprev() {
    ScaffololdTransactionScreen(navController = rememberNavController())
}