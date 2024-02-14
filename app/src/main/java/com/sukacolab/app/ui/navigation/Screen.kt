package com.sukacolab.app.ui.navigation

const val AUTH_GRAPH_ROUTE = "auth"

sealed class Screen(val route: String) {
    object Onboarding : Screen("onboarding")
    object Home : Screen("home")
    object Lamaran : Screen("lamaran")
    object Project : Screen("project")
    object Profile : Screen("profile")
    object Login : Screen("login")
    object Register : Screen("register")
    object Splash : Screen("splash")
}
