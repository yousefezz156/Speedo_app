package com.example.bm_app.more

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bm_app.R
import com.example.bm_app.transfer.Navi
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.bm_app.list.favoriteList
import com.example.bm_app.model.Favorite

@Composable
fun FavoriteScreen(navController: NavController, modifier: Modifier = Modifier, favourites: List<Favorite>) {
    var favouritesState by remember { mutableStateOf(favourites) }
    var selectedItem by remember { mutableStateOf<Favorite?>(null) }
    var isDialogOpen by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFFEF0EA))
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.favourite),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 1.dp, vertical = 8.dp)
            ) {
                Text(
                    text = stringResource(R.string.your_favourite_list),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF24221E)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn {
                items(favouritesState) { favourite ->
                    FavoriteScreenItem(
                        iconRes = R.drawable.group13,
                        name = favourite.name,
                        account = favourite.account,
                        onEditClick = {
                            selectedItem = favourite
                            isDialogOpen = true
                        },
                        onDeleteClick = {
                            favouritesState = favouritesState.filter { it != favourite }
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            if (isDialogOpen) {
                selectedItem?.let { favorite ->
                    EditFavorite(
                        name = favorite.name,
                        account = favorite.account,
                        onDismiss = { isDialogOpen = false },
                        onSave = { newName, newAccount ->
                            favouritesState = favouritesState.map {
                                if (it == favorite) it.copy(name = newName, account = newAccount) else it
                            }
                            isDialogOpen = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun FavoriteScreenItem(
    iconRes: Int,
    name: String,
    account: String,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
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
            painter = painterResource(id = iconRes),
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
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            painter = painterResource(id = R.drawable.edit),
            contentDescription = null,
            modifier = Modifier
                .size(24.dp)
                .clickable { onEditClick() },
            tint = Color(0xFF666666)
        )
        Spacer(modifier = Modifier.width(18.dp))
        Icon(
            painter = painterResource(id = R.drawable.delete),
            contentDescription = null,
            modifier = Modifier
                .size(24.dp)
                .clickable { onDeleteClick() },
            tint = Color(0xFFCA0808)
        )
    }
}

@Composable
fun ScaffoldFav(navController: NavController, modifier: Modifier = Modifier) {
    var selectedItem by rememberSaveable {
        mutableStateOf(4)
    }
    val items = listOf(
        Navi(
            route = "home",
            title = stringResource(R.string.home),
            SelectedIcon = painterResource(id = R.drawable.home),
            unselectedItem = painterResource(id = R.drawable.home)
        ),
        Navi(
            route = "transfer",
            title = stringResource(R.string.transfer),
            SelectedIcon = painterResource(id = R.drawable.transfer_figma),
            unselectedItem = painterResource(id = R.drawable.transfer_figma)
        ),
        Navi(
            route = "transactions",
            title = stringResource(R.string.transactions),
            SelectedIcon = painterResource(id = R.drawable.transaction_figma),
            unselectedItem = painterResource(id = R.drawable.transaction_figma)
        ),
        Navi(
            route = "my_cards",
            title = stringResource(R.string.my_cards),
            SelectedIcon = painterResource(id = R.drawable.cards_figma),
            unselectedItem = painterResource(id = R.drawable.cards_figma)
        ),
        Navi(
            route = "no",
            title = stringResource(R.string.more),
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
            FavoriteScreen(navController = navController, favourites = favoriteList().getFavoriteList() )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditFavorite(
    name: String,
    account: String,
    onDismiss: () -> Unit,
    onSave: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    var newName by rememberSaveable { mutableStateOf(name) }
    var newAccount by rememberSaveable { mutableStateOf(account) }

    Box(
        modifier = modifier
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        ModalBottomSheet(onDismissRequest = onDismiss) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.edit),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = Color(0xFF871E35)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = stringResource(R.string.edit))
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text(text = stringResource(R.string.recipient_account), fontSize = 16.sp)
                Spacer(modifier = Modifier.padding(4.dp))
                OutlinedTextField(
                    value = newAccount,
                    onValueChange = { newAccount = it },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp)
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text(text = stringResource(R.string.recipient_name), fontSize = 16.sp)
                Spacer(modifier = Modifier.padding(4.dp))
                OutlinedTextField(
                    value = newName,
                    onValueChange = { newName = it },
                    placeholder = { Text(stringResource(R.string.enter_cardholder_name)) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp)
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = { onSave(newName, newAccount) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .size(56.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.reddd)
                )
            ) {
                Text(text = stringResource(R.string.save))
            }
            Spacer(modifier = Modifier.height(163.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Show() {
    ScaffoldFav(navController = rememberNavController())
}
