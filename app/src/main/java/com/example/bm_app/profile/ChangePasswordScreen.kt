package com.example.bm_app.profile


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bm_app.R
import com.example.bm_app.approutes.AppRoutes


@Composable
fun ChangePasswordScreen(navController: NavController, modifier: Modifier = Modifier) {
    var password by remember {
        mutableStateOf("")
    }
    var passwordVisual by remember {
        mutableStateOf(false)
    }
    var newPassword by remember {
        mutableStateOf("")
    }
    var newPasswordVisual by remember {
        mutableStateOf(false)
    }


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFFEF0EA))
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.change_password),
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(vertical = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Column {
                Text(text = stringResource(R.string.current_password), fontSize = 16.sp)
                Spacer(modifier = Modifier.padding(4.dp))
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    modifier = Modifier.fillMaxWidth().background(Color(0xFFFFFFFF)),
                    label = {
                        Text(text = stringResource(R.string.enter_your_password))
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = if (passwordVisual) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { passwordVisual = !passwordVisual }) {
                            val icon =
                                if (!passwordVisual) Image(
                                    painter = painterResource(id = R.drawable.eye_comp),
                                    contentDescription =null )
                                else
                                    Image(
                                        painter = painterResource(id = R.drawable.eye_open),
                                        contentDescription = null
                                    )

                        }
                    }
                )
            }
            Spacer(modifier = Modifier.padding(4.dp))

            Column {
                Text(text = stringResource(R.string.new_password), fontSize = 16.sp)
                Spacer(modifier = Modifier.padding(4.dp))
                OutlinedTextField(
                    value = newPassword,
                    onValueChange = { newPassword = it },
                    modifier
                        .fillMaxWidth()
                        .background(Color(0xFFFFFFFF)),
                    placeholder = { Text(text = stringResource(R.string.enter_your_password)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = if (newPasswordVisual) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { newPasswordVisual = !newPasswordVisual }) {
                            val icon =
                                if (!newPasswordVisual) Image(
                                    painter = painterResource(id = R.drawable.eye_comp),
                                    contentDescription =null )
                                else
                                    Image(
                                        painter = painterResource(id = R.drawable.eye_open),
                                        contentDescription = null
                                    )

                        }
                    }
                )
            }
            Spacer(modifier = Modifier.padding(8.dp))
            Button(
                onClick = {navController.navigate(AppRoutes.TRANSFER_HOME)},
                shape = RoundedCornerShape(8.dp),
                modifier = modifier
                    .fillMaxWidth()
                    .size(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.reddd))
            )
            {
                Text(text = stringResource(R.string.save), fontSize = 16.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Show() {
    ChangePasswordScreen(navController = rememberNavController())
}

