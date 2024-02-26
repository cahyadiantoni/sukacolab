package com.sukacolab.app.ui.navigation.nav_graph

import android.util.Log
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.sukacolab.app.ui.feature.user.profile.sub_screen.about.EditAboutScreen
import com.sukacolab.app.ui.feature.user.profile.sub_screen.certification.CertificationScreen
import com.sukacolab.app.ui.feature.user.profile.sub_screen.certification.add_certification.AddCertificationScreen
import com.sukacolab.app.ui.feature.user.profile.sub_screen.education.EducationScreen
import com.sukacolab.app.ui.feature.user.profile.sub_screen.education.add_education.AddEducationScreen
import com.sukacolab.app.ui.feature.user.profile.sub_screen.experience.ExperienceScreen
import com.sukacolab.app.ui.feature.user.profile.sub_screen.experience.add_experience.AddExperienceScreen
import com.sukacolab.app.ui.feature.user.profile.sub_screen.experience.edit_experience.EditExperienceScreen
import com.sukacolab.app.ui.feature.user.profile.sub_screen.resume.ResumeScreen
import com.sukacolab.app.ui.feature.user.profile.sub_screen.setting.SettingScreen
import com.sukacolab.app.ui.feature.user.profile.sub_screen.setting.email.SettingEmailScreen
import com.sukacolab.app.ui.feature.user.profile.sub_screen.setting.password.SettingPasswordScreen
import com.sukacolab.app.ui.feature.user.profile.sub_screen.skill.SkillScreen
import com.sukacolab.app.ui.feature.user.profile.sub_screen.skill.add_skill.AddSkillScreen
import com.sukacolab.app.ui.navigation.CV_LINK
import com.sukacolab.app.ui.navigation.PROJECT_ID
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
    composable(Screen.SettingPassword.route) {
        SettingPasswordScreen(navController = navController)
    }
    composable(Screen.EditAbout.route) {
        EditAboutScreen(navController = navController)
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
    composable(Screen.AddExperience.route) {
        AddExperienceScreen(navController = navController)
    }
    composable(
        route = Screen.EditExperience.route,
        arguments = listOf(
            navArgument(PROJECT_ID) {
                type = NavType.StringType
            }
        )
    ) {
        Log.d("Args", it.arguments?.getString(PROJECT_ID).toString())
        val id = it.arguments?.getString(PROJECT_ID).toString()
        EditExperienceScreen(
            navController = navController,
            id = id,
        )
    }
    composable(Screen.Certification.route) {
        CertificationScreen(navController = navController)
    }
    composable(Screen.AddCertification.route) {
        AddCertificationScreen(navController = navController)
    }
    composable(Screen.AddSkill.route) {
        AddSkillScreen(navController = navController)
    }
    composable(Screen.Skill.route) {
        SkillScreen(navController = navController)
    }
    composable(Screen.Education.route) {
        EducationScreen(navController = navController)
    }
    composable(Screen.AddEducation.route) {
        AddEducationScreen(navController = navController)
    }
}