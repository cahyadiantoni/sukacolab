package com.sukacolab.app.ui.navigation.nav_graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.sukacolab.app.ui.feature.user.profile.subScreen.certification.CertificationScreen
import com.sukacolab.app.ui.feature.user.profile.subScreen.education.EducationScreen
import com.sukacolab.app.ui.feature.user.profile.subScreen.experience.ExperienceScreen
import com.sukacolab.app.ui.feature.user.profile.subScreen.skill.SkillScreen
import com.sukacolab.app.ui.navigation.Screen

fun NavGraphBuilder.profileNavGraph(
    navController: NavHostController,
) {
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