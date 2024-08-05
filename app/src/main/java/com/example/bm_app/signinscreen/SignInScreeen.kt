package com.example.bm_app.signinscreen

//import android.provider.ContactsContract.CommonDataKinds.Email
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.material3.Button
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.Icon
//import androidx.compose.material3.OutlinedTextField
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.res.colorResource
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.input.KeyboardType
//import androidx.compose.ui.text.input.PasswordVisualTransformation
//import androidx.compose.ui.text.style.TextDecoration
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import com.example.bm_app.R
//import kotlin.math.sign


import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bm_app.R
import com.example.bm_app.api.loginApi.loginUserClient
import com.example.bm_app.approutes.AppRoutes
import com.example.bm_app.modelApi.LoginRequest
import com.example.bm_app.modelApi.LoginResponse
import com.example.bm_app.validation.isValidEmail
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun ScaffoldSignIn(navController: NavController,modifier: Modifier = Modifier) {
    Scaffold()
    { innerpadding ->
        Box(modifier = modifier.padding(innerpadding)){
            SigninScreen(navController = navController )
        }
    }
}
@Composable
fun SigninScreen(navController: NavController,modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val prefs = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)
    val savedEmail = prefs.getString("email", "")!!
    val savedPassword = prefs.getString("password", "")!!


    var email by remember { mutableStateOf(savedEmail) }
    var password by remember { mutableStateOf(savedPassword) }
    var passwordVisible by remember { mutableStateOf(false) }



    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
            .padding(8.dp)
    ) {
        Spacer(modifier = modifier.padding(16.dp))

        Text(text = "Sign in",fontSize = 20.sp, fontWeight = FontWeight.Normal)
        Spacer(modifier = Modifier.padding(55.dp))
        Text(text = "Speedo Transfer", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.padding(55.dp))

        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = "Email", modifier = Modifier.padding(8.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                placeholder = { Text(text = "Enter your Email") },
                trailingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.email),
                        contentDescription = null
                    )
                }
            )
            Text(text = "Password", modifier = Modifier.padding(8.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                placeholder = { Text(text = "Enter your Password") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        val icon =
                            if (passwordVisible) Image(
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
            Spacer(modifier = Modifier.padding(8.dp))
            Button(
                onClick = {  if (isValidEmail(email) && password.isNotEmpty()) {
                    val loginRequest = LoginRequest(email, password)
                    loginUserClient.instance.loginUser(loginRequest).enqueue(object :
                        Callback<ResponseBody> {
                        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                            if (response.isSuccessful) {
                                val responsebody = response.body()?.string()
                                if (responsebody != null) {
                                    val token = responsebody
                                    storeToken(context, token)
                                    Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()
                                    navController.navigate(AppRoutes.TRANSFER_HOME)
                                }
                            } else {
                                val errorCode = response.code()
                                val errorMessage = response.errorBody()?.string() ?: "Unknown error"
                                Toast.makeText(context, "Login failed: $errorCode - $errorMessage", Toast.LENGTH_SHORT).show()
                               // Toast.makeText(context, "Login failed", Toast.LENGTH_SHORT).show()
                            }
                        }

                        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                            Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                        }
                    })
                } else {
                    Toast.makeText(context, "Please enter valid email and password", Toast.LENGTH_SHORT).show()
                }
                          },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .height(56.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.reddd)
                )
            ) {
                Text(text = "Sign in")
            }
            Spacer(modifier = Modifier.padding(8.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Don't have an account?")
                Text(
                    text = " Sign up",
                    textDecoration = TextDecoration.Underline,
                    color = colorResource(id = R.color.reddd),
                    modifier = Modifier.clickable { }
                )
            }
        }
    }
}
fun storeToken(context: Context, token: String) {
    val sharedPreferences = context.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
    with(sharedPreferences.edit()) {
        putString("auth_token", token)
        apply()
    }
}

@Preview(showBackground = true)
@Composable
private fun SignInPrev() {
    SigninScreen(rememberNavController())
}
