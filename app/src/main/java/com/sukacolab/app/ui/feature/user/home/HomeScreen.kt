package com.sukacolab.app.ui.feature.user.home

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.FileOpen
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.SizeMode
import com.sukacolab.app.R
import com.sukacolab.app.ui.component.CarouselCard
import com.sukacolab.app.ui.component.alert.PrimaryAlert
import com.sukacolab.app.ui.component.cards.ItemListProject
import com.sukacolab.app.ui.component.cards.ItemListUrProject
import com.sukacolab.app.ui.feature.user.home.ui_state.HomeUiState
import com.sukacolab.app.ui.feature.user.profile.ProfileViewModel
import com.sukacolab.app.ui.navigation.Screen
import com.sukacolab.app.ui.theme.secondaryColor
import kotlinx.coroutines.delay
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(
    navController: NavController,
) {
    HomeContent(navController = navController,)
    BackPressSample()
}

sealed class BackPress {
    object Idle : BackPress()
    object InitialTouch : BackPress()
}

@Composable
private fun BackPressSample() {
    var showToast by remember { mutableStateOf(false) }

    var backPressState by remember { mutableStateOf<BackPress>(BackPress.Idle) }
    val context = LocalContext.current

    if(showToast){
        Toast.makeText(context, "Press again to exit", Toast.LENGTH_SHORT).show()
        showToast = false
    }


    LaunchedEffect(key1 = backPressState) {
        if (backPressState == BackPress.InitialTouch) {
            delay(2000)
            backPressState = BackPress.Idle
        }
    }

    BackHandler(backPressState == BackPress.Idle) {
        backPressState = BackPress.InitialTouch
        showToast = true
    }

    BackHandler(backPressState == BackPress.InitialTouch) {
        (context as? Activity)?.finishAffinity()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    navController: NavController,
) {
    val viewModel: HomeViewModel = getViewModel()
    val responseProject = viewModel.responseProject.value

    val viewModelProfile: ProfileViewModel = getViewModel()

    Scaffold(
        modifier = Modifier,
        topBar = {
            SmallTopAppBar(
                title = {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(64.dp)
                            .padding(vertical = 8.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .clickable {
                                navController.navigate(Screen.Search.route)
                            }
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(64.dp)
                                .padding(horizontal = 12.dp)
                        ) {
                            Icon(imageVector = Icons.Default.Search, contentDescription = "" )
                            Text(text = "Cari", modifier = Modifier.padding(start = 8.dp), fontSize = 16.sp)
                        }
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
                actions = {
                    IconButton(onClick = {

                    }) {
                        Icon(
                            imageVector = Icons.Outlined.Notifications,
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
                .padding(it)
                .fillMaxSize()
        ) {
            item {
                Spacer(modifier = Modifier.padding(top = 16.dp))
                val name = if(viewModelProfile.name == null){
                    ""
                }else{
                    viewModelProfile.name
                }
                Text(
                    text = "Hey, $name",
                    fontSize = 24.sp,
                    lineHeight = 26.sp,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold), textAlign = TextAlign.Start,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                if(viewModelProfile.isComplete == false && viewModelProfile.id != 1){
                    Spacer(modifier = Modifier.padding(top = 8.dp))
                    Box(modifier = Modifier.padding(horizontal = 16.dp)){
                        Button(
                            onClick = {
                                navController.navigate(Screen.Profile.route)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(40.dp),
                            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Warning, // Menggunakan ikon file bawaan
                                contentDescription = "Edit Icon",
                                tint = Color.Yellow, // Warna ikon disesuaikan dengan warna primer
                                modifier = Modifier.size(16.dp) // Ukuran ikon
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Lengkapi Profilmu!",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    color = Color.Yellow,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 16.sp
                                )
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.padding(top = 16.dp))
                CarouselCard()
                Text(
                    text = "Category",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold), textAlign = TextAlign.Start,
                    modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                        .padding(horizontal = 12.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    val mainMenuData = listOf(
                        MainMenuData("Project Manager", R.drawable.project_manager),
                        MainMenuData("Quality Assurance", R.drawable.quality_assurance),
                        MainMenuData("UI-UX Designer", R.drawable.uiux_design),
                        MainMenuData("DevOps Engineer", R.drawable.dev_ops),
                        MainMenuData("Android Developer", R.drawable.android_dev),
                        MainMenuData("Web Developer", R.drawable.web_dev),
                        MainMenuData("Database Engineer", R.drawable.database_eng),
                        MainMenuData("IOS Developer", R.drawable.ios_dev)
                    )
                    mainMenuData.forEach { menu ->
                        MainMenu(title = menu.title, icon = menu.icon, onClick = {
                            navController.navigate(
                                Screen.Category.createRoute(
                                    menu.title
                                )
                            )
                        })
                    }

                }
            }

            item {
                Spacer(modifier = Modifier.padding(top = 16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Column(modifier = Modifier.wrapContentSize()) {

                        Text(
                            text = "Recent Projects",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold), textAlign = TextAlign.Start,
                        )

                    }

                    Row(
                        modifier = Modifier
                            .wrapContentSize()
                            .clickable {
                                navController.navigate(Screen.Project.route)
                            }
                            .padding(top = 5.dp, end = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(20.dp),
                    ) {
                        Text(text = "See All", color = MaterialTheme.colorScheme.secondary, fontSize = 14.sp, fontWeight = FontWeight.ExtraBold)
                    }
                }
                Spacer(modifier = Modifier.padding(bottom = 8.dp))
            }
            item{
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 20.dp)) {
                        when (responseProject) {
                            is HomeUiState.Success -> {
                                if (responseProject.data.isEmpty()) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(top = 30.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = "Belum ada project",
                                            fontWeight = FontWeight.Light
                                        )
                                    }
                                }else{
                                    responseProject.data.forEachIndexed { index, project ->
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
                            is HomeUiState.Failure -> {
                                Text(text = responseProject.error.message ?: "Unknown Error")
                            }
                            HomeUiState.Loading -> {
                                CircularProgressIndicator(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .wrapContentSize(align = Alignment.Center)
                                )
                            }
                            HomeUiState.Empty -> {
                                Text(text = "Empty Data")
                            }
                        }

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 4.dp),
                            contentAlignment = Alignment.Center
                        ) {

                            Row(
                                horizontalArrangement = Arrangement.spacedBy(5.dp),
                                modifier = Modifier
                                    .wrapContentSize()
                                    .padding(bottom = 6.dp)
                                    .clickable {
                                        navController.navigate(Screen.Project.route)
                                    }
                            ) {

                                Text(
                                    text = "Show All Projects",
                                    color = MaterialTheme.colorScheme.secondary,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.ExtraBold
                                )

                                Icon(
                                    imageVector = Icons.Default.ArrowForward,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.secondary
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainMenu(title: String, icon: Int, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .width(80.dp)
            .height(100.dp)
            .padding(horizontal = 4.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Box(
            modifier = Modifier
                .padding(4.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(
                            MaterialTheme.colorScheme.secondary,
                            shape = RoundedCornerShape(15.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Image(painter = painterResource(id = icon), contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable { onClick() },
                        contentScale = ContentScale.Crop
                    )
                }
                Text(
                    text = title,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    lineHeight = 10.sp,
                    modifier = Modifier.padding(horizontal = 2.dp)
                )
            }
        }
    }
}