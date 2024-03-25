package com.sukacolab.app.ui.component.alert

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import com.sukacolab.app.util.Constant.ALERT_HOMESCREEN

@Composable
fun PrimaryAlert(
    openDialog: MutableState<Boolean>,
) {
    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                // Panggil fungsi untuk menutup AlertStateless saat di-dismiss
                openDialog.value = false
            },
            title = {
                Text("Fitur dalam pengembangan")
            },
            text = {
                Text(ALERT_HOMESCREEN)
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        // Panggil fungsi untuk menutup AlertStateless
                        openDialog.value = false
                    }
                ) {
                    Text("Oke")
                }
            }
        )
    }
}
