package com.sukacolab.app.ui.component.textfield

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.sukacolab.app.R

@Composable
fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeHolder: String
) {
    var showError by remember { mutableStateOf(false) }
    var errorText by remember { mutableStateOf("") }
    val isPasswordValid = value.length >= 8 // Minimum password length requirement
    var passwordVisible by remember { mutableStateOf(false) }

    if (!isPasswordValid && value.isNotEmpty()) {
        showError = true
        errorText = "Password must be at least 8 characters long"
    } else {
        showError = false
        errorText = ""
    }

    CustomTextField(
        value = value,
        onValueChange = onValueChange,
        placeHolder = placeHolder,
        icon = {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = null,
            )
        },
        trailingIcon = {
            IconButton(
                onClick = { passwordVisible = !passwordVisible }
            ) {
                val visibilityIcon = if (passwordVisible) {
                    painterResource(R.drawable.visibility_on)
                } else {
                    painterResource(R.drawable.visibility_off)
                }
                Icon(
                    painter = visibilityIcon,
                    contentDescription = "Toggle Password Visibility",
                    modifier = Modifier.size(24.dp)
                )
            }
        },
        isError = showError,
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation()
    )

    if (showError) {
        Text(
            text = "Password harus memiliki panjang diatas 8 karakter.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.error,
            modifier = Modifier.padding(start = 16.dp, top = 4.dp)
        )
    }
}

@Composable
fun RePasswordTextField(
    password: String,
    rePassword: String,
    onRePasswordChange: (String) -> Unit
) {
    var showError by remember { mutableStateOf(false) }
    var errorText by remember { mutableStateOf("") }

    val isPasswordMatching = password == rePassword

    if (!isPasswordMatching && rePassword.isNotEmpty()) {
        showError = true
        errorText = "Password dan re-password harus sama"
    } else {
        showError = false
        errorText = ""
    }

    PasswordTextField(
        value = rePassword,
        onValueChange = onRePasswordChange,
        placeHolder = "Tulis kembali password anda."
    )

    if (showError) {
        Text(
            text = errorText,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.error,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}
