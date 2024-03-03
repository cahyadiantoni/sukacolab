package com.sukacolab.app.ui.feature.user.category

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import com.sukacolab.app.ui.component.StatelessTopBar
import com.sukacolab.app.ui.component.cards.ItemListProject
import com.sukacolab.app.ui.feature.user.category.ui_state.CategoryUiState
import com.sukacolab.app.ui.navigation.Screen
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreen(
    navController: NavController,
    category: String
    ){
    val viewModel: CategoryViewModel = getViewModel()
    val categoryState = remember { mutableStateOf("") }
    val lifecycle: Lifecycle = LocalLifecycleOwner.current.lifecycle

    val responseCategory = viewModel.responseCategory.value

    LaunchedEffect(key1 = Unit) {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            launch {
                categoryState.value = category
                viewModel.categoryProject(category = category)
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
                title = category,
                actionIcon = {
                    IconButton(onClick = {
                        navController.navigate(Screen.Bookmark.route) {
                            popUpTo(Screen.Bookmark.route) {
                                inclusive = true
                            }
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.Bookmarks,
                            contentDescription = "bookmark",
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
                        when (responseCategory) {
                            is CategoryUiState.Success -> {
                                if (responseCategory.data.isEmpty()) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize(),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = "Belum ada project dengan kategori ${category}",
                                            fontWeight = FontWeight.Light
                                        )
                                    }
                                }else{
                                    responseCategory.data.forEachIndexed { index, project ->
                                        ItemListProject(
                                            navController = navController,
                                            id = project.id,
                                            position = project.position,
                                            project = project.name,
                                            date = project.updatedAt,
                                            type = project.tipe
                                        )
                                    }
                                }
                            }
                            is CategoryUiState.Failure -> {
                                Text(text = responseCategory.error.message ?: "Unknown Error")
                            }
                            CategoryUiState.Loading -> {
                                CircularProgressIndicator(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .wrapContentSize(align = Alignment.Center)
                                )
                            }
                            CategoryUiState.Empty -> {
                                Text(text = "Empty Data")
                            }
                        }
                    }
                }
            }
        }
    }
}