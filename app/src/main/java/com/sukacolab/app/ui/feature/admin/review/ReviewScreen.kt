package com.sukacolab.app.ui.feature.admin.review


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import com.sukacolab.app.ui.component.cards.ItemListReviewProject
import com.sukacolab.app.ui.component.cards.ItemListUrProject
import com.sukacolab.app.ui.feature.admin.review.ui_state.ReviewUiState
import com.sukacolab.app.ui.navigation.Screen
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReviewScreen(
    navController: NavController,
) {
    val viewModel: ReviewViewModel = getViewModel()
    val responseReview = viewModel.responseReview.value

    val lifecycle: Lifecycle = LocalLifecycleOwner.current.lifecycle

    LaunchedEffect(key1 = Unit) {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            launch {
                viewModel.getReview()
            }
        }
    }

    Scaffold(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Review Project", color = Color.White)
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
                actions = {
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
                        when (responseReview) {
                            is ReviewUiState.Success -> {
                                if (responseReview.data.isEmpty()) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(top = 30.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = "Tidak ada project yang perlu direview",
                                            fontWeight = FontWeight.Light
                                        )
                                    }
                                }else{
                                    responseReview.data.forEachIndexed { index, project ->
                                        ItemListReviewProject(
                                            navController = navController,
                                            id = project.id,
                                            position = project.position,
                                            project = project.name,
                                            date = project.updatedAt,
                                            isActive = project.isActive,
                                            type = project.tipe
                                        )
                                    }
                                }
                            }
                            is ReviewUiState.Failure -> {
                                Text(text = responseReview.error.message ?: "Unknown Error")
                            }
                            ReviewUiState.Loading -> {
                                CircularProgressIndicator(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .wrapContentSize(align = Alignment.Center)
                                )
                            }
                            ReviewUiState.Empty -> {
                                Text(text = "Empty Data")
                            }
                        }
                    }
                }
            }
        }
    }
}