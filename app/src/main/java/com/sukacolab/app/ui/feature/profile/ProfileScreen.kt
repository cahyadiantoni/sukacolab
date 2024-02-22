package com.sukacolab.app.ui.feature.profile

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sukacolab.app.R
import com.sukacolab.app.ui.component.alert.AlertLogout
import com.sukacolab.app.ui.feature.profile.uiState.ExperienceUiState
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
                        ){
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
                    ){
                        Button(
                            onClick = { navController.navigate(
                                Screen.Resume.createRoute(
                                    encodedUrl
                                )
                            ) },
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

            }

            item {
                AboutCompose(
                    viewModel = viewModel
                )
                borderCompose()
            }

            item {
                ExperienceCompose()
                borderCompose()
            }

            item {
                LicenseCompose()
                borderCompose()
            }

            item {
                SkillsCompose()
                borderCompose()
            }

            item {
                EducationCompose()
                borderCompose()
            }

            item {
                MenuCompose(
                    navController = navController,
                    viewModel = viewModel
                )
                borderCompose()
            }
        }
    }
}

@Preview
@Composable
fun SkillsCompose() {

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

                Row(modifier = Modifier.wrapContentSize(),
                    horizontalArrangement = Arrangement.spacedBy(20.dp)) {

                    Icon(
                        imageVector = Icons.Default.Add, contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                    )
                    Icon(
                        imageVector = Icons.Default.Edit, contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                    )
                }
            }

            Text(
                text = "Kotlin",
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            ) {

                Image(painter = painterResource(id = R.drawable.skills),
                    modifier = Modifier.size(40.dp),
                    contentDescription = null,
                    contentScale = ContentScale.Crop)

                Text(
                    text = "Associate Software Engineer at IBM",
                    fontWeight = FontWeight.Medium,
                    fontSize = 15.sp,
                    modifier = Modifier.padding(7.dp)
                )
            }

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                color = Color.LightGray
            )

            Text(
                text = "Android",
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            ) {

                Image(painter = painterResource(id = R.drawable.skills),
                    modifier = Modifier.size(40.dp),
                    contentDescription = null,
                    contentScale = ContentScale.Crop)

                Text(
                    text = "Associate Software Engineer at IBM",
                    fontWeight = FontWeight.Medium,
                    fontSize = 15.sp,
                    modifier = Modifier.padding(7.dp)
                )
            }

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                color = Color.LightGray
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp),
                contentAlignment = Alignment.Center
            ) {

                Row(
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    modifier = Modifier.wrapContentSize()
                ) {

                    Text(
                        text = "Show All 29 skills",
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


@Preview
@Composable
fun LicenseCompose() {

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
                        text = "Licenses & certifications",
                        fontWeight = FontWeight.Bold,
                    )

                }

                Row(
                    modifier = Modifier.wrapContentSize(),
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {

                    Icon(
                        imageVector = Icons.Default.Add, contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Icon(
                        imageVector = Icons.Default.Edit, contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )

                }
            }

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
                        text = "Azure Developer Associate",
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Microsoft",
                        fontWeight = FontWeight.Medium,
                    )

                    Text(
                        text = "Issued Jan 2023 - Expired Jan 2024",
                        fontWeight = FontWeight.Normal,
                    )

                    Box(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(top = 10.dp)
                            .border(1.dp, Color.DarkGray, shape = RoundedCornerShape(20.dp))
                    ) {

                        Text(
                            text = "Show credential", modifier = Modifier
                                .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp),
                            fontWeight = FontWeight.Light,
                        )
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun EducationCompose() {

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
                        text = "Education",
                        fontWeight = FontWeight.Bold,
                    )

                }

                Row(
                    modifier = Modifier.wrapContentSize(),
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {

                    Icon(
                        imageVector = Icons.Default.Add, contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Icon(
                        imageVector = Icons.Default.Edit, contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )

                }
            }

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
                        text = "Amity University",
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Master of Computer Applications - MCA",
                        fontWeight = FontWeight.Medium,
                    )

                    Text(
                        text = "2019 - 2022",
                        fontWeight = FontWeight.Normal,
                    )
                }
            }

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
                        text = "Delhi University",
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Bachelor of Science, BSc. (CS)",
                        fontWeight = FontWeight.Medium,
                    )

                    Text(
                        text = "2015 - 2018",
                        fontWeight = FontWeight.Normal,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ExperienceCompose() {
    val viewModel: ProfileViewModel = getViewModel()
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
                        text = "Experience",
                        fontWeight = FontWeight.Bold,
                    )

                }

                Row(
                    modifier = Modifier.wrapContentSize(),
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {

                    Icon(
                        imageVector = Icons.Default.Add, contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
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
                                )

                                Text(
                                    text = "${experience.company} - ${experience.role}",
                                    fontWeight = FontWeight.Medium,
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
                                )
                            }
                        }
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