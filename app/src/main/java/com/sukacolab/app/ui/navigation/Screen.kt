package com.sukacolab.app.ui.navigation

const val AUTH_GRAPH_ROUTE = "auth"
const val PROJECT_ID = "projectId"
const val PROFILE_ID = "profileId"
const val CATEGORY = "category"
const val CV_LINK = "cvLink"


sealed class Screen(val route: String) {
    object Onboarding : Screen("onboarding")
    object Home : Screen("home")
    object UrProject : Screen("ur_project")
    object Review : Screen("review")
    object AddProject : Screen("add_project")

    object Application : Screen("application")
    object Profile : Screen("profile")
    object ProfileOther : Screen("profile_other/{$PROFILE_ID}/{$PROJECT_ID}") {
        fun createRoute(profileId: Int, projectId: Int) = "profile_other/$profileId/$projectId"
    }
    object ProfileEdit : Screen("profile_edit")
    object EditPhoto : Screen("edit_photo")


    object Login : Screen("login")
    object Register : Screen("register")
    object Splash : Screen("splash")
    object Project : Screen("project")
    object Bookmark : Screen("bookmark")
    object Search : Screen("search")
    object Category : Screen("category/{$CATEGORY}") {
        fun createRoute(category: String) = "category/$category"
    }
    object ProjectDetail : Screen("product_detail/{$PROJECT_ID}") {
        fun createRoute(projectId: Int) = "product_detail/$projectId"
    }
    object UrProjectDetail : Screen("ur_product_detail/{$PROJECT_ID}") {
        fun createRoute(projectId: Int) = "ur_product_detail/$projectId"
    }
    object DetailReview : Screen("detail_review/{$PROJECT_ID}") {
        fun createRoute(projectId: Int) = "detail_review/$projectId"
    }
    object Resume : Screen("resume/{$CV_LINK}") {
        fun createRoute(cvLink: String) = "resume/$cvLink"
    }
    object ResumeOther : Screen("resume_other/{$CV_LINK}") {
        fun createRoute(cvLink: String) = "resume_other/$cvLink"
    }

    object Setting : Screen("setting")
    object SettingEmail : Screen("setting_email")
    object SettingPassword : Screen("setting_password")
    object EditAbout : Screen("edit_about")

    object Experience : Screen("experience")
    object ExperienceOther : Screen("experience_other/{$PROFILE_ID}") {
        fun createRoute(userId: String) = "experience_other/$userId"
    }
    object AddExperience : Screen("add_experience")
    object EditExperience : Screen("edit_experience/{$PROJECT_ID}") {
        fun createRoute(productId: Int) = "edit_experience/$productId"
    }

    object Certification : Screen("certification")
    object CertificationOther : Screen("certification_other/{$PROFILE_ID}") {
        fun createRoute(userId: String) = "certification_other/$userId"
    }
    object AddCertification : Screen("add_certification")
    object EditCertification : Screen("edit_certification/{$PROJECT_ID}") {
        fun createRoute(productId: Int) = "edit_certification/$productId"
    }

    object Skill : Screen("skill")
    object SkillOther : Screen("skill_other/{$PROFILE_ID}") {
        fun createRoute(userId: String) = "skill_other/$userId"
    }
    object AddSkill : Screen("add_skill")
    object EditSkill : Screen("edit_skill/{$PROJECT_ID}") {
        fun createRoute(productId: Int) = "edit_skill/$productId"
    }

    object Education : Screen("education")
    object EducationOther : Screen("education_other/{$PROFILE_ID}") {
        fun createRoute(userId: String) = "education_other/$userId"
    }
    object AddEducation : Screen("add_education")
    object EditEducation : Screen("edit_education/{$PROJECT_ID}") {
        fun createRoute(productId: Int) = "edit_education/$productId"
    }

}
