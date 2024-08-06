package com.example.bm_app.approutes

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bm_app.SignUp_Screen.ScaffoldSignup
//import com.example.bm_app.SignUp_Screen.ScaffoldSignup
//import com.example.bm_app.SignUp_Screen.SignUp
import com.example.bm_app.SignUp_Screen.SignUpScreenP2S
import com.example.bm_app.approutes.AppRoutes.SIGNUP1
import com.example.bm_app.approutes.AppRoutes.TRANSFER_CONFIRMATION
import com.example.bm_app.list.CountryList
import com.example.bm_app.more.ScaffoldFav
import com.example.bm_app.more.ScaffoldMoreMain
import com.example.bm_app.mycard.ScaffoldMyCardsMain
import com.example.bm_app.mycard.ScaaffoldOTPend
import com.example.bm_app.mycard.ScaffoldCurrency
import com.example.bm_app.mycard.ScaffoldMyCardsMain
import com.example.bm_app.mycard.ScaffoldOtp
import com.example.bm_app.mycard.Scaffold_AddCard
import com.example.bm_app.profile.ChangePasswordScreen
import com.example.bm_app.profile.EditProfileScreen
import com.example.bm_app.profile.ProfileInformationScreen
import com.example.bm_app.profile.ScaffoldProfile
import com.example.bm_app.profile.SettingScreen
import com.example.bm_app.signinscreen.SigninScreen
import com.example.bm_app.transaction.ScaffololdSuccessTransactionScreen
import com.example.bm_app.transaction.ScaffololdTransactionScreen
import com.example.bm_app.transfer.ScaffoldBack
import com.example.bm_app.transfer.Scaffold_Transfer
import com.example.bm_app.transfer.ScaffoldtransMain
import com.example.bm_app.transfer.scaffoldConfirm
import com.example.bm_app.viewModel.AddCardViewModel
import com.example.bm_app.viewModel.SignUpViewModel
import kotlin.math.sign

object AppRoutes {
    val SIGNUP1 = "signup_1"
    val SIGNUP2 = "signup_2"
    val SIGNIN = "signin"
    val TRANSFER_HOME = "home"
    val TRANSFER_AMOUNT = "transfer"
    val TRANSFER_CONFIRMATION = "transfer_confirmation"
    val TRANSFER_PAYMENT = "transfer_payment"
    val TRANSACTIONS_HISTORY = "transactions"
    val TRANSACTION_SUCCESS = "transaction_success"
    val MYCARDS_SELECTCURRENY = "mycards_selectcurrency"
    val MYCARDS_ADDCARDS = "mycards_addcards"
    val MYCARDS_LOADINGSCREEN = "mycards_loadingscreen"
    val MYCARDS_OTP = "mycards_otp"
    val MYCARDS_SUCCESFUL = "mycards_succesful"
    val MORE = "more"
    val MORE_FAV = "more_fav"
    val MORE_PROFILE = "more_profile"
    val PROFILE_INFORMATION = "profile_information"
    val PROFILE_SETTING = "profile_setting"
    val CHANGE_PASSWORD = "change_password"
    val EDIT_PROFILE = "edit_profile"
    val MY_CARDS = "my_cards"

}

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    val addCardViewModel: AddCardViewModel = viewModel()
    val signUpViewModel: SignUpViewModel = viewModel()


    NavHost(navController = navController, startDestination = AppRoutes.TRANSFER_HOME)
    {
        composable(route = SIGNUP1) { ScaffoldSignup(navController) }
        composable(route = AppRoutes.SIGNUP2) { SignUpScreenP2S(navController) }
        composable(route = AppRoutes.SIGNIN) { SigninScreen(navController) }
        composable(route = AppRoutes.TRANSFER_HOME) { ScaffoldtransMain(navController = navController) }
        composable(route = AppRoutes.TRANSFER_AMOUNT) { Scaffold_Transfer(navController) }
        composable(
            route = "${TRANSFER_CONFIRMATION}/{recipientName}/{recipientAccount}",
            arguments = listOf(navArgument("recipientName") { type = NavType.StringType },
                navArgument("recipientAccount") { type = NavType.StringType })
        ) { backStackEntry ->
            val recipientName = backStackEntry.arguments?.getString("recipientName") ?: ""
            val recipientAccount = backStackEntry.arguments?.getString("recipientAccount") ?: ""
            scaffoldConfirm(navController, recipientName, recipientAccount)
        }
        composable(route = "${AppRoutes.TRANSFER_PAYMENT}/{recipientname}/{recipientaccount}",
        arguments = listOf(navArgument("recipientname") { type = NavType.StringType },
            navArgument("recipientaccount") { type = NavType.StringType })
        ) { backStackEntry ->
        val recipientName = backStackEntry.arguments?.getString("recipientname") ?: ""
        val recipientAccount = backStackEntry.arguments?.getString("recipientaccount") ?: ""
        scaffoldConfirm(navController, recipientName, recipientAccount)
    }
        composable(route = AppRoutes.TRANSACTIONS_HISTORY) {
            ScaffololdTransactionScreen(
                navController
            )
        }
        composable(route = AppRoutes.TRANSACTION_SUCCESS) {
            ScaffololdSuccessTransactionScreen(
                navController
            )
        }
        composable(route = AppRoutes.MYCARDS_SELECTCURRENY) { ScaffoldCurrency(navController) }
        composable(route = AppRoutes.MYCARDS_ADDCARDS) { Scaffold_AddCard(navController) }
        // composable(route = AppRoutes.MYCARDS_LOADINGSCREEN) { ScaffoldBack(navController) }
        composable(route = AppRoutes.MYCARDS_OTP) { ScaffoldOtp(navController) }
        composable(route = AppRoutes.MYCARDS_SUCCESFUL) { ScaaffoldOTPend(navController) }
        composable(route = AppRoutes.MORE) { ScaffoldMoreMain(navController) }
        composable(route = AppRoutes.MORE_FAV) { ScaffoldFav(navController) }
        composable(route = AppRoutes.MORE_PROFILE) { ScaffoldProfile(navController) }
        composable(route = AppRoutes.PROFILE_INFORMATION) { ProfileInformationScreen(navController) }
        composable(route = AppRoutes.PROFILE_SETTING) { SettingScreen(navController) }
        composable(route = AppRoutes.CHANGE_PASSWORD) { ChangePasswordScreen(navController) }
        composable(route = AppRoutes.EDIT_PROFILE) {
            EditProfileScreen(
                navController,
                country = CountryList().getCountryList()
            )
        }
        composable(route = AppRoutes.MY_CARDS) { ScaffoldMyCardsMain(navController) }
    }
}