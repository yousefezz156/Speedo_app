package com.example.bm_app.SignUp_Screen

import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Label
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.bm_app.R
import com.example.bm_app.approutes.AppRoutes
import com.example.bm_app.approutes.AppRoutes.SIGNIN
import com.example.bm_app.approutes.AppRoutes.SIGNUP2

@Composable
fun ScaffoldSignup(navController: NavController,modifier: Modifier = Modifier) {
    Scaffold()
    {
        innerpadding ->
        Box (modifier = modifier.padding(innerpadding)){
            SignUp(navController = navController)
        }
    }
}

@Composable
fun SignUp(navController: NavController,modifier : Modifier = Modifier) {
    var FullName by remember {
        mutableStateOf("")
    }
    var Email by remember {
        mutableStateOf("")
    }
    var Password by remember {
        mutableStateOf("")
    }
    var passwordVisual by remember {
        mutableStateOf(false)
    }
    Column(
        //  verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    )
    {
        Spacer(modifier = modifier.padding(8.dp))

        Text(
            text = "Sign Up",
            fontSize = 20.sp, fontWeight = FontWeight.Bold
        )
        Spacer(modifier = modifier.padding(55.dp))
        Text(text = " Speedo Transfer", fontWeight = FontWeight.Bold, fontSize = 24.sp)
        Spacer(modifier = modifier.padding(55.dp))

        Column(modifier = modifier.fillMaxWidth()) {
            Text(text = "Full Name", modifier.padding(horizontal = 8.dp))
            OutlinedTextField(
                value = FullName,
                onValueChange = { FullName = it },
                modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                label = {
                    Text(text = "Enter your full name")
                }, trailingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.user),
                        contentDescription = null
                    )
                })
            Text(text = "Email", modifier.padding(8.dp))
            OutlinedTextField(
                value = Email,
                onValueChange = { Email = it },
                modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                placeholder = { Text(text = "Enter your Email") }, trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.email),
                        contentDescription = null
                    )
                })
            Text(text = "Password", modifier.padding(8.dp))
            OutlinedTextField(
                value = Password,
                onValueChange = { Password = it },
                modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                placeholder = { Text(text = "Enter your Password") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = PasswordVisualTransformation(),
                trailingIcon = {
                    if (passwordVisual)
                        Icon(
                            painter = painterResource(id = R.drawable.eye_comp),
                            contentDescription = null
                        )
                    else
                        Icon(
                            painter = painterResource(id = R.drawable.eye_open),
                            contentDescription = null
                        )

                }
            )
            Spacer(modifier = modifier.padding(8.dp))
            Button(
                onClick = { navController.navigate(SIGNUP2) },
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
                Text(text = "Sign up")
            }
            Spacer(modifier = modifier.padding(8.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = modifier.fillMaxWidth()
            ) {
                Text(text = "Already have an account?")
                Text(text = " Sign in",
                    textDecoration = TextDecoration.Underline,
                    color = colorResource(
                        id = R.color.reddd
                    ),
                    modifier = modifier.clickable { navController.navigate(AppRoutes.SIGNIN)})
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun SignUp_Prev() {
    SignUp(rememberNavController())
}