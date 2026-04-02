package com.example.bm_app.mycard

import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun CameraPrev( controller: LifecycleCameraController, modifier: Modifier = Modifier){

    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(lifecycleOwner) {
        controller.bindToLifecycle(lifecycleOwner)

    }
    AndroidView(
        factory = {
            PreviewView(it).apply {
                this.controller = controller
            }
        }
        , modifier = modifier
    )


}