package com.example.bm_app.SignUp_Screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bm_app.R
import com.example.bm_app.api.registerApi.RegisterApiPost
import com.example.bm_app.approutes.AppRoutes
import com.example.bm_app.approutes.AppRoutes.SIGNUP2
import com.example.bm_app.modelApi.Register
import com.example.bm_app.validation.createPasswordvalidation
import com.example.bm_app.validation.isValidEmail
import com.example.bm_app.viewModel.SignUpViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun ScaffoldSignup(navController: NavController, modifier: Modifier = Modifier, signUpViewModel: SignUpViewModel = viewModel()) {
    Scaffold()
    { innerpadding ->
        Box(modifier = modifier.padding(innerpadding)) {
            SignUp(navController = navController, signUpViewModel = signUpViewModel)
        }
    }
}

@Composable
fun SignUp(navController: NavController, modifier: Modifier = Modifier, signUpViewModel: SignUpViewModel = viewModel()) {
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
    val context = LocalContext.current
    Column(
        //  verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(state = ScrollState(1), true)
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
            Spacer(modifier = modifier.padding(8.dp))
            Button(
                onClick =
                {
                    if (createPasswordvalidation(Password) && isValidEmail(Email)) {
                        val register = Register(
                            email = Email,
                            userName = FullName,
                            password = Password,
                            gender = "",
                            dateOfBirth = "",
                            country = "egypt",
                            isActive = 1
                        )

                        RegisterApiPost.instance.createuser(register).enqueue(object : Callback<Void>{
                            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                                if(response.isSuccessful){
                                    signUpViewModel.fullName= FullName
                                    Toast.makeText(context , "user Registerd successfully" , Toast.LENGTH_SHORT).show()
                                    navController.navigate(SIGNUP2)

                                }else{
                                    val errorBody = response.errorBody()?.string()
                                    val errorMessage = "Failed with code: ${response.code()}, error: $errorBody"
                                    Toast.makeText(context , errorMessage , Toast.LENGTH_SHORT).show()
                                }
                            }

                            override fun onFailure(call: Call<Void>, t: Throwable) {
                                Toast.makeText(context ,"error : ${t.message}" , Toast.LENGTH_SHORT).show()
                            }
                        })
                    } else {
                        Toast.makeText(context, "please fill the password", Toast.LENGTH_SHORT)
                            .show()
                    }
                },
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
                    modifier = modifier.clickable { navController.navigate(AppRoutes.SIGNIN) })
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun SignUp_Prev() {
    SignUp(rememberNavController())
}