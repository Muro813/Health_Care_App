package com.example.healthcareapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.healthcareapp.core.components.DrawerContent
import com.example.healthcareapp.core.navigation.Navigator
import com.example.healthcareapp.core.navigation.Screen
import com.example.healthcareapp.core.navigation.graphs.HealthCareNavigation
import com.example.healthcareapp.core.utils.CustomModifiers
import com.example.healthcareapp.core.utils.rememberAppState
import com.example.healthcareapp.domain.model.DrawerNavItem
import com.example.healthcareapp.ui.theme.HealthCareTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import com.example.healthcareapp.R
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    internal lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            HealthCareTheme {
                val scope = rememberCoroutineScope()
                val appState = rememberAppState()
//                var bottomBarState by remember { (mutableStateOf(false)) }
                val navBackStackEntry by appState.navController.currentBackStackEntryAsState()
                var drawerState by remember { mutableStateOf(false)}
//                bottomBarState = when (navBackStackEntry?.destination?.route) {
//                    Screen.MealPlanScreen.route -> true
//                    Screen.SavedRecipesScreen.route -> true
//                    Screen.SettingsScreen.route -> true
//                    Screen.HelpAndSupportScreen.route -> true
//                    else -> false
//                }
                drawerState = when (navBackStackEntry?.destination?.route){
                    Screen.HomeScreen.route -> true
                    Screen.ResultsScreen.route -> true
                    Screen.RecipesScreen.route -> true
                    else -> false
                }
                CompositionLocalProvider(
                    LocalDensity provides Density(
                        LocalDensity.current.density,
                        1f // - we set here default font scale instead of system one
                    )
                ) {
                    Scaffold(
                        drawerContent = {
                                        DrawerContent(
                                            items = listOf(
                                                DrawerNavItem(
                                                    name = "Home",
                                                    route = Screen.HomeScreen.route,
                                                    R.drawable.ic_home
                                                ),
                                                DrawerNavItem(
                                                    name = "Receipti",
                                                    route = Screen.RecipesScreen.route,
                                                    R.drawable.ic_recipes
                                                ),
                                                DrawerNavItem(
                                                    name = "Nalazi",
                                                    route = Screen.ResultsScreen.route,
                                                    R.drawable.ic_results
                                                )
                                            ),
                                            drawerState = drawerState,
                                            onItemClick = {
                                                scope.launch {
                                                    appState.scaffoldState.drawerState.close()
                                                }
                                                appState.navController.navigate(it) {
                                                    popUpTo(Screen.HomeScreen.route) {
                                                        inclusive = it == Screen.HomeScreen.route
                                                    }
                                                }
                                            },
                                            onLogOutClick = {
                                                scope.launch {
                                                    appState.scaffoldState.drawerState.close()
                                                }
                                                appState.navController.navigate(Screen.LoginScreen.route){
                                                    popUpTo(Screen.LoginScreen.route){
                                                        inclusive = true
                                                    }
                                                }
                                            }
                                        )
                        },
                        drawerGesturesEnabled = drawerState,
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