package com.example.bm_app.transfer

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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
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
import com.example.bm_app.approutes.AppRoutes
import org.xmlpull.v1.sax2.Driver
data class data3 (val route :String, val title : String ,  val SelectedIcon : Painter, val unselectedItem : Painter)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun scaffoldConfirm(navController: NavController,modifier: Modifier = Modifier) {
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
            route = "",
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
        }
        , bottomBar = {
            NavigationBar(modifier = modifier.clip(RoundedCornerShape(24.dp))) {
                items.forEachIndexed { index, data ->
                    NavigationBarItem(
                        selected = selectedItem == index,
                        onClick = {
                            selectedItem = index
                            if (data.route != "no"){
                                navController.navigate(data.route)
                            }
                                  }, icon = { Icon(
                        painter = if (selectedItem == index) data.SelectedIcon else data.unselectedItem,
                        contentDescription = null
                    ) }, label = { Text(text = data.title, fontSize = 11.sp , textAlign = TextAlign.Center , maxLines = 1)})
                }
            }
        }
    )
    { innerpadding ->
        Box(modifier = modifier.padding(innerpadding)) {
            TransferConfirmation(navController = navController)
        }
    }
}

@Composable
fun TransferConfirmation(navController: NavController,modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp).verticalScroll(state = ScrollState(1) , true),
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
                            Text(text = "From")
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
        Spacer(modifier = modifier.padding(12.dp))
        Button(
            onClick = { navController.navigate(AppRoutes.TRANSFER_PAYMENT) },
            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.reddd)),
            modifier = modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(6.dp)

        ) {
            Text(text = "Confirm")
        }
        Spacer(modifier = modifier.padding(8.dp))
        Button(
            onClick = { navController.navigate(AppRoutes.TRANSFER_AMOUNT)},
            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.white)),
            modifier = modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(6.dp),
            border = BorderStroke(width = 0.5.dp, color = colorResource(id = R.color.reddd))

        ) {
            Text(text = "Previous", color = colorResource(id = R.color.reddd))
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun prevs() {
    //TransferConfirmation()
    scaffoldConfirm(navController = rememberNavController())
}