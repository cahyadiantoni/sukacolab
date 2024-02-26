package com.sukacolab.app.ui.feature.user.profile.sub_screen.certification

import android.util.Log
import android.widget.Toast
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sukacolab.app.R
import com.sukacolab.app.ui.component.StatelessTopBar
import com.sukacolab.app.ui.component.alert.AlertDelete
import com.sukacolab.app.ui.feature.user.profile.ui_state.CertificationUiState
import com.sukacolab.app.ui.navigation.Screen
import com.sukacolab.app.util.convertToMonthYearFormat
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CertificationScreen(
    navController: NavController,
) {
    val viewModel: CertificationViewModel = getViewModel()
    val responseCertification = viewModel.responseAllCertification.value

    val context = LocalContext.current

    val deleteCertificationResult by viewModel.deleteCertificationResult.observeAsState()
    val isLoading = viewModel.isLoading.value

    LaunchedEffect(key1 = deleteCertificationResult) {
        when (deleteCertificationResult) {
            is DeleteCertificationResults.Success -> {
                val message = (deleteCertificationResult as DeleteCertificationResults.Success).message
                Log.d("Delete Certification", "Sukses: $message")
                Toast.makeText(context, "Success : $message", Toast.LENGTH_SHORT).show()
                navController.navigate(Screen.Certification.route) {
                    popUpTo(Screen.Certification.route) {
                        inclusive = true
                    }
                }
            }
            is DeleteCertificationResults.Error -> {
                val errorMessage = (deleteCertificationResult as DeleteCertificationResults.Error).errorMessage
                Log.d("Delete Certification", "Gagal: $errorMessage")
                Toast.makeText(context, "Failed : $errorMessage", Toast.LENGTH_SHORT).show()
                navController.navigate(Screen.Certification.route) {
                    popUpTo(Screen.Certification.route) {
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
                title = "All Certification",
                actionIcon = {
                    IconButton(onClick = {
                        navController.navigate(Screen.AddCertification.route) {
                            popUpTo(Screen.AddCertification.route) {
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
                            when (responseCertification) {
                                is CertificationUiState.Success -> {
                                    if (responseCertification.data.isEmpty()) {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .padding(top = 30.dp),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                text = "Certification belum ditambahkan",
                                                fontWeight = FontWeight.Light
                                            )
                                        }
                                    }else{
                                        responseCertification.data.forEachIndexed { index, certification ->
                                            Row(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(top = 10.dp)
                                            ) {

                                                Image(
                                                    painter = painterResource(id = R.drawable.certi),
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
                                                                text = certification.name,
                                                                fontWeight = FontWeight.Bold,
                                                                lineHeight = 18.sp
                                                            )

                                                            Text(
                                                                text = certification.publisher,
                                                                fontWeight = FontWeight.Medium,
                                                                lineHeight = 18.sp
                                                            )
                                                        }

                                                        val openDialog = remember { mutableStateOf(false) }

                                                        Row(modifier = Modifier.wrapContentSize(),
                                                            horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                                            if (isLoading) {
                                                                CircularProgressIndicator()
                                                            } else {
                                                                Icon(
                                                                    imageVector = Icons.Default.Delete,
                                                                    contentDescription = null,
                                                                    tint = MaterialTheme.colorScheme.primary,
                                                                    modifier = Modifier.clickable {
                                                                        openDialog.value = true
                                                                    }
                                                                )

                                                                if (openDialog.value) {
                                                                    AlertDelete(openDialog = openDialog) {
                                                                        openDialog.value = false
                                                                        viewModel.deleteCertification(
                                                                            certification.id.toString()
                                                                        )
                                                                    }
                                                                }

                                                                Icon(
                                                                    imageVector = Icons.Default.Edit,
                                                                    contentDescription = null,
                                                                    tint = MaterialTheme.colorScheme.primary,
                                                                    modifier = Modifier.clickable {
                                                                        navController.navigate(
                                                                            Screen.EditCertification.createRoute(
                                                                                certification.id
                                                                            )
                                                                        )
                                                                    }
                                                                )
                                                            }
                                                        }
                                                    }

                                                    val start = certification.publishDate.convertToMonthYearFormat()
                                                    val end = certification.expireDate.convertToMonthYearFormat()

                                                    Text(
                                                        text = "Issued $start - Expired $end",
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
                                is CertificationUiState.Failure -> {
                                    Text(text = responseCertification.error.message ?: "Unknown Error")
                                }
                                CertificationUiState.Loading -> {
                                    CircularProgressIndicator(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .wrapContentSize(align = Alignment.Center)
                                    )
                                }
                                CertificationUiState.Empty -> {
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