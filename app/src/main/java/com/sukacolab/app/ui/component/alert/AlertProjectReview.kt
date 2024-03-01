package com.sukacolab.app.ui.component.alert

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import com.sukacolab.app.util.Constant.ALERT_HOMESCREEN
import com.sukacolab.app.util.Constant.ALERT_PROJECT_REVIEW

@Composable
fun AlertProjectReview(
    openDialog: MutableState<Boolean>,
    ctx: Context,
) {
    AlertStateless(
        openDialog = openDialog,
        confirmButton = {
            TextButton(
                onClick = {
                    val urlIntent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://wa.me/6281919480565")
                    )
                    ctx.startActivity(urlIntent)
                }
            ) {
                Text("Hubungi Admin")
            }
                        },
        title = "Informasi Project",
        desc = ALERT_PROJECT_REVIEW
    )
}