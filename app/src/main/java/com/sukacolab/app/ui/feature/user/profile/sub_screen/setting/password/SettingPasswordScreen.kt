package com.sukacolab.app.ui.feature.user.profile.sub_screen.setting.password

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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sukacolab.app.ui.component.PrimaryButton
import com.sukacolab.app.ui.component.StatelessTopBar
import com.sukacolab.app.ui.component.fields.PasswordField
import com.sukacolab.app.ui.navigation.Screen
import org.koin.androidx.compose.getViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingPasswordScreen(
    navController: NavController,
){
    val viewModel: SettingPasswordViewModel = getViewModel()

    val context = LocalContext.current

    val setPasswordResult by viewModel.setPasswordResult.observeAsState()
    val isLoading = viewModel.isLoading.value

    LaunchedEffect(key1 = setPasswordResult) {
        when (setPasswordResult) {
            is SetPasswordResults.Success -> {
                val message = (setPasswordResult as SetPasswordResults.Success).message
                Log.d("Setting Password", "Sukses: $message")
                Toast.makeText(context, "Success : $message", Toast.LENGTH_SHORT).show()
                navController.navigateUp()
            }
            is SetPasswordResults.Error -> {
                val errorMessage = (setPasswordResult as SetPasswordResults.Error).errorMessage
                Log.d("Setting Password", "Gagal: $errorMessage")
                Toast.makeText(context, "Failed : $errorMessage", Toast.LENGTH_SHORT).show()
                navController.navigate(Screen.SettingPassword.route) {
                    popUpTo(Screen.SettingPassword.route) {
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
                title = "Setting Password",
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
                            PasswordField(
                                modifier = Modifier.padding(bottom = 8.dp),
                                label = "Password Lama",
                                form = viewModel.form,
                                fieldState = viewModel.form.oldPassword
                            ).Field()

                            PasswordField(
                                modifier = Modifier.padding(bottom = 8.dp),
                                label = "Password Baru",
                                form = viewModel.form,
                                fieldState = viewModel.form.password
                            ).Field()

                            PasswordField(
                                modifier = Modifier.padding(bottom = 8.dp),
                                label = "Konfirmasi Password Baru",
                                form = viewModel.form,
                                fieldState = viewModel.form.passwordConfirm,
                                imeAction = ImeAction.Done
                            ).Field()

                            Box(modifier = Modifier.padding(top = 20.dp)){
                                Box(modifier = Modifier.fillMaxWidth().padding(top = 10.dp)){
                                    if (isLoading) {
                                        CircularProgressIndicator(modifier = Modifier.align(
                                            Alignment.Center))
                                    } else {
                                        PrimaryButton(title = "Change Password", onClick = {
                                            viewModel.validate()
                                        })
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}