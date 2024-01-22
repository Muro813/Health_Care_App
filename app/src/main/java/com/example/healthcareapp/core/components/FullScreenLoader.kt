package com.example.healthcareapp.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.healthcareapp.ui.theme.HealthCareTheme
import com.example.healthcareapp.R

@Composable
fun FullScreenLoader(
    shouldShowLoader : Boolean,
    dismissOnBackPress : Boolean = false,
    dismissOnClickOutside : Boolean = false
) {
    if(shouldShowLoader){
        Dialog(
            onDismissRequest = {},
            properties = DialogProperties(
                dismissOnBackPress = dismissOnBackPress,
                dismissOnClickOutside = dismissOnClickOutside,
                usePlatformDefaultWidth = false
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(HealthCareTheme.colors.white)
            ){
                val compositionResult =
                    rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.loader))

                val progress by animateLottieCompositionAsState(
                    compositionResult.value,
                    isPlaying = true,
                    iterations = LottieConstants.IterateForever,
                    speed = 1f
                )
                LottieAnimation(
                    modifier = Modifier.align(Alignment.Center),
                    composition = compositionResult.value,
                    progress = { progress }
                )
            }
        }
    }
}