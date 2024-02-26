package com.sukacolab.app.ui.feature.user.profile.sub_screen.experience.add_experience

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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sukacolab.app.ui.component.PrimaryButton
import com.sukacolab.app.ui.component.StatelessTopBar
import com.sukacolab.app.ui.component.fields.CheckboxField
import com.sukacolab.app.ui.component.fields.DateField
import com.sukacolab.app.ui.component.fields.PickerField
import com.sukacolab.app.ui.component.fields.TextField
import com.sukacolab.app.ui.feature.user.profile.ProfileViewModel
import com.sukacolab.app.ui.navigation.Screen
import com.sukacolab.app.util.form.formatters.dateShort
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddExperienceScreen(
    navController: NavController,
){
    val viewModel: AddExperienceViewModel = getViewModel()

    val context = LocalContext.current

    val addExperienceResult by viewModel.addExperienceResult.observeAsState()
    val isLoading = viewModel.isLoading.value

    LaunchedEffect(key1 = addExperienceResult) {
        when (addExperienceResult) {
            is AddExperienceResults.Success -> {
                val message = (addExperienceResult as AddExperienceResults.Success).message
                Log.d("Add Experience", "Sukses: $message")
                Toast.makeText(context, "Success : $message", Toast.LENGTH_SHORT).show()
                navController.navigate(Screen.Experience.route) {
                    popUpTo(Screen.Experience.route) {
                        inclusive = true
                    }
                }
            }
            is AddExperienceResults.Error -> {
                val errorMessage = (addExperienceResult as AddExperienceResults.Error).errorMessage
                Log.d("Add Experience", "Gagal: $errorMessage")
                Toast.makeText(context, "Failed : $errorMessage", Toast.LENGTH_SHORT).show()
                navController.navigate(Screen.AddExperience.route) {
                    popUpTo(Screen.AddExperience.route) {
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
                title = "Add Experience",
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
                                label = "Posisi",
                                form = viewModel.form,
                                fieldState = viewModel.form.title,
                                keyboardType = KeyboardType.Text,
                            ).Field()

                            TextField(
                                modifier = Modifier.padding(bottom = 8.dp),
                                label = "Nama Perusahaan",
                                form = viewModel.form,
                                fieldState = viewModel.form.company,
                                keyboardType = KeyboardType.Text,
                            ).Field()

                            PickerField(
                                modifier = Modifier.padding(bottom = 8.dp),
                                label = "Jenis Pekerjaan",
                                form = viewModel.form,
                                fieldState = viewModel.form.role
                            ).Field()

                            DateField(
                                modifier = Modifier.padding(bottom = 8.dp),
                                label = "Tanggal mulai",
                                form = viewModel.form,
                                fieldState = viewModel.form.startDate,
                                formatter = ::dateShort
                            ).Field()

                            CheckboxField(
                                modifier = Modifier.padding(bottom = 8.dp),
                                fieldState = viewModel.form.isNow,
                                label = "Ini adalah peran saya pada saat ini",
                                form = viewModel.form
                            ).Field()

                            DateField(
                                modifier = Modifier.padding(bottom = 8.dp),
                                label = "Tanggal Berakhir",
                                form = viewModel.form,
                                fieldState = viewModel.form.endDate,
                                formatter = ::dateShort
                            ).Field()

                            Box(modifier = Modifier.fillMaxWidth().padding(top = 10.dp)){
                                if (isLoading) {
                                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                                } else {
                                    PrimaryButton(title = "Add Experience", onClick = {
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