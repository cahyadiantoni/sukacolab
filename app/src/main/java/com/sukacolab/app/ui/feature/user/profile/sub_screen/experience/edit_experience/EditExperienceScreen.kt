package com.sukacolab.app.ui.feature.user.profile.sub_screen.experience.edit_experience

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import com.sukacolab.app.R
import com.sukacolab.app.ui.component.PrimaryButton
import com.sukacolab.app.ui.component.StatelessTopBar
import com.sukacolab.app.ui.component.fields.CheckboxField
import com.sukacolab.app.ui.component.fields.DateField
import com.sukacolab.app.ui.component.fields.PickerField
import com.sukacolab.app.ui.component.fields.TextField
import com.sukacolab.app.ui.feature.user.profile.sub_screen.experience.ExperienceViewModel
import com.sukacolab.app.ui.feature.user.profile.sub_screen.experience.Role
import com.sukacolab.app.ui.feature.user.profile.ui_state.DetailExperienceUiState
import com.sukacolab.app.ui.navigation.Screen
import com.sukacolab.app.util.form.formatters.dateShort
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditExperienceScreen(
    navController: NavController,
    id: String,
){
    val viewModel: EditExperienceViewModel = getViewModel()
    val experienceViewModel: ExperienceViewModel = getViewModel()
    val idState = remember { mutableStateOf("") }

    val context = LocalContext.current

    val editExperienceResult by viewModel.editExperienceResult.observeAsState()
    val isLoading = viewModel.isLoading.value

    val lifecycle: Lifecycle = LocalLifecycleOwner.current.lifecycle

    LaunchedEffect(key1 = Unit) {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            launch {
                idState.value = id
                experienceViewModel.getDetailExperience(id = id)
            }
        }
    }

    LaunchedEffect(key1 = editExperienceResult) {
        when (editExperienceResult) {
            is EditExperienceResults.Success -> {
                val message = (editExperienceResult as EditExperienceResults.Success).message
                Log.d("Edit Experience", "Sukses: $message")
                Toast.makeText(context, "Success : $message", Toast.LENGTH_SHORT).show()
                navController.navigate(Screen.Experience.route) {
                    popUpTo(Screen.Experience.route) {
                        inclusive = true
                    }
                }
            }
            is EditExperienceResults.Error -> {
                val errorMessage = (editExperienceResult as EditExperienceResults.Error).errorMessage
                Log.d("Edit Experience", "Gagal: $errorMessage")
                Toast.makeText(context, "Failed : $errorMessage", Toast.LENGTH_SHORT).show()
                navController.navigate(Screen.EditExperience.route) {
                    popUpTo(Screen.EditExperience.route) {
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
                title = "Edit Experience",
                actionIcon = {
                }
            )
        },
        content = {
            Box(modifier = Modifier.padding(it)) {
                Column(modifier = Modifier.padding(50.dp)) {
                    val responseExperience = experienceViewModel.responseDetailExperience.value
                    Log.d("Response Isi", "$responseExperience")
                    when (responseExperience) {
                        is DetailExperienceUiState.Loading -> {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .wrapContentSize(align = Alignment.Center)
                            )
                        }
                        is DetailExperienceUiState.Success -> {
                            Row(
                                modifier = Modifier
                                    .weight(1f)
                                    .verticalScroll(rememberScrollState())
                            ) {
                                Column {
                                    viewModel.form.title.state.value = responseExperience.data.title
                                    TextField(
                                        modifier = Modifier.padding(bottom = 8.dp),
                                        label = "Posisi",
                                        form = viewModel.form,
                                        fieldState = viewModel.form.title,
                                        keyboardType = KeyboardType.Text
                                    ).Field()

                                    viewModel.form.company.state.value = responseExperience.data.company
                                    TextField(
                                        modifier = Modifier.padding(bottom = 8.dp),
                                        label = "Nama Perusahaan",
                                        form = viewModel.form,
                                        fieldState = viewModel.form.company,
                                        keyboardType = KeyboardType.Text
                                    ).Field()

                                    val roleName = responseExperience.data.role
                                    val roleDescriptions = listOf(
                                        Role(name = "Full Time", nama = "Waktu penuh"),
                                        Role(name = "Part Time", nama = "Paruh waktu"),
                                        Role(name = "Enterpriser", nama = "Wiraswasta"),
                                        Role(name = "Freelance", nama = "Bekerja lepas"),
                                        Role(name = "Contract", nama = "Kontrak"),
                                        Role(name = "Internship", nama = "Magang")
                                    )
                                    val roleNama = roleDescriptions.find { it.name == roleName }?.nama ?: ""
                                    viewModel.form.role.state.value = Role(name = "$roleName", nama = "$roleNama")
                                    PickerField(
                                        modifier = Modifier.padding(bottom = 8.dp),
                                        label = "Jenis Pekerjaan",
                                        form = viewModel.form,
                                        fieldState = viewModel.form.role,
                                        isSearchable = false
                                    ).Field()

                                    val dateStart = responseExperience.data.startDate
                                    if(dateStart != null){
                                        val defaultDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(dateStart)
                                        viewModel.form.startDate.state.value = defaultDate
                                    }
                                    DateField(
                                        modifier = Modifier.padding(bottom = 8.dp),
                                        label = "Tanggal mulai",
                                        form = viewModel.form,
                                        fieldState = viewModel.form.startDate,
                                        formatter = ::dateShort
                                    ).Field()

                                    if (responseExperience.data.isNow == 1){
                                        viewModel.form.isNow.state.value = true
                                    }
                                    CheckboxField(
                                        modifier = Modifier.padding(bottom = 8.dp),
                                        fieldState = viewModel.form.isNow,
                                        label = "Ini adalah peran saya pada saat ini",
                                        form = viewModel.form
                                    ).Field()

                                    val dateEnd = responseExperience.data.endDate
                                    if(dateEnd != null){
                                        val defaultEndDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(dateEnd)
                                        viewModel.form.endDate.state.value = defaultEndDate
                                    }
                                    DateField(
                                        modifier = Modifier.padding(bottom = 8.dp),
                                        label = "Tanggal Berakhir",
                                        form = viewModel.form,
                                        fieldState = viewModel.form.endDate,
                                        imeAction = ImeAction.Done,
                                        formatter = ::dateShort
                                    ).Field()

                                    Box(modifier = Modifier.fillMaxWidth().padding(top = 10.dp)){
                                        if (isLoading) {
                                            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                                        } else {
                                            PrimaryButton(title = "Edit Experience", onClick = {
                                                viewModel.validate(responseExperience.data.id.toString())
                                            })
                                        }
                                    }
                                }
                            }
                        }
                        is DetailExperienceUiState.Failure -> {
                            Text(text = responseExperience.error.message ?: "Unknown Error")
                        }
                        DetailExperienceUiState.Empty -> {
                            Text(text = "Empty Data")
                        }
                    }
                }
            }
        }
    )
}