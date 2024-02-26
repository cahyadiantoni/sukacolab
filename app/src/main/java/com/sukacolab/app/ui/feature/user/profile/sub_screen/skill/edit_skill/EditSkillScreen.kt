package com.sukacolab.app.ui.feature.user.profile.sub_screen.skill.edit_skill

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import com.sukacolab.app.ui.component.PrimaryButton
import com.sukacolab.app.ui.component.StatelessTopBar
import com.sukacolab.app.ui.component.fields.TextField
import com.sukacolab.app.ui.feature.user.profile.sub_screen.skill.SkillViewModel
import com.sukacolab.app.ui.feature.user.profile.ui_state.DetailSkillUiState
import com.sukacolab.app.ui.navigation.Screen
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditSkillScreen(
    navController: NavController,
    id: String
){
    val viewModel: EditSkillViewModel = getViewModel()

    val skillViewModel: SkillViewModel = getViewModel()
    val idState = remember { mutableStateOf("") }
    val lifecycle: Lifecycle = LocalLifecycleOwner.current.lifecycle

    val context = LocalContext.current

    val editSkillResult by viewModel.editSkillResult.observeAsState()
    val isLoading = viewModel.isLoading.value

    LaunchedEffect(key1 = Unit) {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            launch {
                idState.value = id
                skillViewModel.getDetailSkill(id = id)
            }
        }
    }

    LaunchedEffect(key1 = editSkillResult) {
        when (editSkillResult) {
            is EditSkillResults.Success -> {
                val message = (editSkillResult as EditSkillResults.Success).message
                Log.d("Edit Skill", "Sukses: $message")
                Toast.makeText(context, "Success : $message", Toast.LENGTH_SHORT).show()
                navController.navigate(Screen.Skill.route) {
                    popUpTo(Screen.Skill.route) {
                        inclusive = true
                    }
                }
            }
            is EditSkillResults.Error -> {
                val errorMessage = (editSkillResult as EditSkillResults.Error).errorMessage
                Log.d("Edit Skill", "Gagal: $errorMessage")
                Toast.makeText(context, "Failed : $errorMessage", Toast.LENGTH_SHORT).show()
                navController.navigate(Screen.EditSkill.route) {
                    popUpTo(Screen.EditSkill.route) {
                        inclusive = true
                    }
                }
            }
            else -> {
                // Initial state or loading state
            }
        }
    }

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
                title = "Edit Skill",
                actionIcon = {
                }
            )
        },
        content = {
            Box(modifier = Modifier.padding(it)) {
                Column(modifier = Modifier.padding(50.dp)) {
                    val responseSkill = skillViewModel.responseDetailSkill.value
                    Log.d("Response Isi", "$responseSkill")
                    when (responseSkill) {
                        is DetailSkillUiState.Loading -> {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .wrapContentSize(align = Alignment.Center)
                            )
                        }
                        is DetailSkillUiState.Success -> {
                            Row(
                                modifier = Modifier
                                    .weight(1f)
                                    .verticalScroll(rememberScrollState())
                            ) {
                                Column {
                                    viewModel.form.name.state.value = responseSkill.data.name
                                    TextField(
                                        modifier = Modifier.padding(bottom = 8.dp),
                                        label = "Nama Skill",
                                        form = viewModel.form,
                                        fieldState = viewModel.form.name,
                                        keyboardType = KeyboardType.Text,
                                    ).Field()

                                    viewModel.form.description.state.value = responseSkill.data.description
                                    TextField(
                                        modifier = Modifier.padding(bottom = 8.dp),
                                        label = "Deskripsi skill digunakan",
                                        form = viewModel.form,
                                        fieldState = viewModel.form.description,
                                        keyboardType = KeyboardType.Text,
                                    ).Field()

                                    Box(modifier = Modifier.fillMaxWidth().padding(top = 10.dp)){
                                        if (isLoading) {
                                            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                                        } else {
                                            PrimaryButton(title = "Edit Skill", onClick = {
                                                viewModel.validate(responseSkill.data.id.toString())
                                            })
                                        }
                                    }
                                }
                            }
                        }
                        is DetailSkillUiState.Failure -> {
                            Text(text = responseSkill.error.message ?: "Unknown Error")
                        }
                        DetailSkillUiState.Empty -> {
                            Text(text = "Empty Data")
                        }
                    }
                }
            }
        }
    )
}