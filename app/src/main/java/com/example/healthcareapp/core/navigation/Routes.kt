package com.example.healthcareapp.core.navigation

import com.example.healthcareapp.core.navigation.destinations.MEAL_ID_ARGUMENT_KEY

object Routes {
    const val ROOT = "root"
    const val HOME = "home"
    const val SPLASH_SCREEN = "splash_screen"
    const val MEAL_SCREEN = "meal_screen/{$MEAL_ID_ARGUMENT_KEY}"
    const val LOGIN_SCREEN = "login_screen"
    const val HOME_SCREEN = "home_screen"
    const val RECIPES_SCREEN = "recipes_screen"
    const val RESULTS_SCREEN = "results_screen"
}