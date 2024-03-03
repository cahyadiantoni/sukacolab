package com.sukacolab.app.ui.feature.user.application

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sukacolab.app.ui.component.cards.ItemListAppStatus
import com.sukacolab.app.ui.component.cards.ItemListProject
import com.sukacolab.app.ui.feature.user.application.ui_state.ApplicationUiState
import com.sukacolab.app.ui.feature.user.profile.ProfileViewModel
import com.sukacolab.app.ui.feature.user.project.ProjectViewModel
import com.sukacolab.app.ui.navigation.Screen
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApplicationScreen(
    navController: NavController,
) {
    val viewModel: ApplicationViewModel = getViewModel()
    val responseProject = viewModel.responseProject.value

    Scaffold(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Application Status", color = Color.White)
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
                actions = {
                    IconButton(onClick = {
                        navController.navigate(Screen.Bookmark.route)
                    }) {
                        Icon(
                            imageVector = Icons.Default.Bookmarks,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            item{
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        when (responseProject) {
                            is ApplicationUiState.Success -> {
                                if (responseProject.data.isEmpty()) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(top = 30.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = "Kamu belum melamar ke project",
                                            fontWeight = FontWeight.Light
                                        )
                                    }
                                }else{
                                    responseProject.data.forEachIndexed { index, project ->
                                        ItemListAppStatus(
                                            navController = navController,
                                            id = project.id,
                                            type = project.tipe,
                                            position = project.position,
                                            project = project.name,
                                            status = project.statusApplied
                                        )
                                    }
                                }
                            }
                            is ApplicationUiState.Failure -> {
                                Text(text = responseProject.error.message ?: "Unknown Error")
                            }
                            ApplicationUiState.Loading -> {
                                CircularProgressIndicator(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .wrapContentSize(align = Alignment.Center)
                                )
                            }
                            ApplicationUiState.Empty -> {
                                Text(text = "Empty Data")
                            }
                        }
                    }
                }
            }
        }
    }
}