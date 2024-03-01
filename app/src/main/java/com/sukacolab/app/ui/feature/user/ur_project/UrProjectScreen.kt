package com.sukacolab.app.ui.feature.user.ur_project


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sukacolab.app.R
import com.sukacolab.app.ui.component.alert.AlertDelete
import com.sukacolab.app.ui.component.cards.ItemListUrProject
import com.sukacolab.app.ui.feature.user.ur_project.ui_state.UrProjectUiState
import com.sukacolab.app.ui.navigation.Screen
import com.sukacolab.app.util.convertToMonthYearFormat
import org.koin.androidx.compose.getViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UrProjectScreen(
    navController: NavController,
) {
    val viewModel: UrProjectViewModel = getViewModel()
    val responseUrProject = viewModel.responseUrProject.value
    Scaffold(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Your Project", color = Color.White)
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
                actions = {
                    IconButton(onClick = {
                        navController.navigate(Screen.AddProject.route)
                    }) {
                        Icon(
                            imageVector = Icons.Default.Add,
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
                        when (responseUrProject) {
                            is UrProjectUiState.Success -> {
                                if (responseUrProject.data.isEmpty()) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(top = 30.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = "Kamu belum membuat project",
                                            fontWeight = FontWeight.Light
                                        )
                                    }
                                }else{
                                    responseUrProject.data.forEachIndexed { index, project ->
                                        ItemListUrProject(
                                            id = project.id,
                                            position = project.position,
                                            project = project.name,
                                            date = project.updatedAt,
                                            isActive = project.isActive
                                        )
                                    }
                                }
                            }
                            is UrProjectUiState.Failure -> {
                                Text(text = responseUrProject.error.message ?: "Unknown Error")
                            }
                            UrProjectUiState.Loading -> {
                                CircularProgressIndicator(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .wrapContentSize(align = Alignment.Center)
                                )
                            }
                            UrProjectUiState.Empty -> {
                                Text(text = "Empty Data")
                            }
                        }
                    }
                }
            }
        }
    }
}