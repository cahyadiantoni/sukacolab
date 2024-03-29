package com.sukacolab.app.ui.feature.user.profile.sub_screen.skill.add_skill

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sukacolab.app.ui.component.PrimaryButton
import com.sukacolab.app.ui.component.StatelessTopBar
import com.sukacolab.app.ui.component.fields.DateField
import com.sukacolab.app.ui.component.fields.TextField
import com.sukacolab.app.ui.navigation.Screen
import com.sukacolab.app.util.form.formatters.dateShort
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddSkillScreen(
    navController: NavController,
){
    val viewModel: AddSkillViewModel = getViewModel()

    val context = LocalContext.current

    val addSkillResult by viewModel.addSkillResult.observeAsState()
    val isLoading = viewModel.isLoading.value

    LaunchedEffect(key1 = addSkillResult) {
        when (addSkillResult) {
            is AddSkillResults.Success -> {
                val message = (addSkillResult as AddSkillResults.Success).message
                Log.d("Add Skill", "Sukses: $message")
                Toast.makeText(context, "Success : $message", Toast.LENGTH_SHORT).show()
                navController.navigate(Screen.Skill.route) {
                    popUpTo(Screen.Skill.route) {
                        inclusive = true
                    }
                }
            }
            is AddSkillResults.Error -> {
                val errorMessage = (addSkillResult as AddSkillResults.Error).errorMessage
                Log.d("Add Skill", "Gagal: $errorMessage")
                Toast.makeText(context, "Failed : $errorMessage", Toast.LENGTH_SHORT).show()
                navController.navigate(Screen.AddSkill.route) {
                    popUpTo(Screen.AddSkill.route) {
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
                title = "Add Skill",
                actionIcon = {
                }
            )
        },
        content = {
            Box(modifier = Modifier.padding(it)) {
                Column(modifier = Modifier.padding(50.dp)) {
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .verticalScroll(rememberScrollState())
                    ) {

                        Column {
                            TextField(
                                modifier = Modifier.padding(bottom = 8.dp),
                                label = "Nama Skill",
                                form = viewModel.form,
                                fieldState = viewModel.form.name,
                                keyboardType = KeyboardType.Text,
                            ).Field()

                            TextField(
                                modifier = Modifier.padding(bottom = 8.dp),
                                label = "Deskripsi skill digunakan",
                                form = viewModel.form,
                                fieldState = viewModel.form.description,
                                imeAction = ImeAction.Done,
                                keyboardType = KeyboardType.Text,
                            ).Field()

                            Box(modifier = Modifier.fillMaxWidth().padding(top = 10.dp)){
                                if (isLoading) {
                                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                                } else {
                                    PrimaryButton(title = "Add Skill", onClick = {
                                        viewModel.validate()
                                    })
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}