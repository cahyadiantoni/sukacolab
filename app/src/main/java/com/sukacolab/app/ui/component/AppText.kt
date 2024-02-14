package com.sukacolab.app.ui.component

import android.graphics.fonts.FontFamily
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp

@Composable
fun AppText(
    modifier: Modifier = Modifier,
    text: String,
    size: Int,
    color: Color,
) {
    Text(
        modifier = modifier,
        text = text,
        fontSize = (size).sp,
        color = color,
        textAlign = TextAlign.Center,
    )
}