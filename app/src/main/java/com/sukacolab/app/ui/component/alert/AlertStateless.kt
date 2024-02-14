package com.sukacolab.app.ui.component.alert

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

@Composable
fun AlertStateless(
    openDialog: MutableState<Boolean>,
    title: String,
    desc: String,
    confirmButton: (@Composable () -> Unit)? = null
) {
    AlertDialogCustomCentered(
        openDialog = { openDialog.value = false },
        title = title,
        desc = desc,
        backButton = "Ok",
        confirmButton = confirmButton,
        icon = {
            Icon(
                imageVector = Icons.Filled.Info,
                contentDescription = "Info",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    )
}