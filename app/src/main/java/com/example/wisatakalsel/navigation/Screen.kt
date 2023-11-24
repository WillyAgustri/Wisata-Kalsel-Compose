package com.example.wisatakalsel.navigation

sealed class Screen(val route: String) {
    object Home : Screen("Home")
    object Favorite : Screen("Favorite")
    object About : Screen("About")
    object DetailTour : Screen("Home/{tourId}") {
        fun createRoute(tourId: Int) = "Home/$tourId"
    }
}
