package com.sukacolab.app.ui.navigation

const val AUTH_GRAPH_ROUTE = "auth"
const val PROJECT_ID = "projectId"
const val CV_LINK = "cvLink"


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
    object Resume : Screen("resume/{$CV_LINK}") {
        fun createRoute(cvLink: String) = "resume/$cvLink"
    }

    object Setting : Screen("setting")
    object SettingEmail : Screen("setting_email")

    object Experience : Screen("experience")
    object Certification : Screen("certification")
    object Skill : Screen("skill")
    object Education : Screen("education")

    //admin
    object HomeAdmin : Screen("home_admin")

}
