package com.example.uasmuhammadalfinkhaerudin.presentation.home.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.uasmuhammadalfinkhaerudin.presentation.MainViewModel
import com.example.uasmuhammadalfinkhaerudin.presentation.home.HomeScreen
import com.example.uasmuhammadalfinkhaerudin.presentation.home.StartScreen
import com.example.uasmuhammadalfinkhaerudin.presentation.home.UpdateScreen

@Composable
fun AppNavigation(
    mainViewModel: MainViewModel
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.StartScreen.name
    ) {
        composable(
            route = Screen.StartScreen.name
        ) {
            StartScreen(
                startApp = {
                    navController.navigate(
                        route = Screen.HomeScreen.name
                    )
                }
            )
        }
        composable(
            route = Screen.HomeScreen.name
        ) {
            HomeScreen(
                mainViewModel = mainViewModel,
                onUpdate = {id ->
                    navController.navigate(
                        route = "${Screen.UpdateScreen.name}/$id"
                    )
                }
            )
        }
        composable(
            route = "${Screen.UpdateScreen.name}/{id}",
            arguments = listOf(navArgument("id"){
                type = NavType.IntType
            }),
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = {-it},
                    animationSpec = tween(300)
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = {-it},
                    animationSpec = tween(300)
                )
            }
        ){navBackStackEntry ->
            navBackStackEntry.arguments?.getInt("id").let { id ->
                UpdateScreen(
                    id = id!!,
                    mainViewModel = mainViewModel,
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}