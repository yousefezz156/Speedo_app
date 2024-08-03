package com.example.bm_app.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun ProfileInformationScreen(navController: NavController, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFFFF5E1))
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Profile information",
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
                Column {
                    Text(
                        text = "Full Name",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF24221E)
                    )
                    Spacer(modifier = Modifier.padding(6.dp))
                    Text(
                        text = "Ahmed Rashed",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF898886)
                    )
                }
            }
            HorizontalDivider(thickness = 1.dp, color = Color(0xFFDDD5EB))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .fillMaxWidth()
                    .clickable { }
                    .padding(horizontal = 1.dp, vertical = 16.dp)
            ) {
                Column {
                    Text(
                        text = "Email",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF24221E)
                    )
                    Spacer(modifier = Modifier.padding(6.dp))
                    Text(
                        text = "Ahmed@gmail.com",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF898886)
                    )
                }
            }
            HorizontalDivider(color = Color(0xFFDDD5EB), thickness = 1.dp)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .fillMaxWidth()
                    .clickable { }
                    .padding(horizontal = 1.dp, vertical = 16.dp)
            ) {
                Column {
                    Text(
                        text = "Date Of Brith",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF24221E)
                    )
                    Spacer(modifier = Modifier.padding(6.dp))
                    Text(
                        text = "12/01/2000",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF898886)
                    )
                }
            }
            HorizontalDivider(color = Color(0xFFDDD5EB), thickness = 1.dp)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .fillMaxWidth()
                    .clickable { }
                    .padding(horizontal = 1.dp, vertical = 16.dp)
            ) {
                Column {
                    Text(
                        text = "Country",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF24221E)
                    )
                    Spacer(modifier = Modifier.padding(6.dp))
                    Text(
                        text = "Egypt",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF898886)
                    )
                }
            }
            HorizontalDivider(color = Color(0xFFDDD5EB), thickness = 1.dp)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .fillMaxWidth()
                    .clickable { }
                    .padding(horizontal = 1.dp, vertical = 16.dp)
            ) {
                Column {
                    Text(
                        text = "Bank Account",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF24221E)
                    )
                    Spacer(modifier = Modifier.padding(6.dp))
                    Text(
                        text = "12335XXX",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF898886)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Show() {
    ProfileInformationScreen(navController = rememberNavController())
}

