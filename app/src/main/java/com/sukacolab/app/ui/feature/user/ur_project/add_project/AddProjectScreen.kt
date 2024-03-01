package com.sukacolab.app.ui.feature.user.ur_project.add_project

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.ColorScheme
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
import com.sukacolab.app.ui.component.alert.AlertProjectReview
import com.sukacolab.app.ui.component.alert.PrimaryAlert
import com.sukacolab.app.ui.component.fields.CheckboxField
import com.sukacolab.app.ui.component.fields.DateField
import com.sukacolab.app.ui.component.fields.PickerField
import com.sukacolab.app.ui.component.fields.TextField
import com.sukacolab.app.ui.navigation.Screen
import com.sukacolab.app.util.form.formatters.dateShort
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProjectScreen(
    navController: NavController,
){
    val viewModel: AddProjectViewModel = getViewModel()

    val context = LocalContext.current

    val addProjectResult by viewModel.addProjectResult.observeAsState()
    val isLoading = viewModel.isLoading.value

    LaunchedEffect(key1 = addProjectResult) {
        when (addProjectResult) {
            is AddProjectResults.Success -> {
                val message = (addProjectResult as AddProjectResults.Success).message
                Log.d("Add Project", "Sukses: $message")
                Toast.makeText(context, "Success : $message", Toast.LENGTH_SHORT).show()
                navController.navigate(Screen.UrProject.route) {
                    popUpTo(Screen.Project.route) {
                        inclusive = true
                    }
                }
            }
            is AddProjectResults.Error -> {
                val errorMessage = (addProjectResult as AddProjectResults.Error).errorMessage
                Log.d("Add Project", "Gagal: $errorMessage")
                Toast.makeText(context, "Failed : $errorMessage", Toast.LENGTH_SHORT).show()
                navController.navigate(Screen.AddProject.route) {
                    popUpTo(Screen.AddProject.route) {
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
                title = "Add Project",
                actionIcon = {
                }
            )
        },
        content = {
            Box(modifier = Modifier.padding(it)) {
                Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .verticalScroll(rememberScrollState())
                    ) {

                        Column {
                            TextField(
                                modifier = Modifier.padding(top = 20.dp, bottom = 8.dp),
                                label = "Nama Project",
                                form = viewModel.form,
                                fieldState = viewModel.form.name,
                                keyboardType = KeyboardType.Text,
                            ).Field()

                            PickerField(
                                modifier = Modifier.padding(bottom = 8.dp),
                                label = "Posisi",
                                form = viewModel.form,
                                fieldState = viewModel.form.position,
                                isSearchable = false
                            ).Field()

                            TextField(
                                modifier = Modifier.padding(bottom = 8.dp),
                                label = "(Other) Tulis Posisi",
                                form = viewModel.form,
                                fieldState = viewModel.form.otherPosition,
                                keyboardType = KeyboardType.Text,
                            ).Field()

                            CheckboxField(
                                modifier = Modifier.padding(bottom = 8.dp),
                                fieldState = viewModel.form.isRemote,
                                label = "Project ini remote",
                                form = viewModel.form
                            ).Field()

                            TextField(
                                modifier = Modifier.padding(bottom = 8.dp),
                                label = "Lokasi",
                                form = viewModel.form,
                                fieldState = viewModel.form.location,
                                keyboardType = KeyboardType.Text,
                            ).Field()

                            PickerField(
                                modifier = Modifier.padding(bottom = 8.dp),
                                label = "Tipe",
                                form = viewModel.form,
                                fieldState = viewModel.form.tipe,
                                isSearchable = false
                            ).Field()

                            TextField(
                                modifier = Modifier.padding(bottom = 8.dp),
                                label = "(Other) Tulis Tipe",
                                form = viewModel.form,
                                fieldState = viewModel.form.otherTipe,
                                keyboardType = KeyboardType.Text,
                            ).Field()

                            if(viewModel.form.tipe.state.value?.name == "Loker"){
                                Text(
                                    text = "Project Paid (digaji) akan diverifikasi terlebih dahulu oleh admin",
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }

                            PickerField(
                                modifier = Modifier.padding(bottom = 8.dp),
                                label = "Waktu",
                                form = viewModel.form,
                                fieldState = viewModel.form.time,
                                isSearchable = false
                            ).Field()

                            TextField(
                                modifier = Modifier.padding(bottom = 8.dp),
                                label = "Deskripsi Project",
                                form = viewModel.form,
                                fieldState = viewModel.form.description,
                                imeAction = ImeAction.Default,
                                keyboardType = KeyboardType.Text,
                            ).Field()

                            TextField(
                                modifier = Modifier.padding(bottom = 8.dp),
                                label = "Kriteria Pelamar",
                                form = viewModel.form,
                                fieldState = viewModel.form.requirements,
                                imeAction = ImeAction.Default,
                                keyboardType = KeyboardType.Text,
                            ).Field()

                            Box(modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp, bottom = 50.dp)){
                                if (isLoading) {
                                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                                } else {
                                    PrimaryButton(title = "Add Project", onClick = {
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