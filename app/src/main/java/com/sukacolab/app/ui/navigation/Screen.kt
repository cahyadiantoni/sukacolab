package com.sukacolab.app.ui.navigation

const val AUTH_GRAPH_ROUTE = "auth"
const val PRODUCT_ID = "productId"

sealed class Screen(val route: String) {
    object Onboarding : Screen("onboarding")
    object Home : Screen("home")
    object Project : Screen("project")
    object Profile : Screen("profile")
    object Login : Screen("login")
    object Register : Screen("register")
    object Splash : Screen("splash")
    object UrProject : Screen("ur_project")
    object AppStatus : Screen("app_status")
    object Bookmark : Screen("bookmark")
    object ProjectDetail : Screen("project_detail")
    object UrProjectDetail : Screen("project_detail")
    object AddProject : Screen("add_project")
}
