package com.sukacolab.app.ui.feature.user.project.project_detail

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.BuildCircle
import androidx.compose.material.icons.filled.CurrencyExchange
import androidx.compose.material.icons.filled.IncompleteCircle
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.LockClock
import androidx.compose.material.icons.filled.Paid
import androidx.compose.material.icons.filled.Quiz
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sukacolab.app.R
import com.sukacolab.app.ui.component.PrimaryButton
import com.sukacolab.app.ui.component.StatelessTopBar
import com.sukacolab.app.ui.feature.user.profile.ProfileViewModel
import com.sukacolab.app.ui.feature.user.project.ui_state.DetailProjectUiState
import com.sukacolab.app.ui.navigation.Screen
import com.sukacolab.app.ui.theme.tertiaryColor
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectDetailScreen(
    navController: NavController,
    projectId: String,
){
    var openPage = remember { mutableStateOf(false) }
    val viewModel: ProjectDetailViewModel = getViewModel()
    val profileViewModel: ProfileViewModel = getViewModel()

    val idState = remember { mutableStateOf("") }
    val lifecycle: Lifecycle = LocalLifecycleOwner.current.lifecycle

    val context = LocalContext.current

    val joinProjectResult by viewModel.joinProjectResult.observeAsState()
    val bookmarkProjectResult by viewModel.bookmarkProjectResult.observeAsState()
    val reviewProjectResult by viewModel.reviewProjectResult.observeAsState()
    val isLoading = viewModel.isLoading.value

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

    LaunchedEffect(key1 = joinProjectResult) {
        when (joinProjectResult) {
            is JoinProjectResults.Success -> {
                val message = (joinProjectResult as JoinProjectResults.Success).message
                Log.d("Join Project", "Sukses: $message")
                Toast.makeText(context, "Success : $message", Toast.LENGTH_SHORT).show()
                navController.navigate(
                    Screen.ProjectDetail.createRoute(
                        projectId.toInt()
                    )
                ){
                    popUpTo(Screen.ProjectDetail.route) {
                        inclusive = true
                    }
                }
            }
            is JoinProjectResults.Error -> {
                val errorMessage = (joinProjectResult as JoinProjectResults.Error).errorMessage
                Log.d("Join Project", "Gagal: $errorMessage")
                Toast.makeText(context, "Failed : $errorMessage", Toast.LENGTH_SHORT).show()
                navController.navigate(
                    Screen.ProjectDetail.createRoute(
                        projectId.toInt()
                    )
                ){
                    popUpTo(Screen.ProjectDetail.route) {
                        inclusive = true
                    }
                }
            }
            else -> {
                // Initial state or loading state
            }
        }
    }

    LaunchedEffect(key1 = bookmarkProjectResult) {
        when (bookmarkProjectResult) {
            is BookmarkProjectResults.Success -> {
                val message = (bookmarkProjectResult as BookmarkProjectResults.Success).message
                Log.d("Add Bookmark", "Sukses: $message")
                Toast.makeText(context, "Success : $message", Toast.LENGTH_SHORT).show()
                navController.navigate(
                    Screen.ProjectDetail.createRoute(
                        projectId.toInt()
                    )
                ){
                    popUpTo(Screen.ProjectDetail.route) {
                        inclusive = true
                    }
                }
            }
            is BookmarkProjectResults.Error -> {
                val errorMessage = (bookmarkProjectResult as BookmarkProjectResults.Error).errorMessage
                Log.d("Add Bookmark", "Gagal: $errorMessage")
                Toast.makeText(context, "Failed : $errorMessage", Toast.LENGTH_SHORT).show()
                navController.navigate(
                    Screen.ProjectDetail.createRoute(
                        projectId.toInt()
                    )
                ){
                    popUpTo(Screen.ProjectDetail.route) {
                        inclusive = true
                    }
                }
            }
            else -> {
                // Initial state or loading state
            }
        }
    }

    LaunchedEffect(key1 = reviewProjectResult) {
        when (reviewProjectResult) {
            is ReviewProjectResults.Success -> {
                val message = (reviewProjectResult as ReviewProjectResults.Success).message
                Log.d("Add Bookmark", "Sukses: $message")
                Toast.makeText(context, "Success : $message", Toast.LENGTH_SHORT).show()
                navController.navigate(
                    Screen.ProjectDetail.createRoute(
                        projectId.toInt()
                    )
                ){
                    popUpTo(Screen.ProjectDetail.route) {
                        inclusive = true
                    }
                }
            }
            is ReviewProjectResults.Error -> {
                val errorMessage = (reviewProjectResult as ReviewProjectResults.Error).errorMessage
                Log.d("Add Bookmark", "Gagal: $errorMessage")
                Toast.makeText(context, "Failed : $errorMessage", Toast.LENGTH_SHORT).show()
                navController.navigate(
                    Screen.ProjectDetail.createRoute(
                        projectId.toInt()
                    )
                ){
                    popUpTo(Screen.ProjectDetail.route) {
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
    val responseDetail = viewModel.responseDetail.value
    Log.d("Response Isi", "$responseDetail")
    when (responseDetail) {
        is DetailProjectUiState.Loading -> {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(align = Alignment.Center)
            )
        }
        is DetailProjectUiState.Success -> {
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
                        title = "Project Detail",
                        actionIcon = {
                            if(profileViewModel.id != responseDetail.data.userId){
                                if (responseDetail.data.isBookmarked == 0){
                                    IconButton(onClick = {
                                        viewModel.bookmarkProject(projectId)
                                    }) {
                                        Icon(imageVector = Icons.Default.BookmarkBorder, contentDescription = "", tint = Color.White)
                                    }
                                }else{
                                    IconButton(onClick = {
                                        viewModel.bookmarkProject(projectId)
                                    }) {
                                        Icon(imageVector = Icons.Default.Bookmark, contentDescription = "", tint = Color.White)
                                    }
                                }
                            }
                        }
                    )
                }
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
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
                                                if (responseDetail.data.tipe == "Loker") {
                                                    R.drawable.paid
                                                } else if (responseDetail.data.tipe == "Portofolio") {
                                                    R.drawable.portofolio
                                                } else if (responseDetail.data.tipe == "Kompetisi") {
                                                    R.drawable.competition
                                                } else {
                                                    R.drawable.unknown
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
                            ) {
                                val colorButton1 = if (openPage.value) {
                                    MaterialTheme.colorScheme.tertiary
                                } else {
                                    MaterialTheme.colorScheme.primary
                                }
                                val colorText1 = if (openPage.value) {
                                    MaterialTheme.colorScheme.primary
                                } else {
                                    Color.White
                                }
                                Button(
                                    onClick = { openPage.value = false },
                                    modifier = Modifier
                                        .weight(0.2f),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = colorButton1
                                    ),
                                    shape = RoundedCornerShape(8.dp)
                                ) {

                                    Text(text = "About", color = colorText1)

                                }

                                val colorButton2 = if (openPage.value) {
                                    MaterialTheme.colorScheme.primary
                                } else {
                                    MaterialTheme.colorScheme.tertiary
                                }
                                val colorText2 = if (openPage.value) {
                                    Color.White
                                } else {
                                    MaterialTheme.colorScheme.primary
                                }
                                Button(
                                    onClick = { openPage.value = true },
                                    modifier = Modifier
                                        .weight(0.2f),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = colorButton2
                                    ),
                                    shape = RoundedCornerShape(8.dp)
                                ) {

                                    Text(text = "Requirements", color = colorText2)

                                }
                            }
                            if (openPage.value) {
                                Text(
                                    text = responseDetail.data.requirements,
                                    color = Color.DarkGray,
                                    modifier = Modifier.padding(
                                        horizontal = 20.dp,
                                        vertical = 5.dp
                                    ).fillMaxWidth(),
                                    textAlign = TextAlign.Justify,
                                    fontWeight = FontWeight.Normal,
                                )
                            } else {
                                Row(
                                    modifier = Modifier.padding(
                                        horizontal = 20.dp,
                                        vertical = 5.dp
                                    ).fillMaxWidth(),
                                ) {
                                    Text(
                                        text = "Author : ",
                                        color = Color.DarkGray,
                                        textAlign = TextAlign.Justify,
                                        fontWeight = FontWeight.Normal,
                                    )

                                    Box(
                                        modifier = Modifier
                                            .size(20.dp)
                                            .clip(CircleShape)
                                            .background(Color.White)

                                    ) {

//                                        Image(
//                                            painter = painterResource(id = R.drawable.img_logo),
//                                            contentDescription = null,
//                                            contentScale = ContentScale.Crop,
//                                            modifier = Modifier
//                                                .clip(CircleShape)
//                                        )

                                        AsyncImage(
                                            model = ImageRequest.Builder(LocalContext.current)
                                                .data(responseDetail.data.userPhoto)
                                                .crossfade(true)
                                                .build(),
                                            contentDescription = null,
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier
                                                .clip(CircleShape)
                                        )
                                    }

                                    Text(
                                        modifier = Modifier
                                            .padding(start = 4.dp),
                                        text = responseDetail.data.userName,
                                        color = Color.DarkGray,
                                        textAlign = TextAlign.Justify,
                                        fontWeight = FontWeight.SemiBold,
                                    )
                                }

                                Text(
                                    text = responseDetail.data.description,
                                    color = Color.DarkGray,
                                    modifier = Modifier.padding(
                                        horizontal = 20.dp,
                                        vertical = 5.dp
                                    ).fillMaxWidth(),
                                    textAlign = TextAlign.Justify,
                                    fontWeight = FontWeight.Normal,
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(100.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.padding(100.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomStart),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = tertiaryColor)
                                .padding(vertical = 15.dp, horizontal = 30.dp)
                        ) {
                            if (isLoading) {
                                CircularProgressIndicator(
                                    modifier = Modifier.align(
                                        Alignment.Center
                                    )
                                )
                            } else {
                                if(profileViewModel.id == responseDetail.data.userId){
                                    when (responseDetail.data.isActive) {
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
                                                Text(text = "Non Aktifkan Project", color = Color.White)
                                            }
                                        }

                                        2 -> {
                                            Button(
                                                onClick = {

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
                                    }

                                }else{
                                    when (responseDetail.data.statusApplied) {
                                        null -> {
                                            Button(
                                                onClick = {
                                                    viewModel.joinProject(projectId)
                                                },
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .height(50.dp),
                                                colors = ButtonDefaults.buttonColors(
                                                    containerColor = MaterialTheme.colorScheme.primary
                                                ),
                                                shape = RoundedCornerShape(8.dp)
                                            ) {
                                                Text(text = "Join Project", color = Color.White)
                                            }
                                        }

                                        0 -> {
                                            Button(
                                                onClick = {
                                                    viewModel.joinProject(projectId)
                                                },
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .height(50.dp),
                                                colors = ButtonDefaults.buttonColors(
                                                    containerColor = Color.Red
                                                ),
                                                shape = RoundedCornerShape(8.dp)
                                            ) {
                                                Text(
                                                    text = "Cancel Join Project",
                                                    color = Color.White
                                                )
                                            }
                                        }

                                        1 -> {
                                            Button(
                                                onClick = {

                                                },
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .height(50.dp),
                                                colors = ButtonDefaults.buttonColors(
                                                    containerColor = MaterialTheme.colorScheme.primary
                                                ),
                                                shape = RoundedCornerShape(8.dp)
                                            ) {
                                                Text(
                                                    text = "Your Accepted (Contact Author)",
                                                    color = Color.White
                                                )
                                            }
                                        }

                                        else -> {
                                            Button(
                                                onClick = {

                                                },
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .height(50.dp),
                                                colors = ButtonDefaults.buttonColors(
                                                    containerColor = Color.Gray
                                                ),
                                                shape = RoundedCornerShape(8.dp)
                                            ) {
                                                Text(
                                                    text = "Your Rejected",
                                                    color = Color.White
                                                )
                                            }
                                        }
                                    }
                                }
                            }

                        }
                    }
                }
            }
        }
        is DetailProjectUiState.Failure -> {
            Text(text = responseDetail.error.message ?: "Unknown Error")
        }
        DetailProjectUiState.Empty -> {
            Text(text = "Empty Data")
        }
    }
}