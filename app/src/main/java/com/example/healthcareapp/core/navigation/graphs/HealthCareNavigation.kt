package com.example.healthcareapp.core.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.healthcareapp.core.navigation.NavType
import com.example.healthcareapp.core.navigation.Navigator
import com.example.healthcareapp.core.navigation.Routes.HOME
import com.example.healthcareapp.core.navigation.Routes.ROOT
import com.example.healthcareapp.core.utils.observeWithLifecycle

@Composable
fun HealthCareNavigation(
    navController: NavHostController,
    navigator: Navigator,
    showSnackBar: (message: String) -> Unit,
    modifier: Modifier = Modifier
) {
    navigator.navigationFlow.observeWithLifecycle { navType ->
        navigate(navController = navController, navType = navType)
    }

    NavHost(
        navController = navController,
        route = ROOT,
        startDestination = HOME,
        modifier = modifier
    ) {
        splashNavGraph(navController = navController, showSnackBar = showSnackBar)
    }


}

private fun navigate(
    navController: NavHostController,
    navType: NavType
) {
    when (navType) {
        is NavType.NavigateToRoute -> {
            navController.navigate(navType.route)
        }

        is NavType.PopToRoute -> {
            navController.navigate(navType.route) {
                popUpTo(navType.staticRoute) {
                    inclusive = navType.inclusive
                }
            }
        }

        is NavType.PopBackStack -> {
            if (navType.route != null && navType.inclusive != null)
                navController.popBackStack(route = navType.route, inclusive = navType.inclusive)
            else navController.popBackStack()
        }

        is NavType.NavigateUp -> {
            navController.navigateUp()
        }
    }
}