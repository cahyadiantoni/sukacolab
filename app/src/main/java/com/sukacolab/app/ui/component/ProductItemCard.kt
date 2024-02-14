package com.sukacolab.app.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sukacolab.app.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductItemCard(
    name: String,
    image: String,
    brand: String,
    itemSize: Dp,
    onClick: () -> Unit
) {
    Box(
        modifier = androidx.compose.ui.Modifier
            .width(itemSize),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 10.dp)
                .fillMaxSize()
                .clickable {
                    onClick()
                }
        ) {
            Column() {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(170.dp)
                        .padding(horizontal = 8.dp, vertical = 8.dp),
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(image)
                            .crossfade(true)
                            .build(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        placeholder = painterResource(id = R.drawable.img_placeholder),
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                Spacer(modifier = Modifier.padding(top = 8.dp))
                Text(text = brand, style = MaterialTheme.typography.titleMedium.copy(color = Color.Gray), modifier = Modifier.padding(horizontal = 8.dp))
                Spacer(modifier = Modifier.padding(top = 12.dp))
            }
        }
    }
}