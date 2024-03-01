package com.sukacolab.app.ui.feature.admin.home_admin

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.sukacolab.app.ui.feature.user.profile.ProfileViewModel
import kotlinx.coroutines.delay
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeAdminScreen(
    navController: NavController,
) {
    HomeAdminContent(navController = navController,)
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
        showToast= false
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
fun HomeAdminContent(
    navController: NavController,
) {
    var openDialog = remember { mutableStateOf(false) }
    var url by remember { mutableStateOf("") }
    val ctx = LocalContext.current
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
                val name = viewModelProfile.name
                Text(
                    text = "Hey, $name",
                    fontSize = 24.sp,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold), textAlign = TextAlign.Start,
                    modifier = Modifier.padding(16.dp)
                )
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
                    MainMenu(title = "Project Manager", icon = R.drawable.project_manager, onClick = {
                        openDialog.value = true
                        url = "https://hat-deepar-wf7cinod4a-et.a.run.app"
                    })
                    MainMenu(title = "Quality Assurance", icon = R.drawable.quality_assurance, onClick = {
                        openDialog.value = true
                        url = "https://hat-deepar-wf7cinod4a-et.a.run.app"
                    })
                    MainMenu(title = "UI/UX Designer", icon = R.drawable.uiux_design, onClick = {
                        openDialog.value = true
                        url = "https://hat-deepar-wf7cinod4a-et.a.run.app"
                    })
                    MainMenu(title = "DevOps Engineer", icon = R.drawable.dev_ops, onClick = {
                        openDialog.value = true
                        url = "https://hat-deepar-wf7cinod4a-et.a.run.app"
                    })
                    MainMenu(title = "Android Developer", icon = R.drawable.android_dev, onClick = {
                        openDialog.value = true
                        url = "https://hat-deepar-wf7cinod4a-et.a.run.app"
                    })
                    MainMenu(title = "Web Developer", icon = R.drawable.web_dev, onClick = {
                        openDialog.value = true
                        url = "https://try.deepar.ai/wrist/rolex"
                    })
                    MainMenu(title = "Database Engineer", icon = R.drawable.database_eng, onClick = {
                        openDialog.value = true
                        url = "https://demo.ar.wanna.fashion/"
                    })
                    MainMenu(title = "IOS Developer Apatuh", icon = R.drawable.ios_dev, onClick = {
                        openDialog.value = true
                        url = "https://demo-bag.ar.wanna.fashion/?modelid=wanna_bag&showonboarding=3d"
                    })
                }
                if(openDialog.value) {
                    PrimaryAlert(openDialog = openDialog, ctx = ctx, url = url)
                }

            }

            item {
                Spacer(modifier = Modifier.padding(top = 16.dp))
                Text(
                        text = "Recent Projects",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold), textAlign = TextAlign.Start,
                        modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
            item {
                val itemSize: Dp = (LocalConfiguration.current.screenWidthDp.dp)
                com.google.accompanist.flowlayout.FlowRow(
                    mainAxisSize = SizeMode.Expand,
                    mainAxisAlignment = FlowMainAxisAlignment.SpaceBetween,
                    modifier = Modifier
                        .padding(top = 4.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .width(itemSize),
                        contentAlignment = Alignment.Center
                    ) {
                        ItemListProject(
                            navController = navController,
                            id = 123,
                            position = "UI/UX Designer",
                            project = "Universitas Singaperbangsa Karawang",
                            date = "16 Februari 2024",
                            type = "Loker"
                        )
                    }

                    Box(
                        modifier = Modifier
                            .width(itemSize),
                        contentAlignment = Alignment.Center
                    ) {
                        ItemListProject(
                            navController = navController,
                            id = 123,
                            position = "Android Developer",
                            project = "PT. Sukacode Solusi Teknologi",
                            date = "14 Februari 2024",
                            type = "Portofolio"
                        )
                    }

                    Box(
                        modifier = Modifier
                            .width(itemSize),
                        contentAlignment = Alignment.Center
                    ) {
                        ItemListProject(
                            navController = navController,
                            id = 123,
                            position = "Web Developer",
                            project = "CV. Karib Solutions",
                            date = "2 Februari 2024",
                            type = "Lomba"
                        )
                    }

                    Box(
                        modifier = Modifier
                            .width(itemSize),
                        contentAlignment = Alignment.Center
                    ) {
                        ItemListProject(
                            navController = navController,
                            id = 123,
                            position = "Project Manager",
                            project = "PT. Maju Mundur",
                            date = "2 Januari 2024",
                            type = "Lain Lain"
                        )
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp),
                        contentAlignment = Alignment.Center
                    ) {

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(5.dp),
                            modifier = Modifier.wrapContentSize().padding(bottom = 16.dp)
                        ) {

                            Text(
                                text = "Show All Projects",
                                color = MaterialTheme.colorScheme.primary,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.ExtraBold
                            )

                            Icon(
                                imageVector = Icons.Default.ArrowForward,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary
                            )
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
        modifier =Modifier
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
                        .background(MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(15.dp)),
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