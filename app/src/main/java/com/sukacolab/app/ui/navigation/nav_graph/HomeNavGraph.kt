package com.sukacolab.app.ui.navigation.nav_graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.sukacolab.app.ui.feature.home.HomeScreen
import com.sukacolab.app.ui.feature.profile.ProfileScreen
import com.sukacolab.app.ui.feature.projectDetail.ProjectDetailContent
import com.sukacolab.app.ui.feature.urProjectDetail.UrProjectDetailContent
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
        UrProjectDetailContent()
    }
    composable(Screen.Bookmark.route) {
        ProjectDetailContent()
    }
}
