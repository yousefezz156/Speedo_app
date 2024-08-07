package com.example.bm_app.mycard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaaffoldOTPend(navController: NavController,modifier: Modifier = Modifier) {
    Scaffold(topBar = {
        TopAppBar(navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.KeyboardArrowLeft, contentDescription = null)

            }
        }, title = {
            Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center)
            {
                Text(text = stringResource(R.string.bank_card_otp))
            }
        })
    }) { innerpadding ->

        Box(modifier = modifier.padding(innerpadding)) {

        }

    }
}



@Composable
fun AccountConnectedScreen(navController: NavController,modifier: Modifier = Modifier) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFEF0EA))
            .padding(16.dp)
    ) {
        Spacer(modifier = modifier.padding(76.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            IconChange()
            Spacer(modifier = modifier.padding(50.dp))
            Text(
                text = stringResource(R.string.account_connected_successfully),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.feel_free_to_connect_another_account_at_the_same_time),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = { navController.navigate(AppRoutes.TRANSFER_PAYMENT) },
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.reddd)),
                modifier = modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(6.dp)

            ) {
                Text(text = stringResource(R.string.create_another_account))
            }
            Spacer(modifier = modifier.padding(8.dp))
            Button(
                onClick = { navController.navigate(AppRoutes.TRANSFER_AMOUNT)},
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.white)),
                modifier = modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(6.dp),
                border = BorderStroke(width = 0.5.dp, color = colorResource(id = R.color.reddd))

            ) {
                Text(text = stringResource(R.string.back_to_home), color = colorResource(id = R.color.reddd))
            }
        }
    }
}
@Composable
fun IconChange(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .width(124.dp)
            .height(120.dp)
            .clip(CircleShape)
            .background(
                colorResource(id = R.color.gry)
            ),
        contentAlignment = Alignment.Center

    )
    {
        Image(
            painter = painterResource(id = R.drawable.confirmation),
            contentDescription = null,
            modifier = Modifier
                .width(124.dp)
                .height(120.dp)
        )

    }
}

@Preview(showBackground = true)
@Composable
private fun prevds () {
    AccountConnectedScreen(rememberNavController())
}