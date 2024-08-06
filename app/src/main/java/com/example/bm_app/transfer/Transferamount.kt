package com.example.bm_app.transfer

import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bm_app.R
import com.example.bm_app.approutes.AppRoutes
import com.example.bm_app.approutes.AppRoutes.TRANSFER_CONFIRMATION
import com.example.bm_app.list.favoriteList
import com.example.bm_app.model.Favorite
import javax.sql.DataSource

data class data2(
    val route: String,
    val title: String,
    val SelectedIcon: Painter,
    val unselectedItem: Painter
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Scaffold_Transfer(navController: NavController, modifier: Modifier = Modifier) {
    var selectedItem by rememberSaveable {
        mutableStateOf(1)
    }
    val items = listOf(
        data2(
            route = "home",
            title = stringResource(R.string.home),
            SelectedIcon = painterResource(id = R.drawable.home),
            unselectedItem = painterResource(id = R.drawable.home)
        ),
        data2(
            route = "no",
            title = stringResource(R.string.transfer),
            SelectedIcon = painterResource(id = R.drawable.transfer_figma),
            unselectedItem = painterResource(id = R.drawable.transfer_figma)
        ),
        data2(
            route = "transactions",
            title = stringResource(R.string.transactions),
            SelectedIcon = painterResource(id = R.drawable.transaction_figma),
            unselectedItem = painterResource(id = R.drawable.transaction_figma)
        ),
        data2(
            route = "my_cards",
            title = stringResource(R.string.my_cards),
            SelectedIcon = painterResource(id = R.drawable.cards_figma),
            unselectedItem = painterResource(id = R.drawable.cards_figma)
        ),
        data2(
            route = "more",
            title = stringResource(R.string.more),
            SelectedIcon = painterResource(id = R.drawable.more),
            unselectedItem = painterResource(id = R.drawable.more)
        )
    )
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                },
                title = {
                    Box(modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Text(text = stringResource(R.string.transfer), textAlign = TextAlign.Center)
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
            TransferPage(navController, favorite = favoriteList().getFavoriteList())

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransferPage(
    navController: NavController,
    modifier: Modifier = Modifier,
    favorite: List<Favorite>
) {

    var from by remember {
        mutableStateOf("0")
    }
    var where by remember {
        mutableStateOf("48332.50")
    }
    var recipientName by remember {
        mutableStateOf("")
    }
    var recipientAccount by remember {
        mutableStateOf("")
    }
    var showpicker by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(state = ScrollState(1), true)
    )
    {
        if (showpicker) {
            ModalBottomSheet(onDismissRequest = { showpicker = false }) {
                favorite.forEach { favoriteItem ->
                    Card(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        colors = CardDefaults.cardColors(Color(0xFFF3E9EB))
                    ) {
                        FavoriteScreenItem(
                            name = favoriteItem.name,
                            account = favoriteItem.account,
                            onFavoriteClick = {
                                recipientName = favoriteItem.name
                                recipientAccount = favoriteItem.account
                                showpicker = false
                            }
                        )
                    }
                }
            }
        }
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        )
        {
            stepintext(
                painter = painterResource(id = R.drawable.step_text___horizontal),
                text = stringResource(R.string.amount)
            )
            stepintext(painter = painterResource(id = R.drawable.step_trail), text = "")
            stepintext(
                painter = painterResource(id = R.drawable.step_text_2_gray),
                text = stringResource(R.string.confirmation)
            )
            stepintext(painter = painterResource(id = R.drawable.step_trail_gray), text = "")
            stepintext(painter = painterResource(id = R.drawable.step_text_2_gray), text = stringResource(R.string.amount))

        }
        Spacer(modifier = modifier.padding(16.dp))
        Row(
            modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(text = stringResource(R.string.how_much_are_you_sending), fontSize = 20.sp)

        }
        Spacer(modifier = modifier.padding(16.dp))
        Row(
            modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(text = stringResource(R.string.choose_currency))
        }
        Card(
            colors = CardDefaults.cardColors(Color.White),
            modifier = modifier
                .height(340.dp)
                .padding(8.dp)
                .fillMaxSize()
        )
        {
            Text(text = "1 USD = 48.4220", modifier.padding(8.dp))
            Text(
                text = "Rate guaranteed (2)",
                modifier.padding(12.dp),
                color = colorResource(id = R.color.gry)
            )
            Text(text = stringResource(R.string.you_send), modifier.padding(8.dp))
            Row(modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.united_states),
                    contentDescription = null
                )
                Text(text = "USD", modifier.padding(8.dp))
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Filled.KeyboardArrowDown, contentDescription = null)
                }
                Row(horizontalArrangement = Arrangement.End, modifier = modifier.fillMaxWidth())
                {
                    OutlinedTextField(
                        value = from,
                        onValueChange = { from.toInt() },
                        modifier = modifier.size(height = 56.dp, width = 160.dp)
                    )
                }


            }
            Spacer(modifier = modifier.padding(8.dp))
            Divider(color = Color.Black, thickness = 0.5.dp)
            Spacer(modifier = modifier.padding(8.dp))
            Text(text = stringResource(R.string.recipient_gets))

            Row(modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {

                Image(
                    painter = painterResource(id = R.drawable.united_states),
                    contentDescription = null
                )
                Text(text = stringResource(R.string.usd), modifier.padding(8.dp))
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Filled.KeyboardArrowDown, contentDescription = null)
                }
                Row(horizontalArrangement = Arrangement.End, modifier = modifier.fillMaxWidth())
                {
                    OutlinedTextField(
                        value = from,
                        onValueChange = {from.toInt()} ,
                        modifier = modifier.size(height = 56.dp, width = 160.dp)
                    )
                }


            }


        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.fillMaxWidth()
        )
        {
            // Spacer(modifier = modifier.padding(24.dp))
            Text(
                text = stringResource(R.string.recipient_information),
                modifier.padding(8.dp),
                textAlign = TextAlign.Center
            )
            Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                TextButton(onClick = { showpicker = true }) {
                    Icon(
                        painter = painterResource(id = R.drawable.favorite_star),
                        contentDescription = null
                    )
                    Text(text = "Favorite")
                    Icon(Icons.Filled.KeyboardArrowRight, contentDescription = null)
                }
            }

        }
        Text(text = stringResource(R.string.recipient_name), modifier.padding(8.dp))
        OutlinedTextField(
            value = recipientName,
            onValueChange = { recipientName = it },
            modifier
                .fillMaxWidth()
                .padding(8.dp), placeholder = {
                Text(
                    text = stringResource(R.string.enter_recipient_name)
                )
            },keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text))
        Text(text = stringResource(R.string.recipient_account), modifier.padding(8.dp))
        OutlinedTextField(
            value = recipientAccount,
            onValueChange = { recipientAccount = it },
            modifier
                .fillMaxWidth()
                .padding(8.dp),
            placeholder = { Text(text = stringResource(R.string.enter_recipient_account_number)) }
        ,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text))

        Button(onClick = {
            if (recipientName.isNotEmpty() && !recipientAccount.isEmpty() && from.toInt() <= 5000) {
                navController.navigate("${TRANSFER_CONFIRMATION}/$recipientName/$recipientAccount")
            } else if (recipientName.isBlank() && recipientAccount.isBlank()) {
                Toast.makeText(context,
                    context.getString(R.string.please_enter_respie), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(
                    context,
                    context.getString(R.string.you_cant_send_more_than_5000_l_e_per_transaction),
                    Toast.LENGTH_SHORT
                ).show()
            }
        },
            modifier
                .fillMaxWidth()
                .padding(8.dp), shape = RoundedCornerShape(4.dp),
            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.reddd))) {
            Text(text = "Continue", color = Color.White)
        }
    }


}

@Composable
fun stepintext(painter: Painter, text: String, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(painter = painter, contentDescription = null)
        Spacer(modifier = modifier.padding(16.dp))
        Text(text = text, textAlign = TextAlign.Center)
    }
}

@Composable
fun FavoriteScreenItem(
    name: String,
    account: String,
    onFavoriteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .background(Color(0xFFF3E9EB), shape = RoundedCornerShape(16.dp))
            .clickable { onFavoriteClick() }
    ) {
        Image(
            painter = painterResource(id = R.drawable.group13),
            contentDescription = null,
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = name,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color(0xFF24221E)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Account xxxx${account.takeLast(4)}",
                fontSize = 14.sp,
                color = Color(0xFF666666)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun prevScreen() {
    // TransferPage(favorite = favoriteList().getFavoriteList())
    Scaffold_Transfer(rememberNavController())
}