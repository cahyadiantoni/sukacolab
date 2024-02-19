package com.sukacolab.app.ui.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun StatelessTopBar(
    navigationIcon: (@Composable () -> Unit)?=null,
    title: String,
    actionIcon: @Composable RowScope.() -> Unit
) {
    CenterAlignedTopAppBar(
        navigationIcon = { navigationIcon?.invoke() },
        title = { Text(text = title, color = Color.White) },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
        actions = actionIcon,
    )
}