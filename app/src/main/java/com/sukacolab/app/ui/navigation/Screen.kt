package com.sukacolab.app.ui.navigation

const val AUTH_GRAPH_ROUTE = "auth"
const val PROJECT_ID = "projectId"
const val CV_LINK = "cvLink"


sealed class Screen(val route: String) {
    object Onboarding : Screen("onboarding")
    object Home : Screen("home")
    object UrProject : Screen("ur_project")
    object Review : Screen("review")
    object UrProjectDetail : Screen("ur_project_detail")
    object AddProject : Screen("add_project")

    object Application : Screen("application")
    object Profile : Screen("profile")
    object ProfileEdit : Screen("profile_edit")
    object EditPhoto : Screen("edit_photo")


    object Login : Screen("login")
    object Register : Screen("register")
    object Splash : Screen("splash")
    object Project : Screen("project")
    object Bookmark : Screen("bookmark")
    object ProjectDetail : Screen("product_detail/{$PROJECT_ID}") {
        fun createRoute(projectId: Int) = "product_detail/$projectId"
    }
    object Resume : Screen("resume/{$CV_LINK}") {
        fun createRoute(cvLink: String) = "resume/$cvLink"
    }

    object Setting : Screen("setting")
    object SettingEmail : Screen("setting_email")
    object SettingPassword : Screen("setting_password")
    object EditAbout : Screen("edit_about")

    object Experience : Screen("experience")
    object AddExperience : Screen("add_experience")
    object EditExperience : Screen("edit_experience/{$PROJECT_ID}") {
        fun createRoute(productId: Int) = "edit_experience/$productId"
    }

    object Certification : Screen("certification")
    object AddCertification : Screen("add_certification")
    object EditCertification : Screen("edit_certification/{$PROJECT_ID}") {
        fun createRoute(productId: Int) = "edit_certification/$productId"
    }

    object Skill : Screen("skill")
    object AddSkill : Screen("add_skill")
    object EditSkill : Screen("edit_skill/{$PROJECT_ID}") {
        fun createRoute(productId: Int) = "edit_skill/$productId"
    }

    object Education : Screen("education")
    object AddEducation : Screen("add_education")
    object EditEducation : Screen("edit_education/{$PROJECT_ID}") {
        fun createRoute(productId: Int) = "edit_education/$productId"
    }

}
