package com.example.healthcareapp.core.navigation.destinations

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.healthcareapp.core.navigation.Screen
import com.example.healthcareapp.presentation.results.ResultsScreen
import com.example.healthcareapp.presentation.results.ResultsViewModel

fun NavGraphBuilder.resultsScreenComposable(
    navController: NavController,
    showSnackBar : (String) -> Unit
){
    composable(Screen.ResultsScreen.route){
        val viewModel = hiltViewModel<ResultsViewModel>()
        ResultsScreen(viewModel = viewModel, showSnackBar = showSnackBar)
    }
}