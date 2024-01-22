package com.example.healthcareapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.healthcareapp.core.navigation.Navigator
import com.example.healthcareapp.core.navigation.graphs.HealthCareNavigation
import com.example.healthcareapp.core.utils.CustomModifiers
import com.example.healthcareapp.core.utils.rememberAppState
import com.example.healthcareapp.ui.theme.HealthCareTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    internal lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            HealthCareTheme {
                val appState = rememberAppState()
//                var bottomBarState by remember { (mutableStateOf(false)) }
                val navBackStackEntry by appState.navController.currentBackStackEntryAsState()

//                bottomBarState = when (navBackStackEntry?.destination?.route) {
//                    Screen.MealPlanScreen.route -> true
//                    Screen.SavedRecipesScreen.route -> true
//                    Screen.SettingsScreen.route -> true
//                    Screen.HelpAndSupportScreen.route -> true
//                    else -> false
//                }

                CompositionLocalProvider(
                    LocalDensity provides Density(
                        LocalDensity.current.density,
                        1f // - we set here default font scale instead of system one
                    )
                ) {
                    Scaffold(
                        bottomBar = {
//                            BottomNavigationBar(
//                                items = listOf(
//                                    BottomNavItem(
//                                        name = stringResource(id = R.string.meal_plan),
//                                        route = Screen.MealPlanScreen.route,
//                                        icon = R.drawable.ic_meal_plan
//                                    ),
//                                    BottomNavItem(
//                                        name = stringResource(id = R.string.saved_recipes),
//                                        route = Screen.SavedRecipesScreen.route,
//                                        icon = R.drawable.ic_bookmark
//                                    ),
//                                    BottomNavItem(
//                                        name = stringResource(id = R.string.settings),
//                                        route = Screen.SettingsScreen.route,
//                                        icon = R.drawable.ic_settings
//                                    ),
//                                    BottomNavItem(
//                                        name = stringResource(id = R.string.help_support),
//                                        route = Screen.HelpAndSupportScreen.route,
//                                        icon = R.drawable.ic_help_support
//                                    )
//                                ),
//                                navController = appState.navController,
//                                onItemClick = {
//                                    appState.navController.navigate(it.route) {
//                                        popUpTo(Screen.MealPlanScreen.route) {
//                                            inclusive = it.route == Screen.MealPlanScreen.route
//                                        }
//                                    }
//                                },
//                                bottomBarState = bottomBarState,
//                                modifier = Modifier
//                                    .height(90.dp)
//                                    .gradientBackground(
//                                        colors = listOf(
//                                            HealthCareTheme.colors.primaryColor,
//                                            HealthCareTheme.colors.secondaryColor
//                                        ),
//                                        angle = -87f
//                                    )
//                            )
                        },
                        scaffoldState = appState.scaffoldState,
                        snackbarHost = CustomModifiers.snackBarHost
                    ) { innerPadding ->
                        HealthCareNavigation(
                            navController = appState.navController,
                            navigator = navigator,
                            showSnackBar = { message ->
                                appState.showSnackBar(message = message)
                            },
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}