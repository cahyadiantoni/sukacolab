package com.sukacolab.app.ui.navigation

const val AUTH_GRAPH_ROUTE = "auth"
const val PROJECT_ID = "projectId"

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
    object ProjectDetail : Screen("product_detail/{$PROJECT_ID}") {
        fun createRoute(projectId: Int) = "product_detail/$projectId"
    }
    object UrProjectDetail : Screen("ur_project_detail")
    object AddProject : Screen("add_project")
}
