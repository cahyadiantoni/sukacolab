package com.sukacolab.app.ui.feature.user.application.bookmark

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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.SizeMode
import com.sukacolab.app.ui.component.StatelessTopBar
import com.sukacolab.app.ui.component.cards.ItemListAppStatus
import com.sukacolab.app.ui.component.cards.ItemListProject
import com.sukacolab.app.ui.component.cards.ItemListUrProject
import com.sukacolab.app.ui.navigation.Screen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookmarkScreen(navController: NavController){
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
                        ItemListProject(
                            navController = navController,
                            id = 123,
                            position = "UI/UX Designer",
                            project = "Universitas Singaperbangsa Karawang",
                            date = "2024-03-01 15:30:22",
                            type = "Loker"
                        )

                        ItemListProject(
                            navController = navController,
                            id = 123,
                            position = "Android Developer",
                            project = "PT. Sukacode Solusi Teknologi",
                            date = "2024-03-01 15:30:22",
                            type = "Portofolio"
                        )

                        ItemListProject(
                            navController = navController,
                            id = 123,
                            position = "Web Developer",
                            project = "CV. Karib Solutions",
                            date = "2024-03-01 15:30:22",
                            type = "Kompetisi"
                        )

                        ItemListProject(
                            navController = navController,
                            id = 123,
                            position = "Project Manager",
                            project = "PT. Maju Mundur",
                            date = "2024-03-01 15:30:22",
                            type = "Lain Lain"
                        )
                    }
                }
            }
        }
    }
}