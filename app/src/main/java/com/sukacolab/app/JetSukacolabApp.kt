package com.sukacolab.app

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AdsClick
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.RunCircle
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.TrackChanges
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sukacolab.app.ui.feature.home.HomeScreen
import com.sukacolab.app.ui.feature.home.primaryColor
import com.sukacolab.app.ui.feature.onboarding.OnboardingScreen
import com.sukacolab.app.ui.navigation.*
import com.sukacolab.app.ui.navigation.nav_graph.authNavGraph
import com.sukacolab.app.ui.navigation.nav_graph.homeNavGraph
import com.sukacolab.app.ui.feature.splash.SplashScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JetSukacolabApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            val excludedRoutes = listOf(
                Screen.Login.route,
                Screen.Register.route,
                Screen.Splash.route,
                Screen.Onboarding.route,
                Screen.UrProjectDetail.route,
                Screen.ProjectDetail.route,
            )
            if (currentRoute !in excludedRoutes) {
                BottomBar(navController)
            }
        },
        modifier = modifier
    ) {
        NavHost(
            navController = navController,
            startDestination = Screen.Splash.route,
            modifier = Modifier.padding(it)
        ) {
            composable(Screen.Splash.route) {
                SplashScreen(
                    navController = navController,
                )
            }
            composable(Screen.Onboarding.route) {
                OnboardingScreen(
                    navController = navController,
                )
            }
            authNavGraph(navController)
            homeNavGraph(navController)
        }
    }
}



@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val navigationItems = listOf(
            NavigationItem(
                title = "Home",
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = "Project",
                icon = Icons.Default.Work,
                screen = Screen.Project
            ),
            NavigationItem(
                title ="Bookmark",
                icon = Icons.Default.Bookmark,
                screen = Screen.UrProject
            ),
            NavigationItem(
                title ="Profile",
                icon = Icons.Default.AccountCircle,
                screen = Screen.Profile
            ),
        )
        NavigationBar() {
            navigationItems.map { item ->
                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title
                        )
                    },
                    label = { Text(item.title) },
                    selected = currentRoute == item.screen.route,
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = primaryColor,
                        unselectedIconColor = Color.Gray,
                        indicatorColor = Color.White
                    ),
                    onClick = {
                        navController.navigate(item.screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}