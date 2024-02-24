package com.sukacolab.app.ui.feature.user.profile.subScreen.skill

import androidx.compose.foundation.Image
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
import com.sukacolab.app.ui.feature.user.profile.ui_state.SkillUiState
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SkillScreen(
    navController: NavController,
) {
    val viewModel: SkillViewModel = getViewModel()
    val responseSkill = viewModel.responseAllSkill.value

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
                title = "All Skill",
                actionIcon = {
                    IconButton(onClick = {

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
                            when (responseSkill) {
                                is SkillUiState.Success -> {
                                    if (responseSkill.data.isEmpty()) {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .padding(top = 30.dp),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                text = "Skill belum ditambahkan",
                                                fontWeight = FontWeight.Light
                                            )
                                        }
                                    }else{
                                        responseSkill.data.forEachIndexed { index, skill ->
                                            Row(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(top = 10.dp),
                                                horizontalArrangement = Arrangement.SpaceBetween
                                            ) {
                                                Column(Modifier.weight(1f)) {
                                                    Text(
                                                        text = skill.name,
                                                        fontWeight = FontWeight.Bold,
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
                                                        tint = MaterialTheme.colorScheme.primary
                                                    )
                                                }
                                            }

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
                        }
                    }
                }
            }
        }
    }
}