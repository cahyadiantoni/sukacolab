package com.sukacolab.app.ui.navigation.nav_graph

import android.util.Log
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.sukacolab.app.ui.feature.user.project.project_detail.ProjectDetailScreen
import com.sukacolab.app.ui.feature.user.ur_project.add_project.AddProjectScreen
import com.sukacolab.app.ui.feature.user.ur_project.ur_project_detail.UrProjectDetailScreen
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
        ProjectDetailScreen(navController = navController, idProject = projectId)
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
        UrProjectDetailScreen(navController = navController, idProject = projectId)
    }
}