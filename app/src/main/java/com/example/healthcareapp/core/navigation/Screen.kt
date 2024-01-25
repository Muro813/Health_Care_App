package com.example.healthcareapp.core.navigation

import com.example.healthcareapp.core.navigation.Routes.HOME_SCREEN
import com.example.healthcareapp.core.navigation.Routes.LOGIN_SCREEN
import com.example.healthcareapp.core.navigation.Routes.MEAL_SCREEN
import com.example.healthcareapp.core.navigation.Routes.SPLASH_SCREEN
import com.example.healthcareapp.core.navigation.destinations.MEAL_ID_ARGUMENT_KEY

sealed class Screen(val route: String) {
    data object SplashScreen : Screen(SPLASH_SCREEN)
    data object MealScreen : Screen(MEAL_SCREEN){
        fun passMealId(mealId : Int) =
            this.route.replace(
                oldValue = "{$MEAL_ID_ARGUMENT_KEY}",
                newValue = mealId.toString()
            )
    }
    data object LoginScreen : Screen(LOGIN_SCREEN)
    data object HomeScreen : Screen(HOME_SCREEN)
}