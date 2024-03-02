package com.sukacolab.app.ui.navigation.nav_graph

import android.util.Log
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.sukacolab.app.ui.feature.admin.review.ReviewScreen
import com.sukacolab.app.ui.feature.user.application.ApplicationScreen
import com.sukacolab.app.ui.feature.user.application.bookmark.BookmarkScreen
import com.sukacolab.app.ui.feature.user.home.HomeScreen
import com.sukacolab.app.ui.feature.user.profile.ProfileScreen
import com.sukacolab.app.ui.feature.user.project.ProjectScreen
import com.sukacolab.app.ui.feature.user.project.project_detail.ProjectDetailScreen
import com.sukacolab.app.ui.feature.user.ur_project.UrProjectScreen
import com.sukacolab.app.ui.navigation.PROJECT_ID
import com.sukacolab.app.ui.navigation.Screen

fun NavGraphBuilder.homeNavGraph(
    navController: NavHostController,
) {
    composable(Screen.Home.route) {
        HomeScreen(navController = navController)
    }
    composable(Screen.Profile.route) {
        ProfileScreen(navController = navController)
    }
    composable(Screen.UrProject.route) {
        UrProjectScreen(navController = navController)
    }
    composable(Screen.Review.route) {
        ReviewScreen(navController = navController)
    }
    composable(Screen.Project.route) {
        ProjectScreen(navController = navController)
    }
    composable(Screen.Bookmark.route) {
        BookmarkScreen(navController = navController)
    }
    composable(Screen.Application.route) {
        ApplicationScreen(navController = navController)
    }
}
