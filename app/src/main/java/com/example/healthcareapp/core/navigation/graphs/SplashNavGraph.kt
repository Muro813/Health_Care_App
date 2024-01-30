package com.example.healthcareapp.core.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.example.healthcareapp.core.navigation.Routes.HOME
import com.example.healthcareapp.core.navigation.Screen
import com.example.healthcareapp.core.navigation.destinations.homeScreenComposable
import com.example.healthcareapp.core.navigation.destinations.loginScreenComposable
import com.example.healthcareapp.core.navigation.destinations.recipesScreenComposable
import com.example.healthcareapp.core.navigation.destinations.resultInfoScreenComposable
import com.example.healthcareapp.core.navigation.destinations.resultsScreenComposable
import com.example.healthcareapp.core.navigation.destinations.splashScreenComposable

fun NavGraphBuilder.splashNavGraph(
    navController: NavController, showSnackBar: (message: String) -> Unit
) {

    navigation(
        route = HOME, startDestination = Screen.SplashScreen.route
    ) {
        splashScreenComposable(navController = navController)
        resultInfoScreenComposable(navController = navController, showSnackBar = showSnackBar)
        loginScreenComposable(navController = navController)
        homeScreenComposable(navController = navController, showSnackBar = showSnackBar)
        resultsScreenComposable(navController = navController,showSnackBar = showSnackBar)
        recipesScreenComposable(navController = navController, showSnackBar = showSnackBar)
    }

}