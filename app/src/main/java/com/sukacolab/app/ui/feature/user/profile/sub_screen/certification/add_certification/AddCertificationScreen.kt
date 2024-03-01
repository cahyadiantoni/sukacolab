package com.sukacolab.app.ui.feature.user.profile.sub_screen.certification.add_certification

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
import com.sukacolab.app.ui.component.fields.CheckboxField
import com.sukacolab.app.ui.component.fields.DateField
import com.sukacolab.app.ui.component.fields.PickerField
import com.sukacolab.app.ui.component.fields.TextField
import com.sukacolab.app.ui.navigation.Screen
import com.sukacolab.app.util.form.formatters.dateShort
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCertificationScreen(
    navController: NavController,
){
    val viewModel: AddCertificationViewModel = getViewModel()

    val context = LocalContext.current

    val addCertificationResult by viewModel.addCertificationResult.observeAsState()
    val isLoading = viewModel.isLoading.value

    LaunchedEffect(key1 = addCertificationResult) {
        when (addCertificationResult) {
            is AddCertificationResults.Success -> {
                val message = (addCertificationResult as AddCertificationResults.Success).message
                Log.d("Add Certification", "Sukses: $message")
                Toast.makeText(context, "Success : $message", Toast.LENGTH_SHORT).show()
                navController.navigate(Screen.Certification.route) {
                    popUpTo(Screen.Certification.route) {
                        inclusive = true
                    }
                }
            }
            is AddCertificationResults.Error -> {
                val errorMessage = (addCertificationResult as AddCertificationResults.Error).errorMessage
                Log.d("Add Certification", "Gagal: $errorMessage")
                Toast.makeText(context, "Failed : $errorMessage", Toast.LENGTH_SHORT).show()
                navController.navigate(Screen.AddCertification.route) {
                    popUpTo(Screen.AddCertification.route) {
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
                title = "Add Certification",
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
                                label = "Nama Sertifikat",
                                form = viewModel.form,
                                fieldState = viewModel.form.name,
                                keyboardType = KeyboardType.Text,
                            ).Field()

                            TextField(
                                modifier = Modifier.padding(bottom = 8.dp),
                                label = "Organisasi penerbit",
                                form = viewModel.form,
                                fieldState = viewModel.form.publisher,
                                keyboardType = KeyboardType.Text,
                            ).Field()

                            TextField(
                                modifier = Modifier.padding(bottom = 8.dp),
                                label = "Kredensial sertifikat",
                                form = viewModel.form,
                                fieldState = viewModel.form.credential,
                                keyboardType = KeyboardType.Text,
                            ).Field()

                            DateField(
                                modifier = Modifier.padding(bottom = 8.dp),
                                label = "Tanggal terbit",
                                form = viewModel.form,
                                fieldState = viewModel.form.startDate,
                                formatter = ::dateShort
                            ).Field()

                            DateField(
                                modifier = Modifier.padding(bottom = 8.dp),
                                label = "Tanggal kadaluarsa",
                                form = viewModel.form,
                                fieldState = viewModel.form.endDate,
                                imeAction = ImeAction.Done,
                                formatter = ::dateShort
                            ).Field()

                            Box(modifier = Modifier.fillMaxWidth().padding(top = 10.dp)){
                                if (isLoading) {
                                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                                } else {
                                    PrimaryButton(title = "Add Certification", onClick = {
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