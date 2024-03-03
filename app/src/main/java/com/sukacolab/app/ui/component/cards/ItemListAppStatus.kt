package com.sukacolab.app.ui.component.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sukacolab.app.R
import com.sukacolab.app.ui.navigation.Screen
import com.sukacolab.app.ui.theme.darkGold
import com.sukacolab.app.ui.theme.darkGreen
import com.sukacolab.app.ui.theme.tertiaryColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemListAppStatus(
    navController: NavController,
    id: Int,
    type: String,
    position: String,
    project: String,
    status: Int,
) {
    Card(
        modifier = Modifier
            .padding(bottom = 10.dp)
            .fillMaxSize()
            .clickable {
                navController.navigate(
                    Screen.ProjectDetail.createRoute(
                        id
                    )
                )
            },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            val img = if (type == "Loker") {
                R.drawable.paid
            } else if (type == "Portofolio"){
                R.drawable.portofolio
            } else if (type == "Kompetisi"){
                R.drawable.competition
            } else{
                R.drawable.unknown
            }

            Image(
                painter = painterResource(id = img),
                contentDescription = null,
                modifier = Modifier.size(60.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.size(10.dp))

            Column(modifier = Modifier.fillMaxWidth()) {

                Text(
                    text = position,
                    fontSize = 16.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 16.sp
                )

                Text(
                    text = project,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 14.sp
                )

                val textContent = when (status) {
                    0 -> {
                        "Menunggu"
                    }
                    1 -> {
                        "Diterima"
                    }
                    else -> {
                        "Ditolak"
                    }
                }

                Box(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(top = 5.dp)
                        .border(1.dp, MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(20.dp))
                ) {
                    Row(){
                        Text(
                            text = textContent, modifier = Modifier
                                .padding(horizontal = 5.dp),
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                }
            }
        }
    }
}