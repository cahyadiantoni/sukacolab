package com.sukacolab.app.ui.navigation.nav_graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sukacolab.app.ui.feature.login.LoginScreen
import com.sukacolab.app.ui.feature.register.RegisterScreen
import com.sukacolab.app.ui.navigation.AUTH_GRAPH_ROUTE
import com.sukacolab.app.ui.navigation.Screen

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = Screen.Login.route,
        route = AUTH_GRAPH_ROUTE
    ) {
        composable(Screen.Login.route) {
            LoginScreen(
               navController = navController,
            )
        }
        composable(Screen.Register.route) {
            RegisterScreen(navController = navController)
        }
    }
}