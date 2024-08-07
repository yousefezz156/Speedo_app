package com.example.bm_app

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavController
import com.example.bm_app.approutes.AppRoutes
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun InactivieDialog(navController: NavController) {
    var isDialogVisible by remember { mutableStateOf(false) }
    val inactivityTimeout = 30 * 60 * 1000L

    val coroutineScope = rememberCoroutineScope()

    val resetTimer: () -> Unit = {
        isDialogVisible = false
        coroutineScope.launch {
            delay(inactivityTimeout)
            isDialogVisible = true
        }
    }

    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                resetTimer()
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    val logoutUser: () -> Unit = {
        navController.navigate(AppRoutes.SIGNIN) {
            popUpTo(AppRoutes.SPLASH_SCREEN) { inclusive = true }
        }
    }

    if (isDialogVisible) {
        AlertDialog(
            onDismissRequest = {},
            title = { Text(text = "inactive dialog") },
            text = { Text(text = "you have been inactive for 30 minutes, you will be logged out") },
            confirmButton = {
                Button(onClick = {
                    isDialogVisible = false
                    logoutUser()
                }) {
                    Text(text = "ok")
                }
            }
        )
    }
}
