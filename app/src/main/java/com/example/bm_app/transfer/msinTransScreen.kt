package com.example.bm_app.transfer

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bm_app.R
import com.example.bm_app.approutes.AppRoutes
import com.example.bm_app.buttonbar.Screen
import com.example.bm_app.mycard.lazycol
import com.example.bm_app.signinscreen.SigninScreen
import com.example.bm_app.transaction.IconandBackground

data class Navi (val route : String ,val title : String ,  val SelectedIcon : Painter, val unselectedItem : Painter)
@Composable
fun ScaffoldtransMain( navController: NavController,modifier: Modifier = Modifier)
{
    var selectedItem by rememberSaveable {
        mutableStateOf(0)
    }
    val items = listOf(
        Navi(
            route = "no",
            title = "Home",
            SelectedIcon = painterResource(id = R.drawable.home),
            unselectedItem = painterResource(id = R.drawable.home)
        ),
        Navi(
            route = "transfer",
            title = "Tranfer",
            SelectedIcon = painterResource(id = R.drawable.transfer_figma),
            unselectedItem = painterResource(id = R.drawable.transfer_figma)
        ),
        Navi(
            route="transactions",
            title = "Transactions",
            SelectedIcon = painterResource(id = R.drawable.transaction_figma),
            unselectedItem = painterResource(id = R.drawable.transaction_figma)
        ),
        Navi(
            route = "my_cards",
            title = "My cards",
            SelectedIcon = painterResource(id = R.drawable.cards_figma),
            unselectedItem = painterResource(id = R.drawable.cards_figma)
        ),
        Navi(
            route = "more",
            title = "More",
            SelectedIcon = painterResource(id = R.drawable.more),
            unselectedItem = painterResource(id = R.drawable.more)
        )
    )
    Scaffold(bottomBar = {
        NavigationBar(modifier = modifier.clip(RoundedCornerShape(24.dp))) {
            items.forEachIndexed { index, item ->
                NavigationBarItem(
                    selected = selectedItem == index,
                    onClick = {
                        selectedItem = index
                        if (item.route != "no"){
                            navController.navigate(item.route)
                        }
                              },
                    icon = { Icon(
                    painter = if (selectedItem == index) item.SelectedIcon else item.unselectedItem,
                    contentDescription = null
                ) }, label = { Text(text = item.title, fontSize = 11.sp , textAlign = TextAlign.Center , maxLines = 1)})
            }
        }
    })
    { innerpadding ->
        Box(modifier = modifier.padding(innerpadding)){
            TransferHome(navController)
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransferHome(navController: NavController, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.purple_200))
    ) {
        Spacer(modifier = modifier.padding(8.dp))
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Spacer(modifier = modifier.padding(8.dp))
            IconChange()
            Spacer(modifier = modifier.padding(8.dp))
            Column {
                Text(text = "welcome  back,")
                Spacer(modifier = modifier.padding(4.dp))
                Text(text = "yousef", modifier.padding(horizontal = 4.dp))
            }

            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_notifications),
                        contentDescription = null
                    )
                }
            }


        }
        Spacer(modifier = modifier.padding(8.dp))
        Card(
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.reddd)),
            modifier = modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(all = 16.dp)
        )
        {
            Spacer(modifier = modifier.padding(top = 32.dp))
            Text(
                text = "Current balnace",
                color = Color.White,
                modifier = modifier.padding(horizontal = 8.dp)
            )
            Spacer(modifier = modifier.padding(8.dp))
            Text(
                text = "$2,85,856.80",
                fontSize = 28.sp,
                color = Color.White,
                modifier = modifier.padding(horizontal = 8.dp)
            )
        }

        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(horizontal = 16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxSize()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    Spacer(modifier = modifier.padding(horizontal = 10.dp))
                    Text(text = "Services")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                ) {
                    ServiceItem(
                        iconId = R.drawable.transfer1,
                        label = "Transfer",
                        onClick = {navController.navigate("transfer")}
                    )
                    ServiceItem(
                        iconId = R.drawable.history11,
                        label = "Transactions",
                        onClick = {navController.navigate("transactions")}
                    )
                    ServiceItem(
                        iconId = R.drawable.card1s,
                        label = "Cards",
                        onClick = {navController.navigate("my_cards")}
                    )
                    ServiceItem(
                        iconId = R.drawable.account1,
                        label = "Account",
                        onClick = {navController.navigate("more")}
                    )
                }
            }
        }
        Column {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFBF6E8))
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 2.dp)
                ) {
                    Text(text = "Recent transactions", fontSize = 16.sp)
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "View all",
                        fontSize = 16.sp,
                        modifier = Modifier.clickable { navController.navigate("transactions") },
                        color = Color(0xFF7C7A78)
                    )
                }
            }

            val transactions = listOf(
                "Ahmed Mohamed" to "1234",
                "Ahmed Mohamed" to "1234",
                "Ahmed Mohamed" to "1234",
                "Ahmed Mohamed" to "1234",
                "Ahmed Mohamed" to "1234"
            )

            LazyColumn {
                items(transactions) { transaction ->
                    TransactionItem(
                        name = transaction.first,
                        accountNumber = transaction.second,
                        accountType = "Visa",
                        date = "Today 11:00",
                        transactionType = "Received",
                        amount = 1000,
                        onClick = {},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
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
    onClick: () -> Unit,
    modifier: Modifier
) {
    Card(
        modifier = modifier
            .background(color = Color.White)
            .fillMaxWidth().clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(8.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFF3E9EB)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.group7),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = Color.Unspecified
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = name, fontWeight = FontWeight.Bold)
                Text(
                    text = "$accountType . $accountNumber",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 4.dp)
                )
                Text(
                    text = "$date - $transactionType",
                    modifier = Modifier.padding(top = 4.dp),
                    color = Color.Gray
                )
            }
            Text(
                text = "$$amount",
                fontWeight = FontWeight.Bold,
                color = Color(0xFF871E35),
                modifier = Modifier.padding(start = 12.dp)
            )
        }
    }
}

@Composable
fun ServiceItem(iconId: Int, label: String, onClick : ()->Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(horizontal = 6.dp)
            .clickable { onClick() }
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(shape = RoundedCornerShape(8.dp))
                .background(Color(0xFFFBFBFB)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = iconId),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = Color(0xFF9F7815)
            )
        }
        Spacer(modifier = Modifier.height(2.dp))
        Text(text = label, fontSize = 14.sp)
    }
}

@Composable
fun IconText(icon: Painter, text: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .clickable { },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = icon,
            contentDescription =null,
            )

        Text(text = text , textAlign = TextAlign.Center, overflow = TextOverflow.Ellipsis)

    }
}


@Composable
fun IconChange(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(48.dp)
            .clip(CircleShape)
            .background(
                colorResource(id = R.color.gry)
            ),
        contentAlignment = Alignment.Center

    )
    {
        Text(
            text = "AD",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )

    }
}

@Preview(showBackground = true)
@Composable
private fun Show() {
    ScaffoldtransMain(navController = rememberNavController())

}

//import androidx.compose.foundation.Image
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.*
//import androidx.compose.material3.Card
//import androidx.compose.material3.CardColors
//import androidx.compose.material3.Icon
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.colorResource
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.example.bm_app.R

//import com.example.app.ui.theme.MyAppTheme

/*
@Composable
fun BankingAppUI() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        Header()
        BalanceCard()
        ServicesSection()
        RecentTransactionsSection()

    }
}

@Composable
fun Header() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Welcome back,", fontSize = 20.sp)
        Text(text = "Asmaa Dosuky", fontSize = 20.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun BalanceCard() {
    Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardColors(containerColor = Color),

        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(100.dp)
            .background(Color.Red)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Current Balance", color = Color.White, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "$2,85,856.20", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun ServicesSection() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Services", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            ServiceItem(iconResId = R.drawable.baseline_payment_24, label = "Transfer")
            ServiceItem(iconResId = R.drawable.ic_receipt, label = "Transactions")
            ServiceItem(iconResId = R.drawable.ic_menu_book, label = "Cards")
            ServiceItem(iconResId = R.drawable.ic_account_balance, label = "Account")
        }
    }
}

@Composable
fun ServiceItem(iconResId: Int, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(painter = painterResource(id = iconResId), contentDescription = label, modifier = Modifier.size(40.dp))
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = label, fontSize = 12.sp)
    }
}

@Composable
fun RecentTransactionsSection() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Recent transactions", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        TransactionItem(name = "Ahmed Mohamed", amount = "$1000", cardDetails = "Visa . Meter Card . 1234", time = "Today 11:00 - Received")
        TransactionItem(name = "Ahmed Mohamed", amount = "$1000", cardDetails = "Visa . Meter Card . 1234", time = "Today 11:00 - Received")
    }
}

@Composable
fun TransactionItem(name: String, amount: String, cardDetails: String, time: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Icon(painter = painterResource(id = R.drawable.baseline_payment_24), contentDescription = null, modifier = Modifier.size(40.dp))
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(text = name, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(text = cardDetails, fontSize = 14.sp, color = Color.Gray)
            Text(text = time, fontSize = 12.sp, color = Color.Gray)
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(text = amount, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Green)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
        BankingAppUI()

}
*/
