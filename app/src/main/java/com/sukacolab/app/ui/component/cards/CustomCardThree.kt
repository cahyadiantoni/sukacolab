package com.sukacolab.app.ui.component.cards

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomCardThree(
    title: String,
    desc: String,
    button: String,
    onClick: () -> Unit
) {
    CustomCard(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall.copy(color = Color.Gray)
            )
            Spacer(modifier = Modifier.padding(top = 5.dp))
        },
        desc = desc,
        button = button,
        onClick = onClick
    )
}