package com.sukacolab.app.ui.component.textfield

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    icon: (@Composable () -> Unit)? = null,
    placeHolder: String,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    isError: Boolean? = false,
    trailingIcon: (@Composable () -> Unit)? = null,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        leadingIcon = icon,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = Color.LightGray
        ),
        placeholder = { Text(placeHolder) },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 48.dp),
        shape = RoundedCornerShape(10.dp),
        visualTransformation = visualTransformation,
        isError = isError?: false,
        trailingIcon = trailingIcon
    )
}

@Composable
fun NameTextField(
    value: String,
    onValueChange: (String) -> Unit
) {
    CustomTextField(value = value, onValueChange = onValueChange, placeHolder = "Name")
}

@Composable
fun EmailTextField(
    value: String,
    onValueChange: (String) -> Unit
) {
    CustomTextField(
        value = value,
        onValueChange = onValueChange,
        placeHolder = "Email",
        icon = {
            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = null,
            )
        }
    )
}