package com.example.bm_app.more
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
fun FavoriteScreen(navController: NavController, modifier: Modifier = Modifier) {
    var isDialogOpen by rememberSaveable { mutableStateOf(false) }
    var selectedItem by rememberSaveable { mutableStateOf<Pair<String, String>?>(null) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFFFF5E1))
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Favourite",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Column(modifier = Modifier
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
                    text = "Your favourite list",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF24221E)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Example list of favourite items
            val favourites = listOf(
                "Ahmed Rasshed" to "Account xxxx7890",
                "Ahmed Fathy" to "Account xxxx7890",
                "Ahmed Alaa" to "Account xxxx7890",
                "Ahmed Galal" to "Account xxxx7890",
                "Ahmed Hagag" to "Account xxxx7890",
                "Ahmed Mohsen" to "Account xxxx7890",
                "Ahmed Test"  to "Account 121 5151"
            )
            LazyColumn {
                items(favourites){(name, account) ->
                    FavoriteScreenItem(
                        iconRes = R.drawable.group13,
                        name = name,
                        account = account,
                        onEditClick = {
                            selectedItem = name to account
                            isDialogOpen = true
                        },
                        onDeleteClick = {
                            // Handle delete logic here
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                }
            }
        }
    }

    if (isDialogOpen && selectedItem != null) {
        val (name, account) = selectedItem!!
        EditFavoriteDialog(
            name = name,
            account = account,
            onDismiss = { isDialogOpen = false },
            onSave = { newName, newAccount ->
                // Handle save logic here
                // Update the item in the list
                isDialogOpen = false
            }
        )
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
                text = account,
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
            FavoriteScreen(navController = navController )
        }
    }
}

@Composable
fun EditFavoriteDialog(
    name: String,
    account: String,
    onDismiss: () -> Unit,
    onSave: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    var newName by rememberSaveable { mutableStateOf(name) }
    var newAccount by rememberSaveable { mutableStateOf(account) }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        AlertDialog(
            onDismissRequest = onDismiss,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            title = {
                Box(
                    modifier = Modifier.fillMaxWidth(),
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
                        Text(text = "Edit")
                    }
                }
            },
            text = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    TextField(
                        value = newAccount,
                        onValueChange = { newAccount = it },
                        label = { Text("Recipient Account") },
                        placeholder = { Text("Enter Cardholder Name") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp)

                    )
                    TextField(
                        value = newName,
                        onValueChange = { newName = it },
                        label = { Text("Recipient Name") },
                        placeholder = { Text("Enter Cardholder Name") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),

                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = { },
                    modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .size(56.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(
                            id = R.color.reddd
                        )
                    )
                ) {
                    Text(text = "Save")
                }
            },
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun Show() {
    ScaffoldFav(navController = rememberNavController())
}

