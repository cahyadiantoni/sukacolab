package com.sukacolab.app.ui.feature.user.bookmark

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.SizeMode
import com.sukacolab.app.ui.component.StatelessTopBar
import com.sukacolab.app.ui.component.cards.ItemListAppStatus
import com.sukacolab.app.ui.component.cards.ItemListProject
import com.sukacolab.app.ui.feature.user.bookmark.ui_state.BookmarkUiState
import com.sukacolab.app.ui.navigation.Screen
import com.sukacolab.app.util.convertDateBookmark
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookmarkScreen(navController: NavController){
    val viewModel: BookmarkViewModel = getViewModel()
    val responseProject = viewModel.responseProject.value

    val lifecycle: Lifecycle = LocalLifecycleOwner.current.lifecycle

    LaunchedEffect(key1 = Unit) {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            launch {
                viewModel.getProject()
            }
        }
    }
    
    Scaffold(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary),
        topBar = {
            StatelessTopBar(
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp()
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "back",
                            tint = Color.White
                        )
                    }
                },
                title = "Bookmark",
                actionIcon = {
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
                            is BookmarkUiState.Success -> {
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
                                        val date = project.updatedAt.convertDateBookmark()
                                        ItemListProject(
                                            navController = navController,
                                            id = project.id,
                                            position = project.position,
                                            project = project.name,
                                            date = date,
                                            type = project.tipe
                                        )
                                    }
                                }
                            }
                            is BookmarkUiState.Failure -> {
                                Text(text = responseProject.error.message ?: "Unknown Error")
                            }
                            BookmarkUiState.Loading -> {
                                CircularProgressIndicator(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .wrapContentSize(align = Alignment.Center)
                                )
                            }
                            BookmarkUiState.Empty -> {
                                Text(text = "Empty Data")
                            }
                        }
                    }
                }
            }
        }
    }
}