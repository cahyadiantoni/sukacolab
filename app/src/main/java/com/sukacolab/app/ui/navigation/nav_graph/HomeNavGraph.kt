package com.sukacolab.app.ui.navigation.nav_graph

import android.util.Log
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.sukacolab.app.ui.feature.admin.home_admin.HomeAdminScreen
import com.sukacolab.app.ui.feature.user.bookmark.BookmarkScreen
import com.sukacolab.app.ui.feature.user.home.HomeScreen
import com.sukacolab.app.ui.feature.user.profile.ProfileScreen
import com.sukacolab.app.ui.feature.user.profile.sub_screen.resume.ResumeScreen
import com.sukacolab.app.ui.feature.user.project.ProjectScreen
import com.sukacolab.app.ui.feature.user.project_detail.ProjectDetailScreen
import com.sukacolab.app.ui.navigation.CV_LINK
import com.sukacolab.app.ui.navigation.PROJECT_ID
import com.sukacolab.app.ui.navigation.Screen

fun NavGraphBuilder.homeNavGraph(
    navController: NavHostController,
) {
    //admin
    composable(Screen.HomeAdmin.route) {
        HomeAdminScreen(navController = navController)
    }

    //user
    composable(Screen.Home.route) {
        HomeScreen(navController = navController)
    }
    composable(Screen.Profile.route) {
        ProfileScreen(navController = navController)
    }
    composable(Screen.Project.route) {
        ProjectScreen()
    }
    composable(Screen.Bookmark.route) {
        BookmarkScreen(navController = navController)
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
        ProjectDetailScreen()
    }
}
