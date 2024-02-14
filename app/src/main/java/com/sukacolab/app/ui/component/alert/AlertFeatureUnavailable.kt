package com.sukacolab.app.ui.component.alert

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

@Composable
fun AlertFeatureUnavailable(
    openDialog: MutableState<Boolean>
) {
    AlertStateless(
        openDialog = openDialog,
        title = "Fitur dalam pengembangan",
        desc = "Mohon maaf untuk saat ini fitur masih dalam tahap pengembangan."
    )
}