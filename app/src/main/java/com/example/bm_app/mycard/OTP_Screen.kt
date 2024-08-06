package com.example.bm_app.mycard

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bm_app.R
import com.example.bm_app.approutes.AppRoutes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldOtp(navController: NavController,modifier: Modifier = Modifier) {
    Scaffold(topBar = {
        TopAppBar(navigationIcon = {
        }, title = {
            Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center)
            {
                Text(text = stringResource(R.string.bank_card_otp))
            }
        })
    }) { innerpadding ->

        Box(modifier = modifier.padding(innerpadding)) {
            OTPScreen(rememberNavController())
        }

    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OTPInputBox(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    OutlinedTextField(
        value = value,
        onValueChange = {
            if (it.length <= 1) onValueChange(it.filter { char -> char.isDigit() })
        },
        singleLine = true,
        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center, fontSize = 24.sp , color = colorResource(
            id = R.color.reddd
        )),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,

        ),
        keyboardActions = KeyboardActions(onNext = { }),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = if(value.isEmpty()) Color.Gray else colorResource(id = R.color.reddd),
            focusedBorderColor = if(value.isEmpty()) Color.Gray else colorResource(id = R.color.reddd),
            disabledBorderColor = if(value.isEmpty()) Color.Gray else colorResource(id = R.color.reddd),
            errorBorderColor = if(value.isEmpty()) Color.Gray else colorResource(id = R.color.reddd)
        ),
        modifier = modifier
            .height(60.dp)
            .width(50.dp)
            ,
        shape = RoundedCornerShape(8.dp)
    )
}

@Composable
fun OTPScreen(navController: NavController, modifier: Modifier = Modifier) {
    var otpValues = remember { mutableStateListOf("", "", "", "", "", "") }
    val isComplete = otpValues.none { it.isEmpty() }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = modifier.height(48.dp))
        Text(stringResource(R.string.enter_the_digits_verification_code), fontSize = 16.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            otpValues.forEachIndexed { index, value ->
                OTPInputBox(
                    value = value,
                    onValueChange = { newValue ->
                        otpValues[index] = newValue
                        if (newValue.length == 1 && index < otpValues.size - 1) {

                        }
                    },
                    modifier = modifier.padding(4.dp),

                )
            }
        }
        Spacer(modifier = modifier.height(24.dp))
        Row() {
            Text(stringResource(R.string.don_t_receive_otp), color = Color.Gray)
            Text(stringResource(R.string.resend_otp) , color = colorResource(id = R.color.reddd), modifier = Modifier.clickable {  })
        }
        Spacer(modifier = modifier.height(156.dp))
        Button(
            onClick = { navController.navigate(AppRoutes.MYCARDS_SUCCESFUL) },
            enabled = isComplete,
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isComplete) Color.Red else Color.Gray
            ),
            modifier = modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            if(isComplete) {
                Text(stringResource(R.string.confirm), color = Color.White)
            }else{
                Text(stringResource(R.string.sign_on), color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun OTPPrev() {
    ScaffoldOtp(rememberNavController() )
}