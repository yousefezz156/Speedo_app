package com.example.bm_app.transaction

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
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
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bm_app.R
import com.example.bm_app.approutes.AppRoutes
import com.example.bm_app.transfer.data4

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffololdSuccessTransactionScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {

    var selectedItem by rememberSaveable {
        mutableStateOf(2)
    }
    val items = listOf(
        data4(
            routes = AppRoutes.TRANSFER_HOME,
            title = stringResource(R.string.home),
            SelectedIcon = painterResource(id = R.drawable.home),
            unselectedItem = painterResource(id = R.drawable.home)
        ),
        data4(
            routes = AppRoutes.TRANSFER_AMOUNT,
            title = stringResource(R.string.transfer),
            SelectedIcon = painterResource(id = R.drawable.transfer_figma),
            unselectedItem = painterResource(id = R.drawable.transfer_figma)
        ),
        data4(
            routes = "no",
            title = stringResource(R.string.transactions),
            SelectedIcon = painterResource(id = R.drawable.transaction_figma),
            unselectedItem = painterResource(id = R.drawable.transaction_figma)
        ),
        data4(
            routes = "my_cards",
            title = stringResource(R.string.my_cards),
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

    Scaffold(topBar = {
        TopAppBar(title = {
            Box(contentAlignment = Alignment.Center, modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp)) {
                Text(text = "Successful Transactions")
            }
        })
    }, bottomBar = {
        NavigationBar(modifier = modifier.clip(RoundedCornerShape(24.dp))) {
            items.forEachIndexed { index, data ->
                NavigationBarItem(
                    selected = selectedItem == index,
                    onClick = {
                        selectedItem = index
                        if (data.routes != "no"){
                        navController.navigate(data.routes)
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
    }, modifier = modifier.background(color = colorResource(id = R.color.main))) { innerpadding ->
        Box(modifier = modifier.padding(innerpadding)) {
            SuccesTransactionScreen()
        }

    }
}

@Composable
fun SuccesTransactionScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(state = ScrollState(1), true)
            .padding(horizontal = 16.dp, vertical = 11.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Image(
            painter = painterResource(id = R.drawable.iconcheckcorrect),
            contentDescription = null
        )
        Spacer(modifier = modifier.padding(16.dp))
        Row {
            Text(text = "1000 ", fontWeight = FontWeight.Bold, fontSize = 24.sp)
            Text(
                text = stringResource(R.string.usd),
                color = colorResource(id = R.color.reddd),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        }
        Row {
            Text(text = stringResource(R.string.transfer), fontSize = 16.sp)
            Text(text = "amount", fontSize = 16.sp)
        }
        Row {
            Text(text = stringResource(R.string.send), fontSize = 14.sp)
            Text(text = stringResource(R.string.money), fontSize = 14.sp, color = colorResource(id = R.color.reddd))
        }
        Spacer(modifier = modifier.padding(16.dp))


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
                        Image(
                            painter = painterResource(id = R.drawable.bank),
                            contentDescription = null
                        )
                        Spacer(modifier = modifier.padding(12.dp))
                        Column {
                            Text(text = stringResource(R.string.from))
                            Text(
                                text = "Asmaa dosuky",
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                modifier = modifier.padding(top = 16.dp)
                            )

                            Text(
                                text = "Account xxxx7890",
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
                            Text(text = stringResource(R.string.to))
                            Text(
                                text = "Asmaa dosuky",
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                modifier = modifier.padding(top = 16.dp)
                            )

                            Text(
                                text = "Account xxxx7890",
                                modifier = modifier.padding(top = 12.dp)
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
        Spacer(modifier = modifier.padding(16.dp))
        Card(
            modifier = modifier
                .fillMaxWidth()
                .height(150.dp)
        )
        {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            )
            {
                Text(text = "Transfer amount")
                Spacer(modifier = modifier.weight(1f))
                Text(text = "48.4220")
            }
            Divider(thickness = 1.dp, modifier = modifier.padding(8.dp))
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(10.dp)

            )
            {
                Text(text = "reference")
                Spacer(modifier = modifier.weight(1f))
                Text(text = "123456789876")
            }
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            )
            {
                Text(text = "Date")
                Spacer(modifier = modifier.weight(1f))
                Text(text = "20 jul 2024 7:50 PM")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun prevScreeeeen() {
    ScaffololdSuccessTransactionScreen(navController = rememberNavController())
}