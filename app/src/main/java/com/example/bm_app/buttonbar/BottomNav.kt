package com.example.bm_app.buttonbar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bm_app.transfer.Scaffold_Transfer
import com.example.bm_app.transfer.ScaffoldtransMain
import com.example.bm_app.transfer.scaffoldConfirm

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) { ScaffoldtransMain(navController) }
        composable(Screen.Transfer.route) { Scaffold_Transfer(navController) }
//        composable(Screen.Transactions.route) { TransactionsScreen(navController) }
//        composable(Screen.MyCards.route) { MyCardsScreen(navController) }
//        composable(Screen.More.route) { MoreScreen(navController) }
    }
}
