package com.example.bm_app.more

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bm_app.R
import com.example.bm_app.approutes.AppRoutes
import com.example.bm_app.transfer.Navi
import javax.security.auth.Subject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoreScreen(navController: NavController, modifier: Modifier = Modifier) {
    var isHelpOpen by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current
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
                destination = "no",
                onItemClick ={
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.banquemisr.com/"))
                    context.startActivity(intent)
                }
            )
            HorizontalDivider(thickness = 1.dp, color = Color(0xFFDDD5EB))
            MoreScreenItem(
                iconRes = R.drawable.favorite,
                text = "Favourites",
                navController = navController,
                destination = "more_fav",
                onItemClick ={}
            )
            HorizontalDivider(color = Color(0xFFDDD5EB), thickness = 1.dp)
            MoreScreenItem(
                iconRes = R.drawable.user,
                text = "Profile",
                navController = navController,
                destination = "more_profile",
                onItemClick ={}
            )
            HorizontalDivider(color = Color(0xFFDDD5EB), thickness = 1.dp)
            MoreScreenItem(
                iconRes = R.drawable.fill,
                text = "Help",
                navController = navController,
                destination = "no",
                onItemClick ={isHelpOpen = true}
            )
            HorizontalDivider(color = Color(0xFFDDD5EB), thickness = 1.dp)
            MoreScreenItem(
                iconRes = R.drawable.logout,
                text = "Logout",
                navController = navController,
                destination = "signin",
                onItemClick ={}
            )
        }
    }
    if (isHelpOpen) {
        ModalBottomSheet(onDismissRequest = { isHelpOpen = false }){
            Spacer(modifier = Modifier.height(55.dp))
            Row(){
                Box(
                    modifier = modifier
                        .size(170.dp,170.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(start = 84.dp),
                        horizontalAlignment = Alignment.CenterHorizontally

                    ) {
                        Box(
                            modifier = modifier
                                .size(55.dp)
                                .clip(shape = RoundedCornerShape(6.dp))
                                .background(Color(0xFFF3E9EB))
                                .padding(horizontal = 8.dp)
                                .clickable {
                                    context.sendEmail("ahmed6@gmail.com", "Test")
                                }
                            ,
                            contentAlignment = Alignment.Center,
                        )
                        {
                            Icon(painter = painterResource(id = R.drawable.email),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(24.dp),
                                tint = Color(0xFFCA0808)

                            )
                        }
                        Spacer(modifier = Modifier.height(14.84.dp))
                        Text(text = "Send Email", fontSize =14.sp)
                    }
                }
                Box(
                    modifier = modifier
                        .size(170.dp,170.dp)
                ) {
                    Column(modifier = Modifier.padding(start = 84.dp ),
                        horizontalAlignment = Alignment.CenterHorizontally

                    ) {
                        Box(
                            modifier = modifier
                                .size(55.dp)
                                .clip(shape = RoundedCornerShape(6.dp))
                                .background(Color(0xFFF3E9EB))
                                .padding(horizontal = 8.dp)
                            ,
                            contentAlignment = Alignment.Center,
                        )
                        {
                            Icon(painter = painterResource(id = R.drawable.call),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(24.dp)
                                    .clickable {
                                        val i = Intent(Intent.ACTION_DIAL, "tel: 19888".toUri())
                                        context.startActivity(i)
                                    },
                                tint = Color(0xFFCA0808)

                            )
                        }
                        Spacer(modifier = Modifier.height(14.84.dp))
                        Text(text = "Call Us", fontSize =14.sp)
                        Text(text = "19888", fontSize =14.sp, color = Color(0xFF871E35))
                    }
                }
            }
            Spacer(modifier = Modifier.height(55.dp))
        }
    }
}

fun Context.sendEmail(to: String, subject: String){
    val intent  = Intent(Intent.ACTION_SEND)
    intent.type = "vdn.android.cursor.item/email"
    intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(to))
    intent.putExtra(Intent.EXTRA_SUBJECT,subject)
    startActivity(intent)

}


@Composable
fun MoreScreenItem(
    iconRes: Int,
    text: String,
    navController: NavController,
    onItemClick: () -> Unit,
    destination: String,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                if (destination != "no"  ) {
                    navController.navigate(destination)
                }
                onItemClick()
            }
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
        if (text != "Logout"){
            Icon(
                painter = painterResource(id = R.drawable.chevron),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = Color(0xFF666666)
            )
        }

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
            route = "my_cards",
            title = "My cards",
            SelectedIcon = painterResource(id = R.drawable.cards_figma),
            unselectedItem = painterResource(id = R.drawable.cards_figma)
        ),
        Navi(
            route = "no",
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
            MoreScreen(navController = navController )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Show() {
    ScaffoldMoreMain(navController = rememberNavController())
}

