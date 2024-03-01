package com.sukacolab.app.ui.feature.user.profile.sub_screen.profile_edit

import android.util.Log
import android.widget.Toast
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sukacolab.app.R
import com.sukacolab.app.ui.component.PrimaryButton
import com.sukacolab.app.ui.component.textfield.EmailTextField
import com.sukacolab.app.ui.component.textfield.NameTextField
import com.sukacolab.app.ui.component.textfield.PasswordTextField
import com.sukacolab.app.ui.component.textfield.RePasswordTextField
import com.sukacolab.app.ui.component.StatelessTopBar
import com.sukacolab.app.ui.component.fields.CheckboxField
import com.sukacolab.app.ui.component.fields.DateField
import com.sukacolab.app.ui.component.fields.PickerField
import com.sukacolab.app.ui.component.fields.TextField
import com.sukacolab.app.ui.feature.register.model.RegisterRequest
import com.sukacolab.app.ui.feature.user.profile.ProfileViewModel
import com.sukacolab.app.ui.navigation.Screen
import com.sukacolab.app.ui.theme.SukacolabBaseCoreTheme
import com.sukacolab.app.util.form.formatters.dateShort
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileEditScreen(
    navController: NavController,
){
    val viewModel: ProfileEditViewModel = getViewModel()
    val profileViewModel: ProfileViewModel = getViewModel()

    val context = LocalContext.current

    val editProfileResult by viewModel.editProfileResult.observeAsState()
    val isLoading = viewModel.isLoading.value

    LaunchedEffect(key1 = editProfileResult) {
        when (editProfileResult) {
            is ProfileEditResults.Success -> {
                val message = (editProfileResult as ProfileEditResults.Success).message
                Log.d("Edit Profile", "Sukses: $message")
                Toast.makeText(context, "Success : $message", Toast.LENGTH_SHORT).show()
                navController.navigate(Screen.Profile.route) {
                    popUpTo(Screen.Profile.route) {
                        inclusive = true
                    }
                }
            }
            is ProfileEditResults.Error -> {
                val errorMessage = (editProfileResult as ProfileEditResults.Error).errorMessage
                Log.d("Edit Profile", "Gagal: $errorMessage")
                Toast.makeText(context, "Failed : $errorMessage", Toast.LENGTH_SHORT).show()
                navController.navigate(Screen.ProfileEdit.route) {
                    popUpTo(Screen.ProfileEdit.route) {
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
                title = "Edit Profile",
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
                            viewModel.form.name.state.value = profileViewModel.name.toString()
                            TextField(
                                modifier = Modifier.padding(bottom = 8.dp),
                                label = "Nama Lengkap",
                                form = viewModel.form,
                                fieldState = viewModel.form.name,
                                keyboardType = KeyboardType.Text
                            ).Field()

                            if(profileViewModel.summary != null){
                                viewModel.form.summary.state.value = profileViewModel.summary.toString()
                            }
                            TextField(
                                modifier = Modifier.padding(bottom = 8.dp),
                                label = "Motto Profesional",
                                form = viewModel.form,
                                fieldState = viewModel.form.summary,
                                keyboardType = KeyboardType.Text
                            ).Field()

                            if(profileViewModel.linkedin != null) {
                                viewModel.form.linkedin.state.value = profileViewModel.linkedin.toString().substringAfterLast("/")
                            }
                            TextField(
                                modifier = Modifier.padding(bottom = 8.dp),
                                label = "Username Linkedin",
                                form = viewModel.form,
                                fieldState = viewModel.form.linkedin,
                                keyboardType = KeyboardType.Text
                            ).Field()

                            if(profileViewModel.github != null) {
                                viewModel.form.github.state.value = profileViewModel.github.toString().substringAfterLast("/")
                            }
                            TextField(
                                modifier = Modifier.padding(bottom = 8.dp),
                                label = "Username Github",
                                form = viewModel.form,
                                fieldState = viewModel.form.github,
                                keyboardType = KeyboardType.Text
                            ).Field()

                            if(profileViewModel.whatsapp != null){
                                viewModel.form.whatsapp.state.value = profileViewModel.whatsapp.toString().substringAfterLast("/")
                            }
                            TextField(
                                modifier = Modifier.padding(bottom = 8.dp),
                                label = "Nomor Whatsapp",
                                form = viewModel.form,
                                fieldState = viewModel.form.whatsapp,
                                keyboardType = KeyboardType.Number
                            ).Field()

                            if(profileViewModel.instagram != null) {
                                viewModel.form.instagram.state.value = profileViewModel.instagram.toString().substringAfterLast("/")
                            }
                            TextField(
                                modifier = Modifier.padding(bottom = 8.dp),
                                label = "Username Instagram",
                                form = viewModel.form,
                                fieldState = viewModel.form.instagram,
                                imeAction = ImeAction.Done,
                                keyboardType = KeyboardType.Text
                            ).Field()

                            Box(modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp)){
                                if (isLoading) {
                                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                                } else {
                                    PrimaryButton(title = "Edit Profile", onClick = {
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