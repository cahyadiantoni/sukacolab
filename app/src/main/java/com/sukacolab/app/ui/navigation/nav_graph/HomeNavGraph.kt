package com.sukacolab.app.ui.navigation.nav_graph

import android.util.Log
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.sukacolab.app.ui.feature.bookmark.BookmarkScreen
import com.sukacolab.app.ui.feature.home.HomeScreen
import com.sukacolab.app.ui.feature.profile.ProfileScreen
import com.sukacolab.app.ui.feature.project.ProjectScreen
import com.sukacolab.app.ui.feature.projectDetail.ProjectDetailContent
import com.sukacolab.app.ui.feature.projectDetail.ProjectDetailScreen
import com.sukacolab.app.ui.feature.urProjectDetail.UrProjectDetailContent
import com.sukacolab.app.ui.navigation.PROJECT_ID
import com.sukacolab.app.ui.navigation.Screen

fun NavGraphBuilder.homeNavGraph(
    navController: NavHostController
) {
    composable(Screen.Home.route) {
        HomeScreen(navController = navController)
    }
    composable(Screen.Profile.route) {
        ProfileScreen()
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
