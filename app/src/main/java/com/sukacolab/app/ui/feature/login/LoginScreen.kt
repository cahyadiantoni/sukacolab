package com.sukacolab.app.ui.feature.login

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.*
import com.sukacolab.app.R
import com.sukacolab.app.ui.component.PrimaryButton
import com.sukacolab.app.ui.component.textfield.EmailTextField
import com.sukacolab.app.ui.component.textfield.PasswordTextField
import com.sukacolab.app.ui.navigation.Screen
import org.koin.androidx.compose.getViewModel

@NavDestinationDsl
@Composable
fun LoginScreen(
   navController: NavController,
) {
    LoginScreenContent(
       navController = navController,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreenContent(
    viewModel: LoginViewModel = getViewModel(),
   navController: NavController,
) {
    val loginResult by viewModel.loginResult.observeAsState()
    val emailState = viewModel.emailState.value
    val passwordState = viewModel.passwordState.value
    val loginState = viewModel.loginState.value
    val scaffoldState = rememberScaffoldState()
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    val isUserLoggedIn by viewModel.isUserLoggedIn.collectAsState()
    val isLoading = viewModel.isLoading.value // Collect the isLoading state as a Compose state
    val (snackbarVisibleState, setSnackBarState) = remember { mutableStateOf(false) }

    val role = "mobile-app"


   LaunchedEffect(key1 = loginResult) {

       when (loginResult) {
           is LoginApiResults.Success -> {
               val userId = (loginResult as LoginApiResults.Success).userId
               val token = (loginResult as LoginApiResults.Success).token
               // Handle successful login
               Log.d("LOGIN TAG SUCCESS", "LoginScreenContent: $userId, $token")
               navController.navigate(Screen.Home.route) {
                   popUpTo(Screen.Login.route) {
                       inclusive = true
                   }
               }
           }
           is LoginApiResults.Error -> {
               val errorMessage = (loginResult as LoginApiResults.Error).errorMessage
               // Handle login error
               Log.e("LOGIN TAG ERROR", "LoginScreenContent: $errorMessage", )
               setSnackBarState(!snackbarVisibleState)
           }
           else -> {
               // Initial state or loading state
           }
       }
   }

    Scaffold {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(horizontal = 22.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_logo),
                contentDescription = null,
                modifier = Modifier
                    .width(400.dp)
                    .height(100.dp)
            )
            Spacer(modifier = Modifier.padding(16.dp))
            Text(text = "Welcome to Sukacolab", style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold
            ))
            Spacer(modifier = Modifier.padding(6.dp))
            Text(text = "Sign In to Continue", style = MaterialTheme.typography.bodyLarge.copy(color = Color.Gray))
            Spacer(modifier = Modifier.padding(32.dp))
            EmailTextField(value = emailState.text, onValueChange = { viewModel.setEmail(it) })
            Spacer(modifier = Modifier.padding(top = 12.dp))
            PasswordTextField(
                value = passwordState.text,
                onValueChange = { viewModel.setPassword(it) },
                placeHolder = "Password"
            )
            Spacer(modifier = Modifier.padding(60.dp))
            val context = LocalContext.current
            Box() {
                if (isLoading) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.Center)
                    ) {
                        CircularProgressIndicator() // Show the loading indicator in the center
                    }
                } else {
                    PrimaryButton(title = "Sign In", onClick = {
                        viewModel.loginNew(emailState.text, passwordState.text, role)

                    })
                }
            }
            Spacer(modifier = Modifier.padding(24.dp))
            LoginCheck(
               navController = navController,
            )
            if (snackbarVisibleState) {
                Snackbar(
                    modifier = Modifier.padding(8.dp),
                    contentColor = Color.White,
                    containerColor = Color.Red,
                ) { Text(text = "Login Failed, please try again with correct email and password!", style = TextStyle(
                    color = Color.White,
                )) }
            }
        }
    }
}

@Composable
fun SnackBarMessage(
    message: String,
) {
    Snackbar {
        Text(text = message, color = Color.White)
    }
}


@Composable
fun LoginCheck(
   navController: NavController,
) {
   Column(
         modifier = Modifier.fillMaxWidth(),
         horizontalAlignment = Alignment.CenterHorizontally,
         verticalArrangement = Arrangement.Center
   ) {
       Text(
           text = "Forgot password?",
           color = MaterialTheme.colorScheme.primary,
           fontWeight = FontWeight.SemiBold,
           modifier = Modifier.clickable {  }
       )
       Spacer(modifier = Modifier.padding(top = 10.dp))
       Row(
           modifier = Modifier.fillMaxWidth(),
           verticalAlignment = Alignment.CenterVertically,
           horizontalArrangement = Arrangement.Center
       ) {
           Text(text = "Don't have an account? ", color = Color.Gray)
           Text(
               text = "Register",
               color = MaterialTheme.colorScheme.primary,
               fontWeight = FontWeight.SemiBold,
               modifier = Modifier.clickable {
                   navController.navigate(Screen.Register.route)
               }
           )
       }
   }
}

@Preview(showBackground = true)
@Composable
fun LoginPrev() {
}