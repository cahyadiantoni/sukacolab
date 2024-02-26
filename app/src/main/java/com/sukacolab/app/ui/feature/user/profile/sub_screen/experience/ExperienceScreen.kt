package com.sukacolab.app.ui.feature.user.profile.sub_screen.experience

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sukacolab.app.R
import com.sukacolab.app.ui.component.StatelessTopBar
import com.sukacolab.app.ui.feature.user.profile.ui_state.ExperienceUiState
import com.sukacolab.app.ui.navigation.Screen
import com.sukacolab.app.util.convertToMonthYearFormat
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExperienceScreen(
    navController: NavController,
) {
    val viewModel: ExperienceViewModel = getViewModel()
    val responseExperience = viewModel.responseAllExperience.value

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
                title = "All Experience",
                actionIcon = {
                    IconButton(onClick = {
                        navController.navigate(Screen.AddExperience.route) {
                            popUpTo(Screen.AddExperience.route) {
                                inclusive = true
                            }
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "add",
                            tint = Color.White
                        )
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
                    ) {

                        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                            when (responseExperience) {
                                is ExperienceUiState.Success -> {
                                    if (responseExperience.data.isEmpty()) {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .padding(top = 30.dp),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                text = "Experience belum ditambahkan",
                                                fontWeight = FontWeight.Light
                                            )
                                        }
                                    }else{
                                        responseExperience.data.forEachIndexed { index, experience ->
                                            Row(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(top = 10.dp),
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Image(
                                                    painter = painterResource(id = R.drawable.xp),
                                                    contentDescription = null,
                                                    modifier = Modifier.size(60.dp),
                                                    contentScale = ContentScale.Crop
                                                )

                                                Spacer(modifier = Modifier.size(10.dp))

                                                Column(modifier = Modifier.fillMaxWidth()) {

                                                    Row(
                                                        modifier = Modifier.fillMaxWidth(),
                                                        horizontalArrangement = Arrangement.SpaceBetween
                                                    ) {
                                                        Column(Modifier.weight(1f)) {
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
                                                        }

                                                        Row(modifier = Modifier.wrapContentSize(),
                                                            horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                                            Icon(
                                                                imageVector = Icons.Default.Delete,
                                                                contentDescription = null,
                                                                tint = MaterialTheme.colorScheme.primary
                                                            )

                                                            Icon(
                                                                imageVector = Icons.Default.Edit,
                                                                contentDescription = null,
                                                                tint = MaterialTheme.colorScheme.primary,
                                                                modifier = Modifier.clickable {
                                                                    navController.navigate(
                                                                        Screen.EditExperience.createRoute(
                                                                            experience.id
                                                                        )
                                                                    )
                                                                }
                                                            )
                                                        }
                                                    }

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
            }
        }
    }
}