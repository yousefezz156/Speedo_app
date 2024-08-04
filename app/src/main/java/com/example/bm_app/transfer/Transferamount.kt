package com.example.bm_app.transfer

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bm_app.R
import com.example.bm_app.approutes.AppRoutes
import com.example.bm_app.list.favoriteList
import com.example.bm_app.model.Favorite
import javax.sql.DataSource
data class data2 (val route : String,val title : String ,  val SelectedIcon : Painter, val unselectedItem : Painter)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Scaffold_Transfer(navController: NavController,modifier: Modifier = Modifier)
{
    var selectedItem by rememberSaveable {
        mutableStateOf(1)
    }
    val items = listOf(
        data2(
            route = "home",
            title = "Home",
            SelectedIcon = painterResource(id = R.drawable.home),
            unselectedItem = painterResource(id = R.drawable.home)
        ),
        data2(
            route = "no",
            title = "Tranfer",
            SelectedIcon = painterResource(id = R.drawable.transfer_figma),
            unselectedItem = painterResource(id = R.drawable.transfer_figma)
        ),
        data2(
            route = "transactions",
            title = "Transactions",
            SelectedIcon = painterResource(id = R.drawable.transaction_figma),
            unselectedItem = painterResource(id = R.drawable.transaction_figma)
        ),
        data2(
            route = "my_cards",
            title = "My cards",
            SelectedIcon = painterResource(id = R.drawable.cards_figma),
            unselectedItem = painterResource(id = R.drawable.cards_figma)
        ),
        data2(
            route = "more",
            title = "More",
            SelectedIcon = painterResource(id = R.drawable.more),
            unselectedItem = painterResource(id = R.drawable.more)
        )
    )
    Scaffold(
        topBar = { TopAppBar(
            navigationIcon = {
                IconButton(onClick = {  }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = null)
                }
            } ,
            title = { Box(modifier.fillMaxWidth(),contentAlignment = Alignment.Center) {
                Text(text = "Transfer" , textAlign = TextAlign.Center)
            } })},bottomBar = {
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
            TransferPage(navController,favorite = favoriteList().getFavoriteList())

        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransferPage(navController: NavController,modifier: Modifier = Modifier, favorite: List<Favorite>) {

    var from by remember {
        mutableStateOf("1000")
    }
    var where by remember {
        mutableStateOf("48332.50")
    }
    var Recipient_name by remember {
        mutableStateOf("")
    }
    var Recipient_account by remember {
        mutableStateOf("")
    }
    var showpicker by remember {
        mutableStateOf(false)
    }
    Column(modifier = modifier
        .fillMaxSize()
        .verticalScroll(state = ScrollState(1), true))
    {
        if(showpicker){
            ModalBottomSheet(onDismissRequest = { showpicker = false })
            {
                favorite.forEach { favoriteItem ->
                    Card(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        colors = CardDefaults.cardColors(
                            colorResource(id = R.color.purple_200)
                        )
                    )
                    {
                        card(
                            painter = favoriteItem.image,
                            name = favoriteItem.name,
                            accountB = favoriteItem.accountB
                        )
                    }
                }
            }
        }
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center)
        {
            stepintext(painter = painterResource(id = R.drawable.step_text___horizontal), text = "Amount")
            stepintext(painter = painterResource(id = R.drawable.step_trail), text = "")
            stepintext(painter = painterResource(id = R.drawable.step_text_2_gray), text = "Confirmation")
            stepintext(painter = painterResource(id = R.drawable.step_trail_gray), text = "")
            stepintext(painter = painterResource(id = R.drawable.step_text_2_gray), text = "Amount")

        }
        Spacer(modifier = modifier.padding(16.dp))
        Row (
            modifier
                .fillMaxWidth()
                .padding(8.dp)){
            Text(text = "How much are you sending", fontSize = 20.sp)

        }
        Spacer(modifier = modifier.padding(16.dp))
        Row (
            modifier
                .fillMaxWidth()
                .padding(8.dp)){
            Text(text = "Choose Currency")
        }
        Card(
            colors = CardDefaults.cardColors(Color.White),
            modifier = modifier
                .height(340.dp)
                .padding(8.dp)
                .fillMaxSize())
        {
            Text(text = "1 USD = 48.4220" , modifier.padding(8.dp))
            Text(text = "Rate guaranteed (2)" , modifier.padding(12.dp), color = colorResource(id = R.color.gry))
            Text(text = "You Send" , modifier.padding(8.dp))
            Row(modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Icon(painter = painterResource(id = R.drawable.united_states), contentDescription =null )
                Text(text = "USD" , modifier.padding(8.dp))
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Filled.KeyboardArrowDown, contentDescription =null )
                }
                Row(horizontalArrangement = Arrangement.End , modifier =modifier.fillMaxWidth())
                {
                    OutlinedTextField(value =from , onValueChange = {from = it}, modifier = modifier.size(height = 56.dp, width = 160.dp) )
                }



            }
            Spacer(modifier = modifier.padding(8.dp))
            Divider(color = Color.Black, thickness = 0.5.dp)
            Spacer(modifier = modifier.padding(8.dp))
            Text(text = "Recipient Gets")

            Row(modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {

                Image(painter = painterResource(id = R.drawable.united_states), contentDescription =null )
                Text(text = "USD" , modifier.padding(8.dp))
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Filled.KeyboardArrowDown, contentDescription =null )
                }
                Row(horizontalArrangement = Arrangement.End , modifier =modifier.fillMaxWidth())
                {
                    OutlinedTextField(value =from , onValueChange = {from = it}, modifier = modifier.size(height = 56.dp, width = 160.dp) )
                }


            }


        }
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.fillMaxWidth())
        {
           // Spacer(modifier = modifier.padding(24.dp))
            Text(text = "Recipient Information" ,modifier.padding(8.dp) , textAlign = TextAlign.Center)
            Row(modifier = modifier.fillMaxWidth() , horizontalArrangement = Arrangement.End) {
                TextButton(onClick = { showpicker= true}) {
                    Icon(
                        painter = painterResource(id = R.drawable.favorite_star),
                        contentDescription = null
                    )
                    Text(text = "Favorite")
                    Icon(Icons.Filled.KeyboardArrowRight, contentDescription = null)
                }
            }

        }
        Text(text = "Recipient Name", modifier.padding(8.dp))
        OutlinedTextField(value = Recipient_name , onValueChange ={Recipient_name = it} ,
            modifier
                .fillMaxWidth()
                .padding(8.dp) , placeholder = { Text(
            text = "Enter Recipient Name"
        )} )
        Text(text = "Recipient Account", modifier.padding(8.dp))
        OutlinedTextField(value = Recipient_account , onValueChange ={Recipient_account = it} ,
            modifier
                .fillMaxWidth()
                .padding(8.dp) , placeholder = { Text(
                text = "Enter Recipient Account Number"
            )} )

        Button(onClick = { navController.navigate(AppRoutes.TRANSFER_CONFIRMATION) } ,
            modifier
                .fillMaxWidth()
                .padding(8.dp), shape = RoundedCornerShape(4.dp),
            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.reddd))) {
            Text(text = "Continue" , color = Color.White)
        }
    }



}

@Composable
fun stepintext( painter: Painter , text : String, modifier: Modifier = Modifier) {
Column(horizontalAlignment = Alignment.CenterHorizontally , verticalArrangement = Arrangement.Center) {
    Image(painter = painter, contentDescription =null)
    Spacer(modifier = modifier.padding(16.dp))
    Text(text = text, textAlign = TextAlign.Center, )
}
}

@Composable
fun card(@DrawableRes painter: Int ,name: String,accountB : String,modifier: Modifier = Modifier) {
    Row (modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
        Image(painter = painterResource(id = painter) , contentDescription = null)
        Spacer(modifier = modifier.padding(8.dp))
        Column(
            modifier =modifier.fillMaxWidth())
        {
            Text(text = name , textAlign = TextAlign.Center)
            Spacer(modifier = modifier.padding(4.dp))
            Text(text = accountB , textAlign = TextAlign.Center)
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun prevScreen() {
   // TransferPage(favorite = favoriteList().getFavoriteList())
    Scaffold_Transfer(rememberNavController())
}