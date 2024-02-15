package com.sukacolab.app.ui.feature.project


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sukacolab.app.ui.navigation.PRODUCT_ID
import com.sukacolab.app.ui.navigation.Screen
import com.sukacolab.app.ui.navigation.TabItems
import com.sukacolab.app.ui.theme.SukacolabBaseCoreTheme

@Composable
fun ProjectScreen(
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf(
        TabItems(
            title = "Application Status",
            screen = Screen.AppStatus
        ),
        TabItems(
            title = "Your Projects",
            screen = Screen.UrProject
        )
    )

    // Update the selectedTabIndex when the currentRoute changes
    if (currentRoute != null) {
        selectedTabIndex = tabs.indexOfFirst { it.screen.route == currentRoute }.takeIf { it != -1 } ?: 0
    }

    Column(modifier = Modifier.fillMaxSize()) {
        val excludedRoutes = listOf(
            Screen.ProjectDetail.route,
            Screen.UrProjectDetail.route,
            Screen.AddProject.route,
        )
        if (currentRoute !in excludedRoutes) {
            TabRow(
                selectedTabIndex = selectedTabIndex,
                modifier = Modifier.background(Color.White)
            ) {
                tabs.forEachIndexed {index, item ->
                    Tab(
                        text = { Text(item.title) },
                        selected = currentRoute == item.screen.route,
                        onClick = {
                            navController.navigate(
                                route = item.screen.route,
                                builder = {
                                    navController.navigate(route = item.screen.route) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                    }
                                    selectedTabIndex = index
                                }
                            )
                        }
                    )
                }
            }
        }

        NavHost(
            navController = navController,
            startDestination = Screen.AppStatus.route,
        ) {
            composable(Screen.UrProject.route){

            }
            composable(Screen.AppStatus.route){

            }
            composable(
                route = Screen.ProjectDetail.route,
                arguments = listOf(
                    navArgument(PRODUCT_ID) {
                        type = NavType.IntType
                    }
                )
            ) {
                Log.d("Args", it.arguments?.getInt(PRODUCT_ID).toString())
                val productId = it.arguments?.getInt(PRODUCT_ID).toString()
            }
            composable(
                route = Screen.UrProjectDetail.route,
                arguments = listOf(
                    navArgument(PRODUCT_ID) {
                        type = NavType.IntType
                    }
                )
            ) {
                Log.d("Args", it.arguments?.getInt(PRODUCT_ID).toString())
                val productId = it.arguments?.getInt(PRODUCT_ID).toString()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TemptPreview() {
    SukacolabBaseCoreTheme {
        ProjectScreen()
    }
}