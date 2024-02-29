package com.sukacolab.app.ui.component.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sukacolab.app.R
import com.sukacolab.app.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemListProject(
    navController: NavController,
    id: Int,
    image: String,
    position: String,
    company: String,
    date: String,
    type: String,
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
                } else if (type == "Lomba"){
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
                    fontWeight = FontWeight.Bold,
                    lineHeight = 16.sp
                )

                Text(
                    text = company,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 14.sp
                )

                Text(
                    text = date,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light
                )

            }
        }
    }
}