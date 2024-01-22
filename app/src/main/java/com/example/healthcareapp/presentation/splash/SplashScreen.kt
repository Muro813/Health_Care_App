package com.example.healthcareapp.presentation.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.healthcareapp.ui.theme.HealthCareTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    viewModel : SplashViewModel
) {
    LaunchedEffect(key1 = Unit){
        delay(1000)
        viewModel.onEvent(SplashEvent.GoNextScreen)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(HealthCareTheme.colors.white)
            .padding(horizontal = 42.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
    }
}