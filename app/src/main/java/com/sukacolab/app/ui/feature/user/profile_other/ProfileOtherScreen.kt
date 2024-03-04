package com.sukacolab.app.ui.feature.user.profile_other

import android.Manifest
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.BuildCircle
import androidx.compose.material.icons.filled.ChangeCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.FileOpen
import androidx.compose.material.icons.filled.FilePresent
import androidx.compose.material.icons.filled.FileUpload
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Paid
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sukacolab.app.R
import com.sukacolab.app.ui.component.StatelessTopBar
import com.sukacolab.app.ui.component.alert.AlertLogout
import com.sukacolab.app.ui.feature.user.profile.ProfileViewModel
import com.sukacolab.app.ui.feature.user.profile.sub_screen.edit_photo.EditPhotoResults
import com.sukacolab.app.ui.feature.user.profile_other.ui_state.CertificationUiState
import com.sukacolab.app.ui.feature.user.profile_other.ui_state.EducationUiState
import com.sukacolab.app.ui.feature.user.profile_other.ui_state.ExperienceUiState
import com.sukacolab.app.ui.feature.user.profile_other.ui_state.ProfileUiState
import com.sukacolab.app.ui.feature.user.profile_other.ui_state.SkillUiState
import com.sukacolab.app.ui.feature.user.project.ui_state.DetailProjectUiState
import com.sukacolab.app.ui.navigation.Screen
import com.sukacolab.app.ui.theme.tertiaryColor
import com.sukacolab.app.util.convertToMonthYearFormat
import com.sukacolab.app.util.saveUriToFilePdf
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import java.io.File
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileOtherScreen(
    navController: NavController,
    userId: String,
    projectId: String,
){
    val viewModel: ProfileOtherViewModel = getViewModel()

    val isLoading = viewModel.isLoading.value
    val context = LocalContext.current

    val idUser = remember { mutableStateOf("") }
    val idProject = remember { mutableStateOf("") }
    val lifecycle: Lifecycle = LocalLifecycleOwner.current.lifecycle

    LaunchedEffect(key1 = Unit) {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            launch {
                idUser.value = userId
                idProject.value = projectId
                viewModel.getProfileOther(userId= userId, projectId = projectId)
            }
        }
    }

    val responseUser = viewModel.response.value
    Log.d("Response Isi", "$responseUser")
    when (responseUser) {
        is ProfileUiState.Loading -> {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(align = Alignment.Center)
            )
        }
        is ProfileUiState.Success -> {
            Scaffold(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primary),
                topBar = {
                    CenterAlignedTopAppBar(
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
                        title = {
                            Text(text = "Profile Other", color = Color.White)
                        },
                        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
                        actions = {
                        }
                    )
                }
            ){
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
                                            if (responseUser.data.photo == null) {
                                                Image(
                                                    painter = painterResource(id = R.drawable.img_profile),
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
                                            } else {
                                                AsyncImage(
                                                    model = ImageRequest.Builder(LocalContext.current)
                                                        .data(responseUser.data.photo)
                                                        .crossfade(true)
                                                        .build(),
                                                    contentDescription = null,
                                                    contentScale = ContentScale.Crop,
                                                    placeholder = painterResource(id = R.drawable.img_profile),
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
                            }

                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Text(
                                    text = responseUser.data.name,
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.padding(start = 20.dp, end = 20.dp)
                                )

                                if (responseUser.data.summary == null) {
                                    Text(
                                        text = "Ringkasan belum ditambahkan",
                                        fontWeight = FontWeight.Light,
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.padding(
                                            top = 8.dp,
                                            start = 20.dp,
                                            end = 20.dp
                                        )
                                    )
                                } else {
                                    Text(
                                        text = responseUser.data.summary.toString(),
                                        fontWeight = FontWeight.Normal,
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.padding(
                                            top = 8.dp,
                                            start = 20.dp,
                                            end = 20.dp
                                        ),
                                        lineHeight = 18.sp
                                    )
                                }
                            }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        start = 35.dp,
                                        end = 35.dp,
                                        top = 20.dp,
                                        bottom = 20.dp
                                    ),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {

                                var nullNum = 4
                                if (responseUser.data.linkedin != null) {
                                    ClickableImage(
                                        painter = painterResource(id = R.drawable.linkedin),
                                        contentDescription = "Linkedin",
                                        imageSize = 40.dp,
                                        uri = responseUser.data.linkedin.toString()
                                    )

                                    nullNum--
                                }

                                if (responseUser.data.github != null) {
                                    ClickableImage(
                                        painter = painterResource(id = R.drawable.github),
                                        contentDescription = "GitHub",
                                        imageSize = 40.dp,
                                        uri = responseUser.data.github.toString()
                                    )

                                    nullNum--
                                }

                                if (responseUser.data.whatsapp != null) {
                                    ClickableImage(
                                        painter = painterResource(id = R.drawable.whatsapp),
                                        contentDescription = "WhatsApp",
                                        imageSize = 40.dp,
                                        uri = responseUser.data.whatsapp.toString()
                                    )

                                    nullNum--
                                }

                                if (responseUser.data.instagram != null) {
                                    ClickableImage(
                                        painter = painterResource(id = R.drawable.instagram),
                                        contentDescription = "Instagram",
                                        imageSize = 40.dp,
                                        uri = responseUser.data.instagram.toString()
                                    )

                                    nullNum--
                                }

                                if (nullNum == 4) {
                                    Text(
                                        text = "Kontak belum ditambahkan",
                                        fontWeight = FontWeight.Light,
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.padding(
                                            top = 8.dp,
                                            start = 20.dp,
                                            end = 20.dp
                                        )
                                    )
                                }
                            }

                            borderCompose()

                            ResumeCompose(
                                navController = navController,
                                viewModel = viewModel,
                                resume = responseUser.data.resume ?: ""
                            )

                            borderCompose()

                            AboutCompose(
                                viewModel = viewModel,
                                about = responseUser.data.about ?: ""
                            )
                            borderCompose()

                            if (responseUser.data.id != 1) {
                                ExperienceCompose(
                                    navController = navController,
                                    userId = userId
                                )
                                borderCompose()

                                LicenseCompose(
                                    navController = navController,
                                    userId = userId
                                )
                                borderCompose()

                                SkillsCompose(
                                    navController = navController,
                                    userId = userId
                                )
                                borderCompose()

                                EducationCompose(
                                    navController = navController,
                                    userId = userId
                                )
                            }
                            Spacer(modifier = Modifier.padding(40.dp))
                        }
                    }
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
                                if (userId == "1") {
                                    Button(
                                        onClick = {
                                            context.openUri("https://wa.me/6281919480565")
                                        },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(50.dp),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = MaterialTheme.colorScheme.primary
                                        ),
                                        shape = RoundedCornerShape(8.dp)
                                    ) {
                                        Text(text = "Hubungi Admin", color = Color.White)
                                    }
                                } else {
                                    when (responseUser.data.status) {
                                        null -> {
                                            Button(
                                                onClick = {
                                                    navController.navigateUp()
                                                },
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .height(50.dp),
                                                colors = ButtonDefaults.buttonColors(
                                                    containerColor = MaterialTheme.colorScheme.primary
                                                ),
                                                shape = RoundedCornerShape(8.dp)
                                            ) {
                                                Text(text = "Kembali", color = Color.White)
                                            }
                                        }

                                        0 -> {
                                            Button(
                                                onClick = {
                                                    navController.navigateUp()
                                                },
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .height(50.dp),
                                                colors = ButtonDefaults.buttonColors(
                                                    containerColor = MaterialTheme.colorScheme.primary
                                                ),
                                                shape = RoundedCornerShape(8.dp)
                                            ) {
                                                Text(text = "Terima", color = Color.White)
                                            }
                                            Button(
                                                onClick = {
                                                    navController.navigateUp()
                                                },
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .height(50.dp),
                                                colors = ButtonDefaults.buttonColors(
                                                    containerColor = Color.Red
                                                ),
                                                shape = RoundedCornerShape(8.dp)
                                            ) {
                                                Text(text = "Tolak", color = Color.White)
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
                                                    text = "User Telah Diterima",
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
                                                    text = "User Telah Ditolak",
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
        is ProfileUiState.Failure -> {
            Text(text = responseUser.error.message ?: "Unknown Error")
        }
        ProfileUiState.Empty -> {
            Text(text = "Empty Data")
        }
    }
}

@Composable
fun SkillsCompose(
    navController: NavController,
    userId: String
) {
    val viewModel: ProfileOtherViewModel = getViewModel()

    val idUser = remember { mutableStateOf("") }
    val lifecycle: Lifecycle = LocalLifecycleOwner.current.lifecycle

    LaunchedEffect(key1 = Unit) {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            launch {
                idUser.value = userId
                viewModel.getSkill(userId= userId.toInt())
            }
        }
    }

    val response = viewModel.responseSkill.value
    Log.d("Response Isi", "$response")
    when (response) {
        is SkillUiState.Loading -> {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(align = Alignment.Center)
            )
        }
        is SkillUiState.Success -> {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Column(modifier = Modifier.wrapContentSize()) {

                            Text(
                                text = "Skills",
                                fontWeight = FontWeight.Bold,
                            )

                        }
                    }
                    if (response.data.isEmpty()) {
                        Text(
                            text = "Skill belum ditambahkan",
                            fontWeight = FontWeight.Light,
                            modifier = Modifier.padding(top = 10.dp)
                        )
                    }else{
                        response.data.forEachIndexed { index, skill ->
                            Text(
                                text = skill.name,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 20.dp),
                                lineHeight = 18.sp
                            )

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 10.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Image(painter = painterResource(id = R.drawable.skills),
                                    modifier = Modifier.size(40.dp),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop)

                                Text(
                                    text = skill.description,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 15.sp,
                                    modifier = Modifier.padding(horizontal = 7.dp),
                                    lineHeight = 16.sp
                                )
                            }

                            Divider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 10.dp),
                                color = Color.LightGray
                            )
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 15.dp)
                                .clickable {
                                    navController.navigate(
                                        Screen.SkillOther.createRoute(
                                            userId
                                        )
                                    )
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(5.dp),
                                modifier = Modifier.wrapContentSize()
                            ) {
                                Text(
                                    text = "Show All Skills",
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.Bold
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
        is SkillUiState.Failure -> {
            Text(text = response.error.message ?: "Unknown Error")
        }
        SkillUiState.Empty -> {
            Text(text = "Empty Data")
        }
    }
}


@Composable
fun LicenseCompose(
    navController: NavController,
    userId: String
) {
    val viewModel: ProfileOtherViewModel = getViewModel()

    val idUser = remember { mutableStateOf("") }
    val lifecycle: Lifecycle = LocalLifecycleOwner.current.lifecycle

    LaunchedEffect(key1 = Unit) {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            launch {
                idUser.value = userId
                viewModel.getCertification(userId= userId.toInt())
            }
        }
    }

    val response = viewModel.responseCertification.value
    Log.d("Response Isi", "$response")
    when (response) {
        is CertificationUiState.Loading -> {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(align = Alignment.Center)
            )
        }
        is CertificationUiState.Success -> {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(20.dp)) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Column(modifier = Modifier.wrapContentSize()) {

                            Text(
                                text = "Licenses & Certifications",
                                fontWeight = FontWeight.Bold,
                            )

                        }
                    }

                    if (response.data.isEmpty()) {
                        Text(
                            text = "Certification belum ditambahkan",
                            fontWeight = FontWeight.Light,
                            modifier = Modifier.padding(top = 10.dp)
                        )
                    }else{
                        response.data.forEachIndexed { index, certification ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 30.dp)
                            ) {

                                Image(
                                    painter = painterResource(id = R.drawable.certi),
                                    contentDescription = null,
                                    modifier = Modifier.size(60.dp),
                                    contentScale = ContentScale.Crop
                                )

                                Spacer(modifier = Modifier.size(10.dp))

                                Column(modifier = Modifier.fillMaxWidth()) {

                                    Text(
                                        text = certification.name,
                                        fontWeight = FontWeight.Bold,
                                        lineHeight = 18.sp
                                    )

                                    Text(
                                        text = certification.publisher,
                                        fontWeight = FontWeight.Medium,
                                        lineHeight = 18.sp
                                    )

                                    val start = certification.publishDate.convertToMonthYearFormat()
                                    val end = certification.expireDate.convertToMonthYearFormat()

                                    Text(
                                        text = "Issued $start - Expired $end",
                                        fontWeight = FontWeight.Normal,
                                        lineHeight = 18.sp
                                    )

                                    Box(
                                        modifier = Modifier
                                            .wrapContentSize()
                                            .padding(top = 10.dp)
                                            .border(
                                                1.dp,
                                                Color.DarkGray,
                                                shape = RoundedCornerShape(20.dp)
                                            )
                                    ) {
                                        Text(
                                            text = certification.credential, modifier = Modifier
                                                .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp),
                                            fontWeight = FontWeight.Light,
                                            lineHeight = 18.sp
                                        )
                                    }
                                }
                            }

                            Divider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 10.dp),
                                color = Color.LightGray
                            )
                        }

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 15.dp)
                                .clickable {
                                    navController.navigate(
                                        Screen.CertificationOther.createRoute(
                                            userId
                                        )
                                    )
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(5.dp),
                                modifier = Modifier
                                    .wrapContentSize()
                            ) {
                                Text(
                                    text = "Show All Certifications",
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.Bold
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
        is CertificationUiState.Failure -> {
            Text(text = response.error.message ?: "Unknown Error")
        }
        CertificationUiState.Empty -> {
            Text(text = "Empty Data")
        }
    }
}


@Composable
fun EducationCompose(
    navController: NavController,
    userId: String
) {
    val viewModel: ProfileOtherViewModel = getViewModel()

    val idUser = remember { mutableStateOf("") }
    val lifecycle: Lifecycle = LocalLifecycleOwner.current.lifecycle

    LaunchedEffect(key1 = Unit) {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            launch {
                idUser.value = userId
                viewModel.getEducation(userId= userId.toInt())
            }
        }
    }

    val response = viewModel.responseEducation.value
    Log.d("Response Isi", "$response")
    when (response) {
        is EducationUiState.Loading -> {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(align = Alignment.Center)
            )
        }
        is EducationUiState.Success -> {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(20.dp)) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Column(modifier = Modifier.wrapContentSize()) {

                            Text(
                                text = "Educations",
                                fontWeight = FontWeight.Bold,
                            )

                        }
                    }
                    if (response.data.isEmpty()) {
                        Text(
                            text = "Education belum ditambahkan",
                            fontWeight = FontWeight.Light,
                            modifier = Modifier.padding(top = 10.dp)
                        )
                    }else{
                        response.data.forEachIndexed { index, education ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 30.dp)
                            ) {

                                Image(
                                    painter = painterResource(id = R.drawable.education),
                                    contentDescription = null,
                                    modifier = Modifier.size(60.dp),
                                    contentScale = ContentScale.Crop
                                )

                                Spacer(modifier = Modifier.size(10.dp))

                                Column(modifier = Modifier.fillMaxWidth()) {

                                    Text(
                                        text = education.instansi,
                                        fontWeight = FontWeight.Bold,
                                        lineHeight = 18.sp
                                    )

                                    Text(
                                        text = education.major,
                                        fontWeight = FontWeight.Medium,
                                        lineHeight = 18.sp
                                    )

                                    val start = education.startDate.convertToMonthYearFormat()
                                    val end = if (education.isNow == 1) {
                                        "Now"
                                    } else{
                                        education.endDate.convertToMonthYearFormat()
                                    }

                                    Text(
                                        text = "$start - $end",
                                        fontWeight = FontWeight.Normal,
                                        lineHeight = 18.sp
                                    )
                                }
                            }

                            Divider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 10.dp),
                                color = Color.LightGray
                            )
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 15.dp)
                                .clickable {
                                    navController.navigate(
                                        Screen.EducationOther.createRoute(
                                            userId
                                        )
                                    )
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(5.dp),
                                modifier = Modifier
                                    .wrapContentSize(),
                            ) {
                                Text(
                                    text = "Show All Educations",
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.Bold
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
        is EducationUiState.Failure -> {
            Text(text = response.error.message ?: "Unknown Error")
        }
        EducationUiState.Empty -> {
            Text(text = "Empty Data")
        }
    }
}

@Composable
fun ExperienceCompose(
    navController: NavController,
    userId: String
) {
    val viewModel: ProfileOtherViewModel = getViewModel()

    val idUser = remember { mutableStateOf("") }
    val lifecycle: Lifecycle = LocalLifecycleOwner.current.lifecycle

    LaunchedEffect(key1 = Unit) {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            launch {
                idUser.value = userId
                viewModel.getExperience(userId= userId.toInt())
            }
        }
    }

    val response = viewModel.responseExperience.value
    Log.d("Response Isi", "$response")
    when (response) {
        is ExperienceUiState.Loading -> {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(align = Alignment.Center)
            )
        }
        is ExperienceUiState.Success -> {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Column(modifier = Modifier.padding(20.dp)) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Column(modifier = Modifier.wrapContentSize()) {

                            Text(
                                text = "Experiences",
                                fontWeight = FontWeight.Bold,
                            )

                        }

                    }
                    if (response.data.isEmpty()) {
                        Text(
                            text = "Experience belum ditambahkan",
                            fontWeight = FontWeight.Light,
                            modifier = Modifier.padding(top = 10.dp)
                        )
                    } else {
                        response.data.forEachIndexed { index, experience ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 30.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.xp),
                                    contentDescription = null,
                                    modifier = Modifier.size(60.dp),
                                    contentScale = ContentScale.Crop
                                )

                                Spacer(modifier = Modifier.size(10.dp))

                                Column(modifier = Modifier.fillMaxWidth()) {

                                    Text(
                                        text = experience.title,
                                        fontWeight = FontWeight.Bold,
                                        lineHeight = 18.sp
                                    )

                                    Text(
                                        text = "${experience.company} - ${experience.role}",
                                        fontWeight = FontWeight.Medium,
                                        lineHeight = 18.sp
                                    )
                                    val start = experience.startDate.convertToMonthYearFormat()
                                    val end = if (experience.isNow == 1) {
                                        "Now"
                                    } else {
                                        experience.endDate.convertToMonthYearFormat()
                                    }
                                    Text(
                                        text = "$start - $end",
                                        fontWeight = FontWeight.Normal,
                                        lineHeight = 18.sp
                                    )
                                }
                            }

                            Divider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 10.dp),
                                color = Color.LightGray
                            )
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 15.dp)
                                .clickable {
                                    navController.navigate(
                                        Screen.ExperienceOther.createRoute(
                                            userId
                                        )
                                    )
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(5.dp),
                                modifier = Modifier.wrapContentSize()
                            ) {
                                Text(
                                    text = "Show All Experiences",
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.Bold
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
        is ExperienceUiState.Failure -> {
            Text(text = response.error.message ?: "Unknown Error")
        }
        ExperienceUiState.Empty -> {
            Text(text = "Empty Data")
        }
    }
}

@Composable
fun borderCompose() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(2.dp)
            .background(Color.LightGray)
            .padding(top = 30.dp)
    )
}


@Composable
fun AboutCompose(
    viewModel: ProfileOtherViewModel,
    about: String
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Column(modifier = Modifier.padding(20.dp)) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = "About",
                    fontWeight = FontWeight.Bold,
                )
            }

            if(about == ""){
                Text(
                    text = "Informasi belum ditambahkan",
                    fontWeight = FontWeight.Light,
                    modifier = Modifier.padding(top = 10.dp)
                )
            }else{
                Text(
                    text = about,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .padding(top = 20.dp),
                    lineHeight = 18.sp
                )
            }
        }
    }
}

@Composable
fun ResumeCompose(
    navController: NavController,
    viewModel: ProfileOtherViewModel,
    resume: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Column(modifier = Modifier.padding(20.dp)) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = "Resume",
                    fontWeight = FontWeight.Bold,
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp, top = 10.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                if (resume == ""){
                    Button(
                        onClick = {
                        },
                        shape = RoundedCornerShape(10),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(57.dp)
                            .border(
                                2.dp,
                                MaterialTheme.colorScheme.primary,
                                shape = RoundedCornerShape(10)
                            ),
                        colors = ButtonDefaults.buttonColors(Color.Transparent)
                    ) {
                        Icon(
                            imageVector = Icons.Default.FilePresent,
                            contentDescription = "File Icon",
                            tint = MaterialTheme.colorScheme.primary, // Warna ikon disesuaikan dengan warna primer
                            modifier = Modifier.size(24.dp) // Ukuran ikon
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Resume has not been added",
                            style = MaterialTheme.typography.titleMedium.copy(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                    }
                }else{
                    val encodedUrl = URLEncoder.encode(resume, StandardCharsets.UTF_8.toString())
                    Button(
                        onClick = {
                            navController.navigate(
                                Screen.ResumeOther.createRoute(
                                    encodedUrl
                                )
                            )
                        },
                        shape = RoundedCornerShape(10),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(57.dp)
                            .border(
                                2.dp,
                                MaterialTheme.colorScheme.primary,
                                shape = RoundedCornerShape(10)
                            ),
                        colors = ButtonDefaults.buttonColors(Color.Transparent)
                    ) {
                        Icon(
                            imageVector = Icons.Default.FileOpen, // Menggunakan ikon file bawaan
                            contentDescription = "File Icon",
                            tint = MaterialTheme.colorScheme.primary, // Warna ikon disesuaikan dengan warna primer
                            modifier = Modifier.size(24.dp) // Ukuran ikon
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Show Resume",
                            style = MaterialTheme.typography.titleMedium.copy(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                    }
                }
            }

        }
    }
}

@Composable
fun ClickableImage(
    painter: Painter,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    imageSize: Dp,
    uri: String
) {
    val context = LocalContext.current

    Box(
        modifier = modifier
            .size(imageSize)
            .clickable {
                context.openUri(uri)
            }
    ) {
        Image(
            painter = painter,
            contentDescription = contentDescription,
            modifier = Modifier.fillMaxSize()
        )
    }
}

fun Context.openUri(uriString: String) {
    val uri = Uri.parse(uriString)
    val intent = Intent(Intent.ACTION_VIEW, uri)
    startActivity(intent)
}