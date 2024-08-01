package com.example.bm_app.transfer

import androidx.annotation.DrawableRes
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
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
import com.example.bm_app.signinscreen.SigninScreen

data class Navi (val route : String ,val title : String ,  val SelectedIcon : Painter, val unselectedItem : Painter)
@Composable
fun ScaffoldtransMain( navController: NavController,modifier: Modifier = Modifier)
{
    var selectedItem by rememberSaveable {
        mutableStateOf(0)
    }
    val items = listOf(
        Navi(
            route = AppRoutes.TRANSFER_HOME,
            title = "Home",
            SelectedIcon = painterResource(id = R.drawable.home),
            unselectedItem = painterResource(id = R.drawable.home)
        ),
        Navi(
            route = AppRoutes.TRANSFER_AMOUNT,
            title = "Tranfer",
            SelectedIcon = painterResource(id = R.drawable.transfer_figma),
            unselectedItem = painterResource(id = R.drawable.transfer_figma)
        ),
        Navi(
            route="",
            title = "Transactions",
            SelectedIcon = painterResource(id = R.drawable.transaction_figma),
            unselectedItem = painterResource(id = R.drawable.transaction_figma)
        ),
        Navi(
            route = "",
            title = "My cards",
            SelectedIcon = painterResource(id = R.drawable.cards_figma),
            unselectedItem = painterResource(id = R.drawable.cards_figma)
        ),
        Navi(
            route = "",
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
                        navController.navigate(item.route)
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
            TransferHome()
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransferHome( modifier: Modifier = Modifier) {
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
                .padding(8.dp)
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
        Spacer(modifier = modifier.padding(8.dp))
        Card(
            modifier = modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(horizontal = 8.dp)
                .border(0.05.dp, Color.Yellow),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        )
        {
            Row (
                verticalAlignment  = Alignment.CenterVertically,

                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp))
            {
                Spacer(modifier = modifier.padding(horizontal = 12.dp))
                Text(text = "Services")
            }
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(40.dp)){
                IconText(icon = painterResource(id = R.drawable.home), text = "Transfer")
                Spacer(modifier = modifier.padding(16.dp))
                IconText(icon = painterResource(id = R.drawable.transaction_figma), text ="Transaction" )
                Spacer(modifier = modifier.padding(16.dp))
                IconText(icon = painterResource(id = R.drawable.cards_figma), text ="Cards")
                Spacer(modifier = modifier.padding(16.dp))
                IconText(icon = painterResource(id = R.drawable.ic_account_balance), text ="Cards")

            }
        }
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
