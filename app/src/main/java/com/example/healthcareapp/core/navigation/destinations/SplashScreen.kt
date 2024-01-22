package com.example.healthcareapp.core.navigation.destinations

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.healthcareapp.core.navigation.Screen
import com.example.healthcareapp.presentation.splash.SplashScreen
import com.example.healthcareapp.presentation.splash.SplashViewModel

fun NavGraphBuilder.splashScreenComposable(
    navController: NavController
) {
    composable(
        route = Screen.SplashScreen.route
    ) {
        val viewModel = hiltViewModel<SplashViewModel>()
        SplashScreen(viewModel = viewModel)
    }
}