package com.example.bm_app.approutes

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bm_app.SignUp_Screen.ScaffoldSignup
import com.example.bm_app.SignUp_Screen.SignUp
import com.example.bm_app.SignUp_Screen.SignUpScreenP2
import com.example.bm_app.SignUp_Screen.SignUpScreenP2S
import com.example.bm_app.list.CountryList
import com.example.bm_app.mycard.ScaaffoldOTPend
import com.example.bm_app.mycard.ScaffoldCurrency
import com.example.bm_app.mycard.ScaffoldOtp
import com.example.bm_app.mycard.Scaffold_AddCard
import com.example.bm_app.signinscreen.SigninScreen
import com.example.bm_app.transfer.ScaffoldBack
import com.example.bm_app.transfer.Scaffold_Transfer
import com.example.bm_app.transfer.ScaffoldtransMain
import com.example.bm_app.transfer.TransferHome
import com.example.bm_app.transfer.scaffoldConfirm

object AppRoutes
{
    val SIGNUP1="signup_1"
    val SIGNUP2="signup_2"
    val SIGNIN ="signin"
    val TRANSFER_HOME ="transfer_home"
    val TRANSFER_AMOUNT ="transfer_amount"
    val TRANSFER_CONFIRMATION ="transfer_confirmation"
    val TRANSFER_PAYMENT ="transfer_payment"
    val MYCARDS_SELECTCURRENY = "mycards_selectcurrency"
    val MYCARDS_ADDCARDS = "mycards_addcards"
    val MYCARDS_LOADINGSCREEN = "mycards_loadingscreen"
    val MYCARDS_OTP = "mycards_otp"
    val MYCARDS_SUCCESFUL = "mycards_succesful"

}

@Composable
fun AppNavHost()
{
 val navController = rememberNavController()

 NavHost(navController = navController, startDestination = AppRoutes.SIGNUP1 )
 {
     composable(route = AppRoutes.SIGNUP1){ ScaffoldSignup(navController) }
     composable(route = AppRoutes.SIGNUP2){ SignUpScreenP2S( navController) }
     composable(route = AppRoutes.SIGNIN){ SigninScreen(navController) }
     composable(route = AppRoutes.TRANSFER_HOME){ ScaffoldtransMain(navController = navController)}
     composable(route = AppRoutes.TRANSFER_AMOUNT){ Scaffold_Transfer(navController) }
     composable(route = AppRoutes.TRANSFER_CONFIRMATION){ scaffoldConfirm(navController) }
     composable(route = AppRoutes.TRANSFER_PAYMENT){ ScaffoldBack(navController) }
     composable(route = AppRoutes.MYCARDS_SELECTCURRENY){ ScaffoldCurrency(navController) }
     composable(route = AppRoutes.MYCARDS_ADDCARDS){ Scaffold_AddCard(navController) }
     composable(route = AppRoutes.MYCARDS_LOADINGSCREEN){ ScaffoldBack(navController) }
     composable(route = AppRoutes.MYCARDS_OTP){ ScaffoldOtp(navController) }
     composable(route = AppRoutes.MYCARDS_SUCCESFUL){ ScaaffoldOTPend(navController) }
 }
}