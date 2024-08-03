package com.example.bm_app.profile

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
import androidx.compose.foundation.shape.CircleShape
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

@Composable
fun ProfileScreen(navController: NavController, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFFFF5E1))
    ) {

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Profile",
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .fillMaxWidth()
                    .clickable { }
                    .padding(horizontal = 1.dp, vertical = 16.dp)
            ) {
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
                        text = "FL",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center
                    )

                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "First Last",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF24221E)
                )

            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .fillMaxWidth()
                    .clickable { }
                    .padding(horizontal = 1.dp, vertical = 16.dp)
            ) {
                Box(
                    modifier = modifier
                        .size(48.dp)
                        .clip(shape = RoundedCornerShape(8.dp))
                        .background(Color(0xFFF3E9EB))
                        ,
                    contentAlignment = Alignment.Center,
                )
                {
                    Icon(painter = painterResource(id = R.drawable.useframe),
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp),
                        tint = Color(0xFFCA0808)

                    )

                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = "Personal information",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF24221E)
                    )
                    Spacer(modifier = Modifier.padding(1.dp))
                    Text(
                        text = "Your information",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF898886)
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    painter = painterResource(id = R.drawable.chevron),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = Color(0xFF666666)
                )

            }
            HorizontalDivider(thickness = 1.dp, color = Color(0xFFDDD5EB))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .fillMaxWidth()
                    .clickable { }
                    .padding(horizontal = 1.dp, vertical = 16.dp)
            ) {
                Box(
                    modifier = modifier
                        .size(48.dp)
                        .clip(shape = RoundedCornerShape(8.dp))
                        .background(Color(0xFFF3E9EB))
                    ,
                    contentAlignment = Alignment.Center,
                )
                {
                    Icon(painter = painterResource(id = R.drawable.setting),
                        contentDescription = null,
                        modifier = Modifier
                            .size(22.dp),
                        tint = Color(0xFFCA0808)

                    )

                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = "Setting",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF24221E)
                    )
                    Spacer(modifier = Modifier.padding(1.dp))
                    Text(
                        text = "Change your settings",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF898886)
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    painter = painterResource(id = R.drawable.chevron),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = Color(0xFF666666)
                )
            }
            HorizontalDivider(thickness = 1.dp, color = Color(0xFFDDD5EB))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .fillMaxWidth()
                    .clickable { }
                    .padding(horizontal = 1.dp, vertical = 16.dp)
            ) {
                Box(
                    modifier = modifier
                        .size(48.dp)
                        .clip(shape = RoundedCornerShape(8.dp))
                        .background(Color(0xFFF3E9EB))
                    ,
                    contentAlignment = Alignment.Center,
                )
                {
                    Icon(painter = painterResource(id = R.drawable.history1),
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp),
                        tint = Color(0xFFCA0808)

                    )

                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = "Payment history",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF24221E)
                    )
                    Spacer(modifier = Modifier.padding(1.dp))
                    Text(
                        text = "view your transactions",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF898886)
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    painter = painterResource(id = R.drawable.chevron),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = Color(0xFF666666)
                )
            }
            HorizontalDivider(thickness = 1.dp, color = Color(0xFFDDD5EB))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .fillMaxWidth()
                    .clickable { }
                    .padding(horizontal = 1.dp, vertical = 16.dp)
            ) {
                Box(
                    modifier = modifier
                        .size(48.dp)
                        .clip(shape = RoundedCornerShape(8.dp))
                        .background(Color(0xFFF3E9EB))
                    ,
                    contentAlignment = Alignment.Center,
                )
                {
                    Icon(painter = painterResource(id = R.drawable.favorite_star),
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp),
                        tint = Color(0xFFCA0808)

                    )

                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = "My Favourite list",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF24221E)
                    )
                    Spacer(modifier = Modifier.padding(1.dp))
                    Text(
                        text = "view your favourites",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF898886)
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    painter = painterResource(id = R.drawable.chevron),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = Color(0xFF666666)
                )
            }

        }
    }
}

@Composable
fun ScaffoldProfileMain(navController: NavController, modifier: Modifier = Modifier) {
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
            ProfileScreen(navController = navController )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Show() {
    ScaffoldProfileMain(navController = rememberNavController())
}

