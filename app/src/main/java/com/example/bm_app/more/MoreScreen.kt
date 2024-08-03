package com.example.bm_app.more

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.HorizontalDivider
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

@Composable
fun MoreScreen(navController: NavController, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFFFF5E1))
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "More",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
        ) {
            MoreScreenItem(
                iconRes = R.drawable.group,
                text = "Transfer From Website",
                navController = navController,
                destination = "transfer"
            )
            HorizontalDivider(thickness = 1.dp, color = Color(0xFFDDD5EB))
            MoreScreenItem(
                iconRes = R.drawable.favorite,
                text = "Favourites",
                navController = navController,
                destination = "favourites"
            )
            HorizontalDivider(color = Color(0xFFDDD5EB), thickness = 1.dp)
            MoreScreenItem(
                iconRes = R.drawable.user,
                text = "Profile",
                navController = navController,
                destination = "profile"
            )
            HorizontalDivider(color = Color(0xFFDDD5EB), thickness = 1.dp)
            MoreScreenItem(
                iconRes = R.drawable.fill,
                text = "Help",
                navController = navController,
                destination = "help"
            )
            HorizontalDivider(color = Color(0xFFDDD5EB), thickness = 1.dp)
            MoreScreenItem(
                iconRes = R.drawable.logout,
                text = "Logout",
                navController = navController,
                destination = "logout"
            )
        }
    }
}

@Composable
fun MoreScreenItem(
    iconRes: Int,
    text: String,
    navController: NavController,
    destination: String,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .clickable { navController.navigate(destination) }
            .padding(horizontal = 1.dp, vertical = 16.dp)
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color(0xFF666666)
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            painter = painterResource(id = R.drawable.chevron),
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = Color(0xFF666666)
        )
    }
}

@Composable
fun ScaffoldMoreMain(navController: NavController, modifier: Modifier = Modifier) {
    var selectedItem by rememberSaveable {
        mutableStateOf(4)
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
            route = "cards",
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
                        navController.navigate(item.route)
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
            MoreScreen(navController = navController )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Show() {
    ScaffoldMoreMain(navController = rememberNavController())
}

