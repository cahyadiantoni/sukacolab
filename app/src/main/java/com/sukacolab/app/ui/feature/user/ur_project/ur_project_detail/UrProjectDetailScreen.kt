package com.sukacolab.app.ui.feature.user.ur_project.ur_project_detail

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.BuildCircle
import androidx.compose.material.icons.filled.CurrencyExchange
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.LockClock
import androidx.compose.material.icons.filled.Paid
import androidx.compose.material.icons.filled.Preview
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.SizeMode
import com.sukacolab.app.R
import com.sukacolab.app.ui.component.StatelessTopBar
import com.sukacolab.app.ui.component.cards.ItemListProfile
import com.sukacolab.app.ui.feature.user.profile.ProfileViewModel
import com.sukacolab.app.ui.feature.user.profile_other.openUri
import com.sukacolab.app.ui.feature.user.ur_project.ui_state.UrDetailProjectUiState
import com.sukacolab.app.ui.feature.user.ur_project.ui_state.UserJoinUiState
import com.sukacolab.app.ui.navigation.Screen
import com.sukacolab.app.ui.theme.tertiaryColor
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UrProjectDetailScreen(
    navController: NavController,
    projectId: String,
){
    var openPage = remember { mutableStateOf(false) }
    val viewModel: UrProjectDetailViewModel = getViewModel()

    val context = LocalContext.current
    val idState = remember { mutableStateOf("") }
    val lifecycle: Lifecycle = LocalLifecycleOwner.current.lifecycle

    val reviewProjectResult by viewModel.reviewProjectResult.observeAsState()

    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                showDialog = false
            },
            title = {
                Text(text = "Konfirmasi")
            },
            text = {
                Text(text = "Apakah Anda yakin ingin menonaktifkan proyek?")
            },
            confirmButton = {
                Button(
                    onClick = {
                        showDialog = false
                        viewModel.reviewProject(projectId, "2")
                    }
                ) {
                    Text(text = "Ya")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        showDialog = false
                    }
                ) {
                    Text(text = "Tidak")
                }
            }
        )
    }

    LaunchedEffect(key1 = reviewProjectResult) {
        when (reviewProjectResult) {
            is ReviewProjectResults.Success -> {
                val message = (reviewProjectResult as ReviewProjectResults.Success).message
                Log.d("Add Bookmark", "Sukses: $message")
                Toast.makeText(context, "Success : $message", Toast.LENGTH_SHORT).show()
                navController.navigate(
                    Screen.UrProjectDetail.createRoute(
                        projectId.toInt()
                    )
                ){
                    popUpTo(Screen.UrProjectDetail.route) {
                        inclusive = true
                    }
                }
            }
            is ReviewProjectResults.Error -> {
                val errorMessage = (reviewProjectResult as ReviewProjectResults.Error).errorMessage
                Log.d("Add Bookmark", "Gagal: $errorMessage")
                Toast.makeText(context, "Failed : $errorMessage", Toast.LENGTH_SHORT).show()
                navController.navigate(
                    Screen.UrProjectDetail.createRoute(
                        projectId.toInt()
                    )
                ){
                    popUpTo(Screen.UrProjectDetail.route) {
                        inclusive = true
                    }
                }
            }
            else -> {
                // Initial state or loading state
            }
        }
    }

    LaunchedEffect(key1 = Unit) {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            launch {
                idState.value = projectId
                viewModel.getDetailProject(projectId = projectId)
            }
        }
    }

    LaunchedEffect(key1 = Unit) {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            launch {
                idState.value = projectId
                viewModel.getUserJoin(projectId = projectId)
            }
        }
    }

    val responseDetail = viewModel.responseDetail.value
    Log.d("Response Isi", "$responseDetail")
    when (responseDetail) {
        is UrDetailProjectUiState.Loading -> {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(align = Alignment.Center)
            )
        }
        is UrDetailProjectUiState.Success -> {
            Scaffold(
                modifier = Modifier,
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
                        title = "Your Project Detail",
                        actionIcon = {
                            IconButton(onClick = {
                                navController.navigate(
                                    Screen.ProjectDetail.createRoute(
                                        projectId.toInt()
                                    )
                                )
                            }) {
                                Icon(imageVector = Icons.Default.Preview, contentDescription = "", tint = Color.White)
                            }
                        }
                    )
                }
            ){
                Box(modifier = Modifier.fillMaxSize()){
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it)
                    ) {
                        item {

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(160.dp)
                            ) {

                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(95.dp)
                                        .background(MaterialTheme.colorScheme.primary),
                                )

                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .align(Alignment.BottomStart)
                                ) {

                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                    }

                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(150.dp)
                                                .padding(10.dp)
                                                .clip(CircleShape)
                                                .background(Color.White)
                                        ) {

                                            val img =
                                                when (responseDetail.data.tipe) {
                                                    "Loker" -> {
                                                        R.drawable.paid
                                                    }
                                                    "Portofolio" -> {
                                                        R.drawable.portofolio
                                                    }
                                                    "Kompetisi" -> {
                                                        R.drawable.competition
                                                    }
                                                    else -> {
                                                        R.drawable.unknown
                                                    }
                                                }

                                            Image(
                                                painter = painterResource(id = img),
                                                contentDescription = null,
                                                contentScale = ContentScale.Crop,
                                                modifier = Modifier
                                                    .border(
                                                        2.dp,
                                                        Color.LightGray,
                                                        shape = CircleShape
                                                    )
                                                    .padding(5.dp)
                                                    .clip(CircleShape)
                                            )
                                        }
                                    }
                                }
                            }

                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Text(
                                    text = responseDetail.data.position,
                                    color = Color.Black,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.padding(start = 20.dp, end = 20.dp)
                                )

                                Text(
                                    text = responseDetail.data.name,
                                    color = Color.DarkGray,
                                    fontSize = 18.sp,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.padding(
                                        top = 8.dp,
                                        start = 20.dp,
                                        end = 20.dp
                                    )
                                )
                            }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 16.dp),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Column(
                                    modifier = Modifier
                                        .width(60.dp)
                                        .height(70.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.LocationOn,
                                        contentDescription = "",
                                        tint = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier.size(36.dp)
                                    )

                                    Text(
                                        text = responseDetail.data.location,
                                        color = Color.DarkGray,
                                        fontSize = 12.sp,
                                        textAlign = TextAlign.Center,
                                        lineHeight = 12.sp
                                    )
                                }

                                Column(
                                    modifier = Modifier
                                        .width(60.dp)
                                        .height(70.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.BuildCircle,
                                        contentDescription = "",
                                        tint = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier.size(36.dp)
                                    )

                                    Text(
                                        text = responseDetail.data.tipe,
                                        color = Color.DarkGray,
                                        fontSize = 12.sp,
                                        textAlign = TextAlign.Center,
                                        lineHeight = 12.sp
                                    )
                                }

                                Column(
                                    modifier = Modifier
                                        .width(60.dp)
                                        .height(70.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Paid,
                                        contentDescription = "",
                                        tint = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier.size(36.dp)
                                    )

                                    val isPaid = if (responseDetail.data.isPaid == 1) {
                                        "Paid"
                                    } else {
                                        "Unpaid"
                                    }

                                    Text(
                                        text = isPaid,
                                        color = Color.DarkGray,
                                        fontSize = 12.sp,
                                        textAlign = TextAlign.Center,
                                        lineHeight = 12.sp
                                    )
                                }

                                Column(
                                    modifier = Modifier
                                        .width(60.dp)
                                        .height(70.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Timer,
                                        contentDescription = "",
                                        tint = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier.size(36.dp)
                                    )

                                    Text(
                                        text = responseDetail.data.time,
                                        color = Color.DarkGray,
                                        fontSize = 12.sp,
                                        textAlign = TextAlign.Center,
                                        lineHeight = 12.sp
                                    )
                                }
                            }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 20.dp, end = 20.dp),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ){
                                val colorButton1 = if(openPage.value){MaterialTheme.colorScheme.tertiary}else{MaterialTheme.colorScheme.primary}
                                val colorText1 = if(openPage.value){MaterialTheme.colorScheme.primary}else{Color.White}
                                Button(
                                    onClick = { openPage.value = false },
                                    modifier = Modifier
                                        .weight(0.2f)
                                        .padding(horizontal = 5.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = colorButton1
                                    ),
                                    shape = RoundedCornerShape(8.dp)
                                ) {

                                    Text(text = "Selection", color = colorText1)

                                }

                                val colorButton2 = if(openPage.value){MaterialTheme.colorScheme.primary}else{MaterialTheme.colorScheme.tertiary}
                                val colorText2 = if(openPage.value){Color.White}else{MaterialTheme.colorScheme.primary}
                                Button(
                                    onClick = { openPage.value = true },
                                    modifier = Modifier
                                        .weight(0.2f)
                                        .padding(horizontal = 5.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = colorButton2
                                    ),
                                    shape = RoundedCornerShape(8.dp)
                                ) {

                                    Text(text = "Accepted", color = colorText2)

                                }
                            }
                        }
                        item {
                                    when (val responseUserJoin = viewModel.responseUserJoin.value) {
                                        is UserJoinUiState.Success -> {
                                            if (responseUserJoin.data.isEmpty()) {
                                                Box(
                                                    modifier = Modifier
                                                        .fillMaxSize()
                                                        .padding(top = 30.dp),
                                                    contentAlignment = Alignment.Center
                                                ) {
                                                    Text(
                                                        text = "Belum ada user join pada project ini",
                                                        fontWeight = FontWeight.Light
                                                    )
                                                }
                                            }else{
                                                val itemSize: Dp =
                                                    (LocalConfiguration.current.screenWidthDp.dp)
                                                com.google.accompanist.flowlayout.FlowRow(
                                                    mainAxisSize = SizeMode.Expand,
                                                    mainAxisAlignment = FlowMainAxisAlignment.SpaceBetween,
                                                    modifier = Modifier
                                                        .padding(horizontal = 4.dp),
                                                ) {
                                                    if(openPage.value) {
                                                        var count = 0
                                                        responseUserJoin.data.forEachIndexed { index, project ->
                                                            if (project.status == 1) {
                                                                Box(
                                                                    modifier = Modifier
                                                                        .width(itemSize),
                                                                    contentAlignment = Alignment.Center
                                                                ) {
                                                                    ItemListProfile(
                                                                        navController = navController,
                                                                        userId = project.id,
                                                                        projectId = projectId.toInt(),
                                                                        image = project.photo,
                                                                        name = project.name,
                                                                        summary = project.summary
                                                                    )
                                                                }
                                                                count++
                                                            }
                                                        }
                                                        if (count == 0) {
                                                            Box(
                                                                modifier = Modifier
                                                                    .fillMaxSize()
                                                                    .padding(top = 30.dp),
                                                                contentAlignment = Alignment.Center
                                                            ) {
                                                                Text(
                                                                    text = "Belum ada user yang perlu diseleksi",
                                                                    fontWeight = FontWeight.Light
                                                                )
                                                            }
                                                        }
                                                    }else{
                                                        var countTwo = 0
                                                        responseUserJoin.data.forEachIndexed { index, project ->
                                                            if (project.status == 0) {
                                                                Box(
                                                                    modifier = Modifier
                                                                        .width(itemSize),
                                                                    contentAlignment = Alignment.Center
                                                                ) {
                                                                    ItemListProfile(
                                                                        navController = navController,
                                                                        userId = project.id,
                                                                        projectId = projectId.toInt(),
                                                                        image = project.photo,
                                                                        name = project.name,
                                                                        summary = project.summary
                                                                    )
                                                                }
                                                                countTwo++
                                                            }
                                                        }
                                                        if (countTwo == 0) {
                                                            Box(
                                                                modifier = Modifier
                                                                    .fillMaxSize()
                                                                    .padding(top = 30.dp),
                                                                contentAlignment = Alignment.Center
                                                            ) {
                                                                Text(
                                                                    text = "Belum ada user join di project ini",
                                                                    fontWeight = FontWeight.Light
                                                                )
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        is UserJoinUiState.Failure -> {
                                            Text(text = responseUserJoin.error.message ?: "Unknown Error")
                                        }
                                        UserJoinUiState.Loading -> {
                                            CircularProgressIndicator(
                                                modifier = Modifier
                                                    .fillMaxSize()
                                                    .wrapContentSize(align = Alignment.Center)
                                            )
                                        }
                                        UserJoinUiState.Empty -> {
                                            Text(text = "Empty Data")
                                        }
                                    }
                            Box(modifier = Modifier.size(100.dp))
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomStart),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ){
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = tertiaryColor)
                                .padding(vertical = 15.dp, horizontal = 30.dp)
                        ) {
                            when (responseDetail.data.isActive) {
                                0 -> {
                                    Button(
                                        onClick = {
                                            context.openUri("https://wa.me/6281919480565")
                                        },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(50.dp),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Color.Gray
                                        ),
                                        shape = RoundedCornerShape(8.dp)
                                    ) {
                                        Text(text = "Menunggu review Admin", color = Color.White)
                                    }
                                }
                                1 -> {
                                    Button(
                                        onClick = {
                                            showDialog = true
                                        },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(50.dp),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Color.Red
                                        ),
                                        shape = RoundedCornerShape(8.dp)
                                    ) {
                                        Text(text = "Deactivate the project", color = Color.White)
                                    }
                                }
                                2 -> {
                                    Button(
                                        onClick = {
                                            navController.navigateUp()
                                        },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(50.dp),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Color.Gray
                                        ),
                                        shape = RoundedCornerShape(8.dp)
                                    ) {
                                        Text(text = "Project non Active", color = Color.White)
                                    }
                                }
                                else -> {
                                    Button(
                                        onClick = {
                                            navController.navigateUp()
                                        },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(50.dp),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Color.Gray
                                        ),
                                        shape = RoundedCornerShape(8.dp)
                                    ) {
                                        Text(text = "Rejected by Admin", color = Color.White)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        is UrDetailProjectUiState.Failure -> {
            Text(text = responseDetail.error.message ?: "Unknown Error")
        }
        UrDetailProjectUiState.Empty -> {
            Text(text = "Empty Data")
        }
    }
}
