package com.sukacolab.app.ui.feature.user.profile.sub_screen.certification.edit_certification

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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import com.sukacolab.app.ui.component.PrimaryButton
import com.sukacolab.app.ui.component.StatelessTopBar
import com.sukacolab.app.ui.component.fields.DateField
import com.sukacolab.app.ui.component.fields.TextField
import com.sukacolab.app.ui.feature.user.profile.sub_screen.certification.CertificationViewModel
import com.sukacolab.app.ui.feature.user.profile.ui_state.DetailCertificationUiState
import com.sukacolab.app.ui.navigation.Screen
import com.sukacolab.app.util.form.formatters.dateShort
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditCertificationScreen(
    navController: NavController,
    id: String,
){
    val viewModel: EditCertificationViewModel = getViewModel()

    val certificationViewModel: CertificationViewModel = getViewModel()
    val idState = remember { mutableStateOf("") }
    val lifecycle: Lifecycle = LocalLifecycleOwner.current.lifecycle

    val context = LocalContext.current

    val editCertificationResult by viewModel.editCertificationResult.observeAsState()
    val isLoading = viewModel.isLoading.value

    LaunchedEffect(key1 = Unit) {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            launch {
                idState.value = id
                certificationViewModel.getDetailCertification(id = id)
            }
        }
    }

    LaunchedEffect(key1 = editCertificationResult) {
        when (editCertificationResult) {
            is EditCertificationResults.Success -> {
                val message = (editCertificationResult as EditCertificationResults.Success).message
                Log.d("Edit Certification", "Sukses: $message")
                Toast.makeText(context, "Success : $message", Toast.LENGTH_SHORT).show()
                navController.navigate(Screen.Certification.route) {
                    popUpTo(Screen.Certification.route) {
                        inclusive = true
                    }
                }
            }
            is EditCertificationResults.Error -> {
                val errorMessage = (editCertificationResult as EditCertificationResults.Error).errorMessage
                Log.d("Edit Certification", "Gagal: $errorMessage")
                Toast.makeText(context, "Failed : $errorMessage", Toast.LENGTH_SHORT).show()
                navController.navigate(Screen.EditCertification.route) {
                    popUpTo(Screen.EditCertification.route) {
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
                title = "Edit Certification",
                actionIcon = {
                }
            )
        },
        content = {
            Box(modifier = Modifier.padding(it)) {
                Column(modifier = Modifier.padding(50.dp)) {
                    val responseCertification = certificationViewModel.responseDetailCertification.value
                    Log.d("Response Isi", "$responseCertification")
                    when (responseCertification) {
                        is DetailCertificationUiState.Loading -> {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .wrapContentSize(align = Alignment.Center)
                            )
                        }
                        is DetailCertificationUiState.Success -> {
                            Row(
                                modifier = Modifier
                                    .weight(1f)
                                    .verticalScroll(rememberScrollState())
                            ) {
                                Column {

                                    viewModel.form.name.state.value = responseCertification.data.name
                                    TextField(
                                        modifier = Modifier.padding(bottom = 8.dp),
                                        label = "Nama Sertifikat",
                                        form = viewModel.form,
                                        fieldState = viewModel.form.name,
                                        keyboardType = KeyboardType.Text,
                                    ).Field()

                                    viewModel.form.publisher.state.value = responseCertification.data.publisher
                                    TextField(
                                        modifier = Modifier.padding(bottom = 8.dp),
                                        label = "Organisasi penerbit",
                                        form = viewModel.form,
                                        fieldState = viewModel.form.publisher,
                                        keyboardType = KeyboardType.Text,
                                    ).Field()

                                    viewModel.form.credential.state.value = responseCertification.data.credential
                                    TextField(
                                        modifier = Modifier.padding(bottom = 8.dp),
                                        label = "Kredensial sertifikat",
                                        form = viewModel.form,
                                        fieldState = viewModel.form.credential,
                                        imeAction = ImeAction.Done,
                                        keyboardType = KeyboardType.Text,
                                    ).Field()

                                    val dateStart = responseCertification.data.publishDate
                                    if(dateStart != null){
                                        val defaultDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(dateStart)
                                        viewModel.form.startDate.state.value = defaultDate
                                    }
                                    DateField(
                                        modifier = Modifier.padding(bottom = 8.dp),
                                        label = "Tanggal terbit",
                                        form = viewModel.form,
                                        fieldState = viewModel.form.startDate,
                                        formatter = ::dateShort
                                    ).Field()

                                    val dateEnd = responseCertification.data.expireDate
                                    if(dateEnd != null){
                                        val defaultEndDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(dateEnd)
                                        viewModel.form.endDate.state.value = defaultEndDate
                                    }
                                    DateField(
                                        modifier = Modifier.padding(bottom = 8.dp),
                                        label = "Tanggal kadaluarsa",
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
                                            PrimaryButton(title = "Edit Certification", onClick = {
                                                viewModel.validate(responseCertification.data.id.toString())
                                            })
                                        }
                                    }
                                }
                            }
                        }
                        is DetailCertificationUiState.Failure -> {
                            Text(text = responseCertification.error.message ?: "Unknown Error")
                        }
                        DetailCertificationUiState.Empty -> {
                            Text(text = "Empty Data")
                        }
                    }
                }
            }
        }
    )
}