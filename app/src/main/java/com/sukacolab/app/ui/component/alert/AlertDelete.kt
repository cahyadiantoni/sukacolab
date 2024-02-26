package com.sukacolab.app.ui.component.alert

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

@Composable
fun AlertDelete(
    openDialog : MutableState<Boolean>,
    onClick: () -> Unit
) {
    AlertDialogCustom(
        openDialog = { openDialog.value = false },
        title = "Delete",
        desc = "Apakah kamu yakin ingin menghapus data ini?",
        backButton = "Batal",
        confirmButton = {
            TextButton(
                onClick = onClick
            ) {
                Text("Delete")
            }
        }
    )
}