package com.sukacolab.app.ui.component.alert

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

@Composable
fun AlertLogout(
    openDialog : MutableState<Boolean>,
    onClick: () -> Unit
) {
    AlertDialogCustom(
        openDialog = { openDialog.value = false },
        title = "Logout",
        desc = "Apakah kamu yakin bahwa kamu ingin logout?",
        backButton = "Tidak",
        confirmButton = {
            TextButton(
                onClick = onClick
            ) {
                Text("Logout")
            }
        }
    )
}