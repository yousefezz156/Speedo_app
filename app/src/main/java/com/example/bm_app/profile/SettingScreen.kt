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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bm_app.R

@Composable
fun SettingScreen(navController: NavController, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFFFF5E1))
    ) {

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Setting",
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
                    .clickable { navController.navigate("change_password")}
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
                    Icon(painter = painterResource(id = R.drawable.password_icon),
                        contentDescription = null,
                        modifier = Modifier
                            .size(22.dp),
                        tint = Color(0xFFCA0808)

                    )

                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = "Change password",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF24221E)
                    )
                    Spacer(modifier = Modifier.padding(1.dp))
                    Text(
                        text = "Change password",
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
                    .clickable { navController.navigate("edit_profile")}
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
                    Icon(painter = painterResource(id = R.drawable.edit),
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp),
                        tint = Color(0xFFCA0808)

                    )

                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = "Edit Profile",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF24221E)
                    )
                    Spacer(modifier = Modifier.padding(1.dp))
                    Text(
                        text = "Change your information",
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


@Preview(showBackground = true)
@Composable
private fun Show() {
    SettingScreen(navController = rememberNavController())
}

