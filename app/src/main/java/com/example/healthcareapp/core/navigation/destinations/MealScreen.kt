package com.example.healthcareapp.core.navigation.destinations

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.healthcareapp.core.navigation.Screen

const val MEAL_ID_ARGUMENT_KEY = "meal_id"

fun NavGraphBuilder.mealScreenComposable(
    navController: NavController,
    showSnackBar : (String) -> Unit
){
    composable(
        route = Screen.MealScreen.route,
        arguments = listOf(
            navArgument(MEAL_ID_ARGUMENT_KEY) {
                type = NavType.IntType
                defaultValue = -1

            }
        )
    ){ backStackEntry ->
//        val mealId = backStackEntry.arguments?.getInt(MEAL_ID_ARGUMENT_KEY) ?: -1
//        val viewModel = hiltViewModel<MealViewModel>()
//        MealScreen(viewModel = viewModel, mealId = mealId, showSnackBar = showSnackBar)
    }
}