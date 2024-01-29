package com.example.healthcareapp.core.navigation.destinations

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.healthcareapp.core.navigation.Screen
import com.example.healthcareapp.presentation.recipes.RecipesScreen
import com.example.healthcareapp.presentation.recipes.RecipesViewModel

fun NavGraphBuilder.recipesScreenComposable(
    navController: NavController,
    showSnackBar : (String) -> Unit
){
    composable(Screen.RecipesScreen.route){
        val viewModel = hiltViewModel<RecipesViewModel>()
        RecipesScreen(viewModel = viewModel, showSnackBar = showSnackBar)
    }
}