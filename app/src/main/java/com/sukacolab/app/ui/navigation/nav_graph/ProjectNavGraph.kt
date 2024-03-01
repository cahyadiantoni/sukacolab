package com.sukacolab.app.ui.navigation.nav_graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.sukacolab.app.ui.feature.user.ur_project.add_project.AddProjectScreen
import com.sukacolab.app.ui.navigation.Screen

fun NavGraphBuilder.projectNavGraph(
    navController: NavHostController,
) {
    composable(Screen.AddProject.route) {
        AddProjectScreen(navController = navController)
    }
}