package com.example.healthcareapp.core.navigation

import com.example.healthcareapp.core.navigation.Routes.HOME_SCREEN
import com.example.healthcareapp.core.navigation.Routes.LOGIN_SCREEN
import com.example.healthcareapp.core.navigation.Routes.RECIPES_SCREEN
import com.example.healthcareapp.core.navigation.Routes.RESULTS_SCREEN
import com.example.healthcareapp.core.navigation.Routes.RESULT_INFO_SCREEN
import com.example.healthcareapp.core.navigation.Routes.SPLASH_SCREEN
import com.example.healthcareapp.core.navigation.destinations.RESULT_ID_ARGUMENT_KEY

sealed class Screen(val route: String) {
    data object SplashScreen : Screen(SPLASH_SCREEN)
    data object ResultInfoScreen : Screen(RESULT_INFO_SCREEN){
        fun passResultId(resultId : Int) =
            this.route.replace(
                oldValue = "{$RESULT_ID_ARGUMENT_KEY}",
                newValue = resultId.toString()
            )
    }
    data object LoginScreen : Screen(LOGIN_SCREEN)
    data object HomeScreen : Screen(HOME_SCREEN)
    data object RecipesScreen : Screen(RECIPES_SCREEN)
    data object ResultsScreen : Screen(RESULTS_SCREEN)
}