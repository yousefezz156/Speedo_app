import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.microblink.blinkcard.core.session.BlinkCardSessionSettings
import com.microblink.blinkcard.ux.BlinkCardCameraScanningScreen
import com.microblink.blinkcard.ux.UiSettings
import com.microblink.blinkcard.ux.camera.CameraSettings
import com.microblink.blinkcard.ux.settings.BlinkCardUxSettings
import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.bm_app.approutes.AppRoutes
import com.example.bm_app.mycard.CardDetails
import com.example.bm_app.viewModel.AddCardViewModel
import com.microblink.blinkcard.core.BlinkCardSdk
import com.microblink.blinkcard.core.BlinkCardSdkSettings


@Composable
fun ShowCam(navController: NavController,addCardViewModel: AddCardViewModel,modifier: Modifier = Modifier) {
    val context = LocalContext.current

    // 1) Camera permission state
    var hasCameraPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        hasCameraPermission = isGranted
    }

    // 2) SDK state
    var sdkInstance by remember { mutableStateOf<BlinkCardSdk?>(null) }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    // Ask permission once when screen opens
    LaunchedEffect(Unit) {
        if (!hasCameraPermission) {
            permissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    // Initialize SDK only after permission granted
    LaunchedEffect(hasCameraPermission) {
        if (hasCameraPermission && sdkInstance == null && !isLoading) {
            isLoading = true

            val maybeInstance = BlinkCardSdk.initializeSdk(
                context,
                BlinkCardSdkSettings(
                    licenseKey = "sRwCABJjb20uZXhhbXBsZS5ibV9hcHAAbGV5SkRjbVZoZEdWa1QyNGlPakUzTnpNMU1ERTJNVEk1TVRZc0lrTnlaV0YwWldSR2IzSWlPaUkzT1dOaVltRmpaaTAwT0daa0xUUXdZVFV0WVRNeU9TMDNORGsyTldGaVlUTm1NR0lpZlE9PVobA4jvK0Esbhbg4dsFV2h8HNYZDi6R678cCOlX4HX17BRLesSOfaTQACX8oY/2aneqj87pDEh828s3nbXq9v0khghzi39cpuqQRonPO582G6XKPSRIuvQG1YgWaA=="
                )
            )

            when {
                maybeInstance.isSuccess -> {
                    sdkInstance = maybeInstance.getOrNull()
                    Log.d("BlinkCard", "SDK initialized successfully")
                }

                maybeInstance.isFailure -> {
                    val exception = maybeInstance.exceptionOrNull()
                    errorMessage = exception?.message ?: "Failed to initialize SDK"
                    Log.e("BlinkCard", "Initialization failed", exception)
                }
            }

            isLoading = false
        }
    }

    when {
        !hasCameraPermission -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Button(onClick = {
                    permissionLauncher.launch(Manifest.permission.CAMERA)
                }) {
                    Text("Grant Camera Permission")
                }
            }
        }

        isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        errorMessage != null -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Error: $errorMessage")
            }
        }

        sdkInstance != null -> {
            BlinkCardCameraScanningScreen(
                blinkCardSdk = sdkInstance!!,
                uxSettings = BlinkCardUxSettings(),
                uiSettings = UiSettings(),
                cameraSettings = CameraSettings(),
                sessionSettings = BlinkCardSessionSettings(),
                onScanningSuccess = { scanningResult ->

                    Log.d("BlinkCard", "========= SCAN SUCCESS =========")
                    Log.d("BlinkCard", "Issuing Network: ${scanningResult.issuingNetwork}")
                    Log.d("BlinkCard", "Cardholder Name: ${scanningResult.cardholderName}")
                    Log.d("BlinkCard", "IBAN: ${scanningResult.iban}")
                    Log.d("BlinkCard", "Card Accounts Count: ${scanningResult.cardAccounts.size}")

                    scanningResult.cardAccounts.forEachIndexed { index, account ->
                        Log.d("BlinkCard", "---- Account #$index ----")
                        Log.d("BlinkCard", "Card Number: ${account.cardNumber}")
                        Log.d("BlinkCard", "Card Number Valid: ${account.cardNumberValid}")
                        Log.d("BlinkCard", "Card Number Prefix: ${account.cardNumberPrefix}")
                        Log.d("BlinkCard", "CVV: ${account.cvv}")
                        Log.d("BlinkCard", "Expiry Date: ${account.expiryDate}")
                        Log.d("BlinkCard", "Funding Type: ${account.fundingType}")
                        Log.d("BlinkCard", "Card Category: ${account.cardCategory}")
                        Log.d("BlinkCard", "Issuer Name: ${account.issuerName}")
                        Log.d("BlinkCard", "Issuer Country Code: ${account.issuerCountryCode}")
                        Log.d("BlinkCard", "Issuer Country: ${account.issuerCountry}")

                        addCardViewModel.addCard(CardDetails(
                            cardNumber = account.cardNumber,
                            cardHolderName = scanningResult.cardholderName,
                            expiryDate = account.expiryDate.toString(),
                            cvv = account.cvv,
                        ))

                    }

                    Log.d("BlinkCard", "===============================")
                    navController.navigate(AppRoutes.MYCARDS_ADDCARDS)
                },
                onScanningCanceled = {
                    Log.d("BlinkCard", "User canceled scanning")
                    navController.navigate(AppRoutes.MYCARDS_ADDCARDS)

                }
            )
        }
    }
}