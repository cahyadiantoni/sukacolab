package com.sukacolab.app.ui.component.alert

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import com.sukacolab.app.util.Constant.ALERT_HOMESCREEN

@Composable
fun PrimaryAlert(
    openDialog: MutableState<Boolean>,
    ctx: Context,
    url: String,
) {
    AlertStateless(
        openDialog = openDialog,
        confirmButton = {
            TextButton(
                onClick = {
                    val urlIntent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(url)
                    )
                    ctx.startActivity(urlIntent)
                }
            ) {
                Text("Coba")
            }
                        },
        title = "Fitur dalam pengembangan",
        desc = ALERT_HOMESCREEN
    )
}