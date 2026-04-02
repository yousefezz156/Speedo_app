package com.example.bm_app

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.bm_app.MainActivity.Companion.CAMERAX_PERMISSIONS
import com.example.bm_app.SignUp_Screen.SignUpScreenP2
import com.example.bm_app.approutes.AppNavHost
import com.example.bm_app.list.CountryList
import com.example.bm_app.mycard.OTPScreen
import com.example.bm_app.mycard.ScaffoldOtp
import com.example.bm_app.transfer.ScaffoldBack
import com.example.bm_app.transfer.Scaffold_Transfer
//import com.example.bm_app.signinscreen.SigninScreen

import com.example.bm_app.ui.theme.Bm_appTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        if (!hasPermission()) {
            ActivityCompat.requestPermissions(this, CAMERAX_PERMISSIONS, 0)
        }
        setContent {
            Bm_appTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                  // SignUpScreenP2(country = CountryList().getCountryList())
                   // SigninScreen()
//                    BankingAppUI()
                   // ScaffoldBack()
                     AppNavHost()
                   // SOTPScreen()
                    //ScaffoldOtp()
                }
            }
        }
    }

    fun hasPermission(): Boolean {
        return CAMERAX_PERMISSIONS.all{
            ContextCompat.checkSelfPermission(
                applicationContext,
                it
            ) == android.content.pm.PackageManager.PERMISSION_GRANTED
        }
    }
    companion object {
        val CAMERAX_PERMISSIONS= arrayOf(android.Manifest.permission.CAMERA)
    }

}


