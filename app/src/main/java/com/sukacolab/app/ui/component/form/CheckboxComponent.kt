package com.sukacolab.app.ui.component.form

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun CheckboxComponent(
    modifier: Modifier = Modifier,
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit),
    label: String,
    hasError: Boolean = false,
    errorText: MutableList<String>? = null
) {
    Column(modifier = Modifier.padding(top = 8.dp)) {
        val darkTheme: Boolean = isSystemInDarkTheme()
        val textColor = when {
            darkTheme -> Color.White
            else -> Color.Black
        }
        Column(modifier = modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.small)
            .clickable(
                indication = rememberRipple(color = androidx.compose.material3.MaterialTheme.colorScheme.secondary),
                interactionSource = remember { MutableInteractionSource() },
                onClick = { onCheckedChange(!checked) }
            )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Checkbox(
                    checked = checked,
                    onCheckedChange = null,
                    colors = CheckboxDefaults.colors(
                        checkedColor = androidx.compose.material3.MaterialTheme.colorScheme.secondary, // Warna checkbox ketika tercentang
                        uncheckedColor = androidx.compose.material3.MaterialTheme.colorScheme.primary // Warna checkbox ketika tidak tercentang
                    )
                )

                Spacer(Modifier.size(6.dp))

                Text(
                    text = label,
                    color = textColor,
                    style = MaterialTheme.typography.button
                )
            }
        }

        if (hasError && errorText != null) {
            Text(
                text = errorText.joinToString(),
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                style = TextStyle.Default.copy(color = MaterialTheme.colors.error)
            )
        }
    }
}