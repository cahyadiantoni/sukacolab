package com.sukacolab.app.ui.feature.user.profile.sub_screen.setting.email

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sukacolab.app.ui.component.PrimaryButton
import com.sukacolab.app.ui.component.StatelessTopBar
import com.sukacolab.app.ui.component.fields.TextField
import com.sukacolab.app.ui.feature.user.profile.ProfileViewModel
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingEmailScreen(
    navController: NavController,
){
    val profileViewModel: ProfileViewModel = getViewModel()
    val viewModel: SettingEmailViewModel = getViewModel()


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
                title = "Setting Email",
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
                                label = "E Mail",
                                form = viewModel.form,
                                fieldState = viewModel.form.email,
                                keyboardType = KeyboardType.Email,
                                formatter = { rawEmail ->
                                    rawEmail ?: profileViewModel.email ?: ""
                                }
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
        PrimaryButton(title = "Change Email", onClick = {nextClicked()})
    }
}