package com.example.healthcareapp.core.navigation.destinations

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.healthcareapp.core.navigation.Screen
import com.example.healthcareapp.presentation.home.HomeScreen
import com.example.healthcareapp.presentation.home.HomeViewModel

fun NavGraphBuilder.homeScreenComposable(
    navController: NavController,
    showSnackBar : (String) -> Unit
){
    composable(Screen.HomeScreen.route){
        val viewModel = hiltViewModel<HomeViewModel>()
        HomeScreen(viewModel = viewModel, showSnackBar = showSnackBar)
    }
}