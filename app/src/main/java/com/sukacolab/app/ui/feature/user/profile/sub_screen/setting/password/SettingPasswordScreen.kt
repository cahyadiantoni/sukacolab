package com.sukacolab.app.ui.feature.user.profile.sub_screen.setting.password

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sukacolab.app.ui.component.PrimaryButton
import com.sukacolab.app.ui.component.StatelessTopBar
import com.sukacolab.app.ui.component.fields.PasswordField
import com.sukacolab.app.ui.component.fields.TextField
import com.sukacolab.app.ui.feature.user.profile.ProfileViewModel
import org.koin.androidx.compose.getViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingPasswordScreen(
    navController: NavController,
){
    val profileViewModel: ProfileViewModel = getViewModel()
    val viewModel: SettingPasswordViewModel = getViewModel()


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
                                fieldState = viewModel.form.passwordConfirm
                            ).Field()

                            Box(modifier = Modifier.padding(top = 20.dp)){
                                ButtonRow(nextClicked = {

                                })
                            }
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun ButtonRow(nextClicked: () -> Unit) {
    Row {
        PrimaryButton(title = "Change Password", onClick = {nextClicked()})
    }
}