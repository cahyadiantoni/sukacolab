package com.sukacolab.app.ui.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun StatelessTopBar(
    navigationIcon: (@Composable () -> Unit)?=null,
    title: String,
    actionIcon: @Composable RowScope.() -> Unit
) {
    SmallTopAppBar(
        navigationIcon = { navigationIcon?.invoke() },
        title = { Text(text = title) },
        actions = actionIcon,
    )
}