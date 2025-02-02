package com.example.bm_app.SignUp_Screen

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.key.Key.Companion.Calendar
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bm_app.R
import com.example.bm_app.approutes.AppRoutes
import com.example.bm_app.list.CountryList
import com.example.bm_app.model.Country
import com.example.bm_app.model.Currency
import com.example.bm_app.mycard.CurrencyCard
import java.text.SimpleDateFormat
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreenP2S(navController: NavController,modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { /*TODO*/ },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ })
                    {
                        Icon(Icons.Filled.ArrowBack, contentDescription = null)
                    }
                }


            )
        }
    )
    {
        innerpadding ->
        Box (modifier = modifier.padding(innerpadding)){
            SignUpScreenP2(navController, country = CountryList().getCountryList() )
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreenP2(navController: NavController,country : List<Country>,modifier: Modifier = Modifier) {

    var showCountryPicker by remember { mutableStateOf(false) }
    var selectedCountry by remember { mutableStateOf<Country?>(null) }
    var dateButton by remember { mutableStateOf("Choose a date") }
    var isDatePickerShown by remember { mutableStateOf(false) }
    val context = LocalContext.current
    var year by remember { mutableIntStateOf(0) }
    var month by remember { mutableIntStateOf(0) }
    var day by remember { mutableIntStateOf(0) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFFEF0EA))
            .padding(16.dp)
    ) {
        Text(text = stringResource(R.string.speedo_transfer), fontWeight = FontWeight.Bold, fontSize = 24.sp)
        Spacer(modifier = modifier.padding(12.dp))
        Text(text = stringResource(R.string.welcome_to_banque_misr) , fontWeight = FontWeight.Bold, fontSize = 24.sp)
        Spacer(modifier = modifier.padding(4.dp))
        Text(text = stringResource(R.string.lets_complete_your_profile))

        Column(modifier = modifier.fillMaxWidth()) {
            Text(text = stringResource(R.string.country), modifier.padding(8.dp))
            if (showCountryPicker){
                ModalBottomSheet(onDismissRequest = { showCountryPicker = false}) {
                  lazycol(country = CountryList().getCountryList() , selectedCountry = selectedCountry, onCountrySelected = {selected -> selectedCountry = selected
                  showCountryPicker=false})

            }}

            if (isDatePickerShown) {
                DatePickerChooser(onConfirm = { dateState ->
                    val dateFormatter = SimpleDateFormat("yyyy-MM-dd", java.util.Locale.US)
                    val c = android.icu.util.Calendar.getInstance()
                    //Ex: time in millis = 1720483200000
                    //Ex: c.time = Tue Jul 09 03:00:00 GMT+03:00 2024 --> date formatter = 09-07-2024
                    dateButton = dateFormatter.format(c.time)
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
                value = selectedCountry?.country ?: "",
                onValueChange = {it},
                modifier = modifier
                    .clickable { showCountryPicker = true }
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(Color(0xFFFFFFFF)),
                placeholder = {Text(text =  stringResource(R.string.select_your_country))},
                trailingIcon = {Icon(Icons.Filled.KeyboardArrowDown , contentDescription = null)},
                readOnly = true
            )
            Text(text = stringResource(R.string.date_of_brith), modifier.padding(8.dp))
            OutlinedTextField(
                value = "",
                onValueChange = {it},
                modifier = modifier
                    .clickable { isDatePickerShown = true }
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(Color(0xFFFFFFFF)),
                placeholder = {Text(dateButton)},
                trailingIcon = {Icon(Icons.Filled.DateRange , contentDescription = null)}
            )
            Spacer(modifier = modifier.padding(16.dp))
            Button(
                onClick = {navController.navigate(AppRoutes.TRANSFER_HOME)},
                shape = RoundedCornerShape(8.dp),
                modifier = modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .size(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.reddd))
            )
            {
                Text(text = stringResource(R.string._continue))
            }
        }
    }

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerChooser(onConfirm: (DatePickerState) -> Unit, onDismiss: () -> Unit) {

    val datePickerState = rememberDatePickerState()

    AlertDialog(
        onDismissRequest = {},
        confirmButton = {
            TextButton(onClick = { onConfirm(datePickerState) }) {
                Text(text = "ok")
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) { Text(text = stringResource(R.string.cancel)) }
        },
        text = { DatePicker(state = datePickerState) },
    )
}

@Composable
fun Countrycard(onCountrySelected: (Country) -> Unit,
                isSelected: Boolean,country: Country,modifier: Modifier = Modifier)
{

    Row(modifier = modifier
        .fillMaxWidth()
        .padding(16.dp)
        .clickable {
            onCountrySelected(country)
        }) {
        Image(painter = painterResource(id = country.image), contentDescription = null)
        Spacer(modifier = modifier.padding(8.dp))
        Text(text = country.country)

        if (isSelected) {
            Spacer(modifier = modifier.weight(1f))
            Icon(Icons.Filled.Check, contentDescription = null)
        }
    }
}
@Composable
fun lazycol(selectedCountry: Country?,
            onCountrySelected: (Country) -> Unit,country: List<Country>, modifier: Modifier = Modifier) {


    LazyColumn {
        items(country) { countryy ->
            Countrycard(country = countryy , isSelected = selectedCountry==countryy, onCountrySelected = onCountrySelected)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpP2Prev() {
    SignUpScreenP2(rememberNavController(),country = CountryList().getCountryList() )
}