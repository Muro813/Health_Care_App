package com.example.healthcareapp.core.navigation.destinations

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.healthcareapp.core.navigation.Screen
import com.example.healthcareapp.presentation.login.LoginScreen
import com.example.healthcareapp.presentation.login.LoginViewModel

fun NavGraphBuilder.loginScreenComposable(
    navController: NavController,
    showSnackBar : (String) -> Unit
) {
    composable(
        route = Screen.LoginScreen.route
    ) {
        val viewModel = hiltViewModel<LoginViewModel>()
        LoginScreen(viewModel = viewModel, showSnackBar = showSnackBar)
    }
}