package com.example.bm_app.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bm_app.R
import com.example.bm_app.SignUp_Screen.DatePickerChooser
import com.example.bm_app.approutes.AppRoutes
import com.example.bm_app.list.CountryList
import com.example.bm_app.model.Country
import java.text.SimpleDateFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(navController: NavController,country : List<Country>, modifier: Modifier = Modifier) {
    var FullName by remember {
        mutableStateOf("")
    }
    var Email by remember {
        mutableStateOf("")
    }

    var showCountryPicker by remember { mutableStateOf(false) }
    var selectedCountry by remember { mutableStateOf<Country?>(null) }
    var dateButton by remember { mutableStateOf("Choose a date") }
    var isDatePickerShown by remember { mutableStateOf(false) }
    var year by remember { mutableIntStateOf(0) }
    var month by remember { mutableIntStateOf(0) }
    var day by remember { mutableIntStateOf(0) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFFFF5E1))
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.edit_profile),
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
                Text(text = stringResource(R.string.full_name), fontSize = 16.sp)
                Spacer(modifier = Modifier.padding(4.dp))
                OutlinedTextField(
                    value = FullName,
                    onValueChange = { FullName = it },
                    modifier.fillMaxWidth(),
                    label = {
                        Text(text = stringResource(R.string.enter_your_full_name))
                    },
                )
            }
            Spacer(modifier = Modifier.padding(4.dp))

            Column {
                Text(text = stringResource(R.string.email), fontSize = 16.sp)
                Spacer(modifier = Modifier.padding(4.dp))
                OutlinedTextField(
                    value = Email,
                    onValueChange = { Email = it },
                    modifier
                        .fillMaxWidth(),
                    placeholder = { Text(text = stringResource(R.string.enter_your_email)) },

                )
            }
            Spacer(modifier = Modifier.padding(4.dp))
            Column {
                Text(text = stringResource(R.string.country), fontSize = 16.sp)
                Spacer(modifier = Modifier.padding(4.dp))
                if (showCountryPicker){
                    ModalBottomSheet(onDismissRequest = { showCountryPicker = false}) {
                        Box(modifier = modifier
                            .fillMaxHeight()
                            .padding(12.dp)) {

                            Column (modifier = modifier.padding(8.dp)){

                                country.forEach{ countryItem ->
                                    Row(modifier = modifier
                                        .fillMaxWidth()
                                        .clickable { }
                                        .size(32.dp)) {

                                        Image(painter = painterResource(id = countryItem.image), contentDescription = null, modifier.size(24.dp))
                                        Spacer(modifier = modifier.padding(4.dp))
                                        Text(text = countryItem.country , fontSize = 16.sp)
                                    }
                                }
                            }
                        }

                    }
                }
                OutlinedTextField(
                    value = selectedCountry?.country ?: "",
                    onValueChange = {},
                    modifier = modifier
                        .clickable { showCountryPicker = true }
                        .fillMaxWidth(),
                    placeholder = {Text(text = stringResource(R.string.select_your_country))},
                    trailingIcon = { Icon(Icons.Filled.KeyboardArrowDown , contentDescription = null) },
                    readOnly = true
                )
            }
            Spacer(modifier = Modifier.padding(4.dp))
            Column {
                Text(text = stringResource(R.string.date_of_brith), fontSize = 16.sp)
                Spacer(modifier = Modifier.padding(4.dp))
                if (isDatePickerShown) {
                    DatePickerChooser(onConfirm = { dateState ->
                        val dateFormatter = SimpleDateFormat("dd-MM-yyyy", java.util.Locale.US)

                        year =
                            SimpleDateFormat("yyyy", java.util.Locale.US).format(dateFormatter.parse(dateButton)!!)
                                .toInt()
                        month =
                            SimpleDateFormat("MM", java.util.Locale.US).format(dateFormatter.parse(dateButton)!!)
                                .toInt()
                        day =
                            SimpleDateFormat("dd", java.util.Locale.US).format(dateFormatter.parse(dateButton)!!)
                                .toInt()
                        isDatePickerShown = false
                    }, onDismiss = { isDatePickerShown = false })
                }
                OutlinedTextField(
                    value = "",
                    onValueChange = {it},
                    modifier = modifier
                        .clickable { isDatePickerShown = true }
                        .fillMaxWidth(),
                    placeholder = {Text(text = stringResource(R.string.dd_mm_yyy))},
                    trailingIcon = {
                        Icon(Icons.Filled.DateRange,
                            contentDescription = null,
                            tint = Color(0xFFB0AFAE)
                        )
                    }
                )
            }
            Spacer(modifier = modifier.padding(8.dp))
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
    EditProfileScreen(navController = rememberNavController(),country = CountryList().getCountryList())
}

