package com.sukacolab.app.ui.navigation.nav_graph

import android.util.Log
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.sukacolab.app.ui.feature.user.profile.sub_screen.certification.CertificationScreen
import com.sukacolab.app.ui.feature.user.profile.sub_screen.education.EducationScreen
import com.sukacolab.app.ui.feature.user.profile.sub_screen.experience.ExperienceScreen
import com.sukacolab.app.ui.feature.user.profile.sub_screen.resume.ResumeScreen
import com.sukacolab.app.ui.feature.user.profile.sub_screen.setting.SettingScreen
import com.sukacolab.app.ui.feature.user.profile.sub_screen.setting.email.SettingEmailScreen
import com.sukacolab.app.ui.feature.user.profile.sub_screen.skill.SkillScreen
import com.sukacolab.app.ui.navigation.CV_LINK
import com.sukacolab.app.ui.navigation.Screen

fun NavGraphBuilder.profileNavGraph(
    navController: NavHostController,
) {
    composable(Screen.Setting.route) {
        SettingScreen(navController = navController)
    }
    composable(Screen.SettingEmail.route) {
        SettingEmailScreen(navController = navController)
    }
    composable(
        route = Screen.Resume.route,
        arguments = listOf(
            navArgument(CV_LINK) {
                type = NavType.StringType
            }
        )
    ) {
        Log.d("Args", it.arguments?.getString(CV_LINK).toString())
        val cvLink = it.arguments?.getString(CV_LINK).toString()
        ResumeScreen(
            navController = navController,
            cvLink = cvLink,
        )
    }
    composable(Screen.Experience.route) {
        ExperienceScreen(navController = navController)
    }
    composable(Screen.Certification.route) {
        CertificationScreen(navController = navController)
    }
    composable(Screen.Skill.route) {
        SkillScreen(navController = navController)
    }
    composable(Screen.Education.route) {
        EducationScreen(navController = navController)
    }
}