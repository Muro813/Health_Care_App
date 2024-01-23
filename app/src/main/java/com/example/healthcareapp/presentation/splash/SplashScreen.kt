package com.example.healthcareapp.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.healthcareapp.ui.theme.HealthCareTheme
import kotlinx.coroutines.delay
import com.example.healthcareapp.R

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
            .background(
                brush = Brush.verticalGradient(
                    listOf(
                        HealthCareTheme.colors.secondaryColor,
                        HealthCareTheme.colors.primaryColor
                    )
                )
            )
            .padding(horizontal = 42.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null
        )
    }
}