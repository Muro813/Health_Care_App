package com.example.healthcareapp.core.navigation.destinations

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.healthcareapp.core.navigation.Screen
import com.example.healthcareapp.presentation.result_info.ResultInfoScreen
import com.example.healthcareapp.presentation.result_info.ResultInfoViewModel

const val RESULT_ID_ARGUMENT_KEY = "meal_id"

fun NavGraphBuilder.resultInfoScreenComposable(
    navController: NavController,
    showSnackBar : (String) -> Unit
){
    composable(
        route = Screen.ResultInfoScreen.route,
        arguments = listOf(
            navArgument(RESULT_ID_ARGUMENT_KEY) {
                type = NavType.IntType
                defaultValue = -1

            }
        )
    ){ backStackEntry ->
        val resultId = backStackEntry.arguments?.getInt(RESULT_ID_ARGUMENT_KEY) ?: -1
        val viewModel = hiltViewModel<ResultInfoViewModel>()
        ResultInfoScreen(viewModel = viewModel, resultId = resultId, showSnackBar = showSnackBar)
    }
}