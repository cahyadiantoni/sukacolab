package com.sukacolab.app.ui.feature.user.profile.subScreen.profile_edit

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sukacolab.app.R
import com.sukacolab.app.ui.component.textfield.EmailTextField
import com.sukacolab.app.ui.component.textfield.NameTextField
import com.sukacolab.app.ui.component.textfield.PasswordTextField
import com.sukacolab.app.ui.component.textfield.RePasswordTextField
import com.sukacolab.app.ui.component.StatelessTopBar
import com.sukacolab.app.ui.feature.register.model.RegisterRequest
import com.sukacolab.app.ui.theme.SukacolabBaseCoreTheme
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileEditScreen(
    navController: NavController
) {
    val viewModel: ProfileEditViewModel = getViewModel()
    val isLoading = viewModel.isLoading.value // Collect the isLoading state as a Compose state
    val responseMessage by viewModel.responseMessage.collectAsState()
    val context = LocalContext.current // Get the current context for showing toast

    var emailState by remember { mutableStateOf("") }
    var passwordState by remember { mutableStateOf("") }
    var confirmPasswordState by remember { mutableStateOf("") }
    var fullNameState by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            ProfileEditTopBar(
                onMenuClick = {
                    navController.navigateUp()
                },
                onActionClick = {
                    val email = emailState
                    val password = passwordState
                    val confirmPassword = confirmPasswordState
                    val fullName = fullNameState

                    val editRequest = RegisterRequest(email, password, confirmPassword, fullName)
                    viewModel.editProfile(editRequest)
                },
                isLoading = isLoading
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    Spacer(modifier = Modifier.padding(8.dp))
                    ProfilePictureEdit()
                    Spacer(modifier = Modifier.padding(8.dp))
                    NameTextField(value = fullNameState, onValueChange = { fullNameState = it })
                    Spacer(modifier = Modifier.padding(8.dp))
                    EmailTextField(value = emailState, onValueChange = { emailState = it })
                    Spacer(modifier = Modifier.padding(8.dp))
                    PasswordTextField(
                        value = passwordState,
                        onValueChange = { passwordState = it },
                        placeHolder = "Password"
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    RePasswordTextField(
                        value = confirmPasswordState,
                        onValueChange = { confirmPasswordState = it })
                    Spacer(modifier = Modifier.padding(8.dp))
                }
            }
        }

        LaunchedEffect(responseMessage) {
            responseMessage?.let {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                viewModel.clearResponseMessage()
            }
        }
    }
}

@Composable
fun ProfilePictureEdit() {
    // TODO 2: Update Profile Picture logic and API integration hasn't been implemented.
    Box(
        modifier = Modifier
            //.width(80.dp)
            .padding(horizontal = 4.dp, vertical = 4.dp),
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://media.wired.com/photos/5ca648a330f00e47fd82ae77/master/w_2560%2Cc_limit/Culture_Matrix_Code_corridor.jpg")
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.img_placeholder),
            alignment = Alignment.Center,
            modifier = Modifier.clip(CircleShape)
        )
        Icon(
            imageVector = Icons.Default.Edit,
            contentDescription = null,
            modifier = Modifier
                .size(24.dp)
                .padding(4.dp)
                .align(Alignment.BottomEnd) // Position the edit icon at the bottom right corner
        )
    }
}

@Composable
fun ProfileEditTopBar(
    onMenuClick: () -> Unit,
    onActionClick: () -> Unit,
    isLoading: Boolean
) {
    rememberUpdatedState(isLoading) // Track the isLoading state

    StatelessTopBar(
        navigationIcon = {
            IconButton(onClick =
            { onMenuClick() }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = ""
                )
            }
        },
        title = "Data Pribadi",
        actionIcon = {
            IconButton(onClick = onActionClick) {
                if (isLoading) {
                    CircularProgressIndicator() // Show CircularProgressIndicator when isLoading is true
                } else {
                    Text(
                        text = "Simpan",
                        style = TextStyle.Default,
                        modifier = Modifier
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun ProfileEditPreview() {
    SukacolabBaseCoreTheme {
        //  ProfileEditScreen()
    }
}