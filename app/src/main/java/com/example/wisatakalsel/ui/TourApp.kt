package com.example.wisatakalsel.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.wisatakalsel.navigation.Screen
import com.example.wisatakalsel.ui.component.BottomBar
import com.example.wisatakalsel.ui.screen.AboutScreen
import com.example.wisatakalsel.ui.screen.DetailScreen
import com.example.wisatakalsel.ui.screen.FavoriteScreen
import com.example.wisatakalsel.ui.screen.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TourApp(
    modifier : Modifier = Modifier,
    navController : NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        modifier = modifier,
        bottomBar = {
            if (currentRoute == Screen.Home.route || currentRoute == Screen.Favorite.route || currentRoute == Screen.About.route){
                BottomBar(navController = navController)
            }
        }
    ){innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToDetail = { tourId ->
                    navController.navigate(Screen.DetailTour.createRoute(tourId))
                })
            }
            composable(Screen.Favorite.route) {
                FavoriteScreen(
                    navigateToDetail = { tourId ->
                        navController.navigate(Screen.DetailTour.createRoute(tourId))
                    }
                )
            }
            composable(Screen.About.route) {
                AboutScreen(
                )
            }
            composable(Screen.DetailTour.route,
                    arguments = listOf(
                    navArgument("tourId") { type = NavType.IntType }
                    )
            ) {
                val id = it.arguments?.getInt("tourId") ?: -1
                DetailScreen(
                    tourId = id,
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }
        }

    }
}