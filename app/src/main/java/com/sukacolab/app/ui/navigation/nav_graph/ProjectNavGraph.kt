package com.sukacolab.app.ui.navigation.nav_graph

import android.util.Log
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.sukacolab.app.ui.feature.user.profile.ProfileScreen
import com.sukacolab.app.ui.feature.user.profile.sub_screen.experience.ExperienceScreen
import com.sukacolab.app.ui.feature.user.profile.sub_screen.resume.ResumeScreen
import com.sukacolab.app.ui.feature.user.profile_other.ProfileOtherScreen
import com.sukacolab.app.ui.feature.user.profile_other.sub_screen.certification.CertificationOtherScreen
import com.sukacolab.app.ui.feature.user.profile_other.sub_screen.education.EducationOtherScreen
import com.sukacolab.app.ui.feature.user.profile_other.sub_screen.experience.ExperienceOtherScreen
import com.sukacolab.app.ui.feature.user.profile_other.sub_screen.resume.ResumeOtherScreen
import com.sukacolab.app.ui.feature.user.profile_other.sub_screen.skill.SkillOtherScreen
import com.sukacolab.app.ui.feature.user.project.project_detail.ProjectDetailScreen
import com.sukacolab.app.ui.feature.user.ur_project.add_project.AddProjectScreen
import com.sukacolab.app.ui.feature.user.ur_project.ur_project_detail.UrProjectDetailScreen
import com.sukacolab.app.ui.navigation.CV_LINK
import com.sukacolab.app.ui.navigation.PROFILE_ID
import com.sukacolab.app.ui.navigation.PROJECT_ID
import com.sukacolab.app.ui.navigation.Screen

fun NavGraphBuilder.projectNavGraph(
    navController: NavHostController,
) {
    composable(Screen.AddProject.route) {
        AddProjectScreen(navController = navController)
    }

    composable(
        route = Screen.ProjectDetail.route,
        arguments = listOf(
            navArgument(PROJECT_ID) {
                type = NavType.IntType
            }
        )
    ) {
        Log.d("Args", it.arguments?.getInt(PROJECT_ID).toString())
        val projectId = it.arguments?.getInt(PROJECT_ID).toString()
        ProjectDetailScreen(navController = navController, projectId = projectId)
    }

    composable(
        route = Screen.UrProjectDetail.route,
        arguments = listOf(
            navArgument(PROJECT_ID) {
                type = NavType.IntType
            }
        )
    ) {
        Log.d("Args", it.arguments?.getInt(PROJECT_ID).toString())
        val projectId = it.arguments?.getInt(PROJECT_ID).toString()
        UrProjectDetailScreen(navController = navController, projectId = projectId)
    }

    composable(
        route = Screen.ProfileOther.route,
        arguments = listOf(
            navArgument(PROFILE_ID) {
                type = NavType.StringType
            },
            navArgument(PROJECT_ID) {
                type = NavType.StringType
            }
        )
    ) {
        val profileId = it.arguments?.getString(PROFILE_ID).toString()
        val projectId = it.arguments?.getString(PROJECT_ID).toString()
        Log.d("Args", "Profile ID: $profileId, Project ID: $projectId")
        ProfileOtherScreen(navController = navController, userId = profileId, projectId = projectId)
    }

    composable(
        route = Screen.ResumeOther.route,
        arguments = listOf(
            navArgument(CV_LINK) {
                type = NavType.StringType
            }
        )
    ) {
        Log.d("Args", it.arguments?.getString(CV_LINK).toString())
        val cvLink = it.arguments?.getString(CV_LINK).toString()
        ResumeOtherScreen(
            navController = navController,
            cvLink = cvLink,
        )
    }

    composable(
        route = Screen.CertificationOther.route,
        arguments = listOf(
            navArgument(PROFILE_ID) {
                type = NavType.IntType
            }
        )
    ) {
        Log.d("Args", it.arguments?.getInt(PROFILE_ID).toString())
        val userId = it.arguments?.getInt(PROFILE_ID).toString()
        CertificationOtherScreen(navController = navController, userId = userId)
    }

    composable(
        route = Screen.EducationOther.route,
        arguments = listOf(
            navArgument(PROFILE_ID) {
                type = NavType.IntType
            }
        )
    ) {
        Log.d("Args", it.arguments?.getInt(PROFILE_ID).toString())
        val userId = it.arguments?.getInt(PROFILE_ID).toString()
        EducationOtherScreen(navController = navController, userId = userId)
    }

    composable(
        route = Screen.ExperienceOther.route,
        arguments = listOf(
            navArgument(PROFILE_ID) {
                type = NavType.IntType
            }
        )
    ) {
        Log.d("Args", it.arguments?.getInt(PROFILE_ID).toString())
        val userId = it.arguments?.getInt(PROFILE_ID).toString()
        ExperienceOtherScreen(navController = navController, userId = userId)
    }

    composable(
        route = Screen.SkillOther.route,
        arguments = listOf(
            navArgument(PROFILE_ID) {
                type = NavType.IntType
            }
        )
    ) {
        Log.d("Args", it.arguments?.getInt(PROFILE_ID).toString())
        val userId = it.arguments?.getInt(PROFILE_ID).toString()
        SkillOtherScreen(navController = navController, userId = userId)
    }
}