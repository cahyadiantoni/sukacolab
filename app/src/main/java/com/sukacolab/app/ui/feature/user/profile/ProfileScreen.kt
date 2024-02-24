package com.sukacolab.app.ui.feature.user.profile

import android.content.Context
import android.content.Intent
import android.net.Uri
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Share
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sukacolab.app.R
import com.sukacolab.app.ui.component.alert.AlertLogout
import com.sukacolab.app.ui.feature.user.profile.ui_state.CertificationUiState
import com.sukacolab.app.ui.feature.user.profile.ui_state.EducationUiState
import com.sukacolab.app.ui.feature.user.profile.ui_state.ExperienceUiState
import com.sukacolab.app.ui.feature.user.profile.ui_state.SkillUiState
import com.sukacolab.app.ui.navigation.Screen
import com.sukacolab.app.util.convertToMonthYearFormat
import org.koin.androidx.compose.getViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavController,
){
    val viewModel: ProfileViewModel = getViewModel()

    Scaffold(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Profilmu", color = Color.White)
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
                actions = {
                    IconButton(onClick = {
                    }) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                    IconButton(onClick = {

                    }) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
            )
        }
    ){
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

                                AsyncImage(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(viewModel.photo)
                                        .crossfade(true)
                                        .build(),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    placeholder = painterResource(id = R.drawable.img_logo),
                                    modifier = Modifier
                                        .border(2.dp, Color.LightGray, shape = CircleShape)
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
                        text = viewModel.name.toString(),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(start = 20.dp, end = 20.dp)
                    )

                    Text(
                        text = viewModel.summary.toString(),
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 8.dp, start = 20.dp, end = 20.dp)
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 35.dp, end = 35.dp, top = 20.dp, bottom = 20.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    ClickableImage(
                        painter = painterResource(id = R.drawable.linkedin),
                        contentDescription = "Linkedin",
                        imageSize = 40.dp,
                        uri = viewModel.linkedin.toString()
                    )

                    ClickableImage(
                        painter = painterResource(id = R.drawable.github),
                        contentDescription = "GitHub",
                        imageSize = 40.dp,
                        uri = viewModel.github.toString()
                    )


                    ClickableImage(
                        painter = painterResource(id = R.drawable.whatsapp),
                        contentDescription = "WhatsApp",
                        imageSize = 40.dp,
                        uri = viewModel.whatsapp.toString()
                    )

                    ClickableImage(
                        painter = painterResource(id = R.drawable.instagram),
                        contentDescription = "Instagram",
                        imageSize = 40.dp,
                        uri = viewModel.instagram.toString()
                    )
                }

                viewModel.resume?.let { resumeUrl ->
                    val encodedUrl = URLEncoder.encode(resumeUrl, StandardCharsets.UTF_8.toString())
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 50.dp, end = 50.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(
                            onClick = {
                                navController.navigate(
                                    Screen.Resume.createRoute(
                                        encodedUrl
                                    )
                                )
                            },
                            modifier = Modifier
                                .weight(0.2f),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            )
                        ) {
                            Text(text = "Show Resume", color = Color.White)
                        }
                    }
                }

                Spacer(modifier = Modifier.size(10.dp))

                borderCompose()

                AboutCompose(
                    viewModel = viewModel
                )
                borderCompose()

                ExperienceCompose(
                    navController = navController,
                    viewModel = viewModel
                )
                borderCompose()

                LicenseCompose(
                    navController = navController,
                    viewModel = viewModel
                )
                borderCompose()

                SkillsCompose(
                    navController = navController,
                    viewModel = viewModel
                )
                borderCompose()

                EducationCompose(
                    navController = navController,
                    viewModel = viewModel
                )
                borderCompose()

                MenuCompose(
                    navController = navController,
                    viewModel = viewModel
                )
                borderCompose()
            }
        }
    }
}

@Composable
fun SkillsCompose(
    navController: NavController,
    viewModel: ProfileViewModel,
) {
    val responseSkill = viewModel.responseSkill.value

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

                Row(modifier = Modifier
                    .wrapContentSize()
                    .clickable {
                        navController.navigate(Screen.Skill.route)
                    },
                    horizontalArrangement = Arrangement.spacedBy(20.dp)) {

                    Icon(
                        imageVector = Icons.Default.Edit, contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                    )
                }
            }

            when (responseSkill) {
                is SkillUiState.Success -> {
                    responseSkill.data.forEachIndexed { index, skill ->
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
                }
                is SkillUiState.Failure -> {
                    Text(text = responseSkill.error.message ?: "Unknown Error")
                }
                SkillUiState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(align = Alignment.Center)
                    )
                }
                SkillUiState.Empty -> {
                    Text(text = "Empty Data")
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp)
                    .clickable {
                        navController.navigate(Screen.Skill.route)
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


@Composable
fun LicenseCompose(
    navController: NavController,
    viewModel: ProfileViewModel,
) {
    val responseCertification = viewModel.responseCertification.value

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

                Row(
                    modifier = Modifier
                        .wrapContentSize()
                        .clickable {
                            navController.navigate(Screen.Certification.route)
                        },
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {

                    Icon(
                        imageVector = Icons.Default.Edit, contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )

                }
            }

            when (responseCertification) {
                is CertificationUiState.Success -> {
                    responseCertification.data.forEachIndexed { index, certification ->
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
                                        .border(1.dp, Color.DarkGray, shape = RoundedCornerShape(20.dp))
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
                }
                is CertificationUiState.Failure -> {
                    Text(text = responseCertification.error.message ?: "Unknown Error")
                }
                CertificationUiState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(align = Alignment.Center)
                    )
                }
                CertificationUiState.Empty -> {
                    Text(text = "Empty Data")
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp),
                contentAlignment = Alignment.Center
            ) {

                Row(
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    modifier = Modifier
                        .wrapContentSize()
                        .clickable {
                            navController.navigate(Screen.Certification.route)
                        }
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


@Composable
fun EducationCompose(
    navController: NavController,
    viewModel: ProfileViewModel,
) {
    val responseEducation = viewModel.responseEducation.value

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

                Row(
                    modifier = Modifier
                        .wrapContentSize()
                        .clickable {
                            navController.navigate(Screen.Education.route)
                        },
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {

                    Icon(
                        imageVector = Icons.Default.Edit, contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )

                }
            }

            when (responseEducation) {
                is EducationUiState.Success -> {
                    responseEducation.data.forEachIndexed { index, education ->
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
                }
                is EducationUiState.Failure -> {
                    Text(text = responseEducation.error.message ?: "Unknown Error")
                }
                EducationUiState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(align = Alignment.Center)
                    )
                }
                EducationUiState.Empty -> {
                    Text(text = "Empty Data")
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp),
                contentAlignment = Alignment.Center
            ) {

                Row(
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    modifier = Modifier
                        .wrapContentSize()
                        .clickable {
                            navController.navigate(Screen.Education.route)
                        },
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

@Composable
fun ExperienceCompose(
    navController: NavController,
    viewModel: ProfileViewModel,
) {
    val responseExperience = viewModel.responseExperience.value

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

                Row(
                    modifier = Modifier
                        .wrapContentSize()
                        .clickable {
                            navController.navigate(Screen.Experience.route)
                        },
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit, contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )

                }
            }
            when (responseExperience) {
                is ExperienceUiState.Success -> {
                    responseExperience.data.forEachIndexed { index, experience ->
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
                                } else{
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
                }
                is ExperienceUiState.Failure -> {
                    Text(text = responseExperience.error.message ?: "Unknown Error")
                }
                ExperienceUiState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(align = Alignment.Center)
                    )
                }
                ExperienceUiState.Empty -> {
                    Text(text = "Empty Data")
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp)
                    .clickable {
                        navController.navigate(Screen.Experience.route)
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
    viewModel: ProfileViewModel,
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

                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            Text(
                text = viewModel.about.toString(),
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(top = 20.dp)
            )
        }
    }
}

@Composable
fun MenuCompose(
    navController: NavController,
    viewModel: ProfileViewModel
){
    val openDialog = remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 20.dp, bottom = 10.dp, start = 30.dp, end = 30.dp)
            ) {
                Text(
                    text = "Privacy Policy",
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.weight(1f)
                )

                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(24.dp)
                )

            }

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp),
                color = Color.Gray
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 10.dp, bottom = 10.dp, start = 30.dp, end = 30.dp)
            ) {
                Text(
                    text = "About App",
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.weight(1f)
                )

                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(24.dp)
                )

            }

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp),
                color = Color.Gray
            )

            Row(
                modifier = Modifier
                    .padding(20.dp)
            ) {

                Button(
                    onClick = { openDialog.value = true },
                    modifier = Modifier.weight(0.4f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red
                    ),
                ) {

                    Text(text = "Logout")

                }
                if (openDialog.value) {
                    AlertLogout(openDialog = openDialog) {
                        openDialog.value = false
                        viewModel.logout()
                        navController.navigate(Screen.Login.route) {
                            popUpTo(Screen.Profile.route) {
                                inclusive = true
                            }
                            popUpTo(Screen.Home.route) {
                                inclusive = true
                            }
                            popUpTo(Screen.HomeAdmin.route) {
                                inclusive = true
                            }
                        }
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