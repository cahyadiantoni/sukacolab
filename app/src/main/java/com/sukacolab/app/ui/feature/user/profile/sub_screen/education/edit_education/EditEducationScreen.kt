package com.sukacolab.app.ui.feature.user.profile.sub_screen.education.edit_education

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
import com.sukacolab.app.ui.component.fields.CheckboxField
import com.sukacolab.app.ui.component.fields.DateField
import com.sukacolab.app.ui.component.fields.TextField
import com.sukacolab.app.ui.feature.user.profile.sub_screen.education.EducationViewModel
import com.sukacolab.app.ui.feature.user.profile.ui_state.DetailEducationUiState
import com.sukacolab.app.ui.navigation.Screen
import com.sukacolab.app.util.form.formatters.dateShort
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditEducationScreen(
    navController: NavController,
    id: String,
){
    val viewModel: EditEducationViewModel = getViewModel()
    
    val educationViewModel: EducationViewModel = getViewModel()
    val idState = remember { mutableStateOf("") }
    val lifecycle: Lifecycle = LocalLifecycleOwner.current.lifecycle
    
    val context = LocalContext.current

    val editEducationResult by viewModel.editEducationResult.observeAsState()
    val isLoading = viewModel.isLoading.value

    LaunchedEffect(key1 = Unit) {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            launch {
                idState.value = id
                educationViewModel.getDetailEducation(id = id)
            }
        }
    }

    LaunchedEffect(key1 = editEducationResult) {
        when (editEducationResult) {
            is EditEducationResults.Success -> {
                val message = (editEducationResult as EditEducationResults.Success).message
                Log.d("Edit Education", "Sukses: $message")
                Toast.makeText(context, "Success : $message", Toast.LENGTH_SHORT).show()
                navController.navigate(Screen.Education.route) {
                    popUpTo(Screen.Education.route) {
                        inclusive = true
                    }
                }
            }
            is EditEducationResults.Error -> {
                val errorMessage = (editEducationResult as EditEducationResults.Error).errorMessage
                Log.d("Edit Education", "Gagal: $errorMessage")
                Toast.makeText(context, "Failed : $errorMessage", Toast.LENGTH_SHORT).show()
                navController.navigate(Screen.EditEducation.route) {
                    popUpTo(Screen.EditEducation.route) {
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
                title = "Edit Education",
                actionIcon = {
                }
            )
        },
        content = {
            Box(modifier = Modifier.padding(it)) {
                Column(modifier = Modifier.padding(50.dp)) {
                    val responseEducation = educationViewModel.responseDetailEducation.value
                    Log.d("Response Isi", "$responseEducation")
                    when (responseEducation) {
                        is DetailEducationUiState.Loading -> {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .wrapContentSize(align = Alignment.Center)
                            )
                        }
                        is DetailEducationUiState.Success -> {
                            Row(
                                modifier = Modifier
                                    .weight(1f)
                                    .verticalScroll(rememberScrollState())
                            ) {

                                Column {
                                    viewModel.form.instansi.state.value = responseEducation.data.instansi
                                    TextField(
                                        modifier = Modifier.padding(bottom = 8.dp),
                                        label = "Instansi",
                                        form = viewModel.form,
                                        fieldState = viewModel.form.instansi,
                                        keyboardType = KeyboardType.Text,
                                    ).Field()

                                    viewModel.form.major.state.value = responseEducation.data.major
                                    TextField(
                                        modifier = Modifier.padding(bottom = 8.dp),
                                        label = "Jurusan / Keahlian",
                                        form = viewModel.form,
                                        fieldState = viewModel.form.major,
                                        keyboardType = KeyboardType.Text,
                                    ).Field()

                                    val dateStart = responseEducation.data.startDate
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

                                    if (responseEducation.data.isNow == 1){
                                        viewModel.form.isNow.state.value = true
                                    }
                                    CheckboxField(
                                        modifier = Modifier.padding(bottom = 8.dp),
                                        fieldState = viewModel.form.isNow,
                                        label = "Ini adalah instansi saya pada saat ini",
                                        form = viewModel.form
                                    ).Field()

                                    val dateEnd = responseEducation.data.endDate
                                    if(dateEnd != null){
                                        val defaultEndDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(dateEnd)
                                        viewModel.form.endDate.state.value = defaultEndDate
                                    }
                                    DateField(
                                        modifier = Modifier.padding(bottom = 8.dp),
                                        label = "Tanggal Berakhir",
                                        form = viewModel.form,
                                        fieldState = viewModel.form.endDate,
                                        formatter = ::dateShort
                                    ).Field()

                                    Box(modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 10.dp)){
                                        if (isLoading) {
                                            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                                        } else {
                                            PrimaryButton(title = "Edit Education", onClick = {
                                                viewModel.validate(responseEducation.data.id.toString())
                                            })
                                        }
                                    }
                                }
                            }
                        }
                        is DetailEducationUiState.Failure -> {
                            Text(text = responseEducation.error.message ?: "Unknown Error")
                        }
                        DetailEducationUiState.Empty -> {
                            Text(text = "Empty Data")
                        }
                    }
                }
            }
        }
    )
}