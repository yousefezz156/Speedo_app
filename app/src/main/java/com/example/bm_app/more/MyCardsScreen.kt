package com.example.bm_app.more
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bm_app.R
import com.example.bm_app.transfer.Navi
import androidx.compose.foundation.lazy.items



@Composable
fun MyCardsScreen(navController: NavController, modifier: Modifier = Modifier) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFFFF5E1))
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "My Cards",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
        ) {

            // Example list of favourite items
            val Accounts = listOf(
                listOf("Ahmed Rasshed" , "Account xxxx7890" , true),
                listOf("Ahmed Fathy" , "Account xxxx7890" , false),
                listOf("Ahmed Rasshed" , "Account xxxx7890" , false),
                listOf("Ahmed Fathy" , "Account xxxx7890" , false),
                listOf("Ahmed Rasshed" , "Account xxxx7890" , false),
                listOf("Ahmed Fathy" , "Account xxxx7890" , false),
                listOf("Ahmed Rasshed" , "Account xxxx7890" , false),
                listOf("Ahmed Fathy" , "Account xxxx7890" , true)
            )

            LazyColumn {
                items(Accounts){fav->
                    CardItem(
                        name = fav[0].toString(),
                        account = fav[1].toString(),
                        default = fav[2] as Boolean
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                }
            }
            Button(
                onClick = {},
                shape = RoundedCornerShape(6.dp),
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.reddd))
            )
            {
                Text(text = "Add New Account", fontSize = 16.sp)
            }
        }
    }
}

@Composable
fun CardItem(
    name: String,
    account: String,
    default: Boolean,
    modifier: Modifier = Modifier

) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .background(Color(0xFFF3E9EB), shape = RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.group13),
            contentDescription = null,
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Row{
                Text(
                    text = name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color(0xFF24221E),
                )
                Spacer(modifier = Modifier.weight(1f))
                if (default) {
                    Box(
                        modifier = modifier
                            .size(44.dp, 15.dp)
                            .clip(shape = RoundedCornerShape(5.35.dp))
                            .background(Color(0xFFE3E2E2)),
                        contentAlignment = Alignment.Center,
                    )
                    {
                        Text(text = "Default", fontSize = 9.34.sp)

                    }
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = account,
                fontSize = 14.sp,
                color = Color(0xFF666666)
            )
        }
    }
}


@Composable
fun ScaffoldMyCardsMain(navController: NavController, modifier: Modifier = Modifier) {
    var selectedItem by rememberSaveable {
        mutableStateOf(3)
    }
    val items = listOf(
        Navi(
            route = "home",
            title = "Home",
            SelectedIcon = painterResource(id = R.drawable.home),
            unselectedItem = painterResource(id = R.drawable.home)
        ),
        Navi(
            route = "transfer",
            title = "Transfer",
            SelectedIcon = painterResource(id = R.drawable.transfer_figma),
            unselectedItem = painterResource(id = R.drawable.transfer_figma)
        ),
        Navi(
            route = "transactions",
            title = "Transactions",
            SelectedIcon = painterResource(id = R.drawable.transaction_figma),
            unselectedItem = painterResource(id = R.drawable.transaction_figma)
        ),
        Navi(
            route = "no",
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
                    icon = {
                        Icon(
                            painter = if (selectedItem == index) item.SelectedIcon else item.unselectedItem,
                            contentDescription = null
                        )
                    },
                    label = {
                        Text(
                            text = item.title,
                            fontSize = 11.sp,
                            textAlign = TextAlign.Center,
                            maxLines = 1
                        )
                    }
                )
            }
        }
    }) { innerPadding ->
        Box(modifier = modifier.padding(innerPadding)) {
            MyCardsScreen(navController = navController )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun Show() {
    ScaffoldMyCardsMain(navController = rememberNavController())
}

