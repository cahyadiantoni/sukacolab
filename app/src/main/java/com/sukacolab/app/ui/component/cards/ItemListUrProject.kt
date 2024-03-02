package com.sukacolab.app.ui.component.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sukacolab.app.R
import com.sukacolab.app.ui.navigation.Screen
import com.sukacolab.app.util.convertToDayMonthYear

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemListUrProject(
    navController: NavController,
    id: Int,
    position: String,
    project: String,
    date: String,
    isActive: Int,
    ) {
    Card(
        modifier = Modifier
            .padding(bottom = 10.dp)
            .fillMaxSize()
            .clickable {
                navController.navigate(
                    Screen.UrProjectDetail.createRoute(
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
            Image(
                painter = painterResource(id = R.drawable.img_logo),
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
                    text = project,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 14.sp
                )

                Text(
                    text = date.convertToDayMonthYear(),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light,
                    lineHeight = 14.sp
                )

                val textContent = if (isActive == 0) {
                    "Review Admin"
                } else if (isActive == 2){
                    "Tidak Aktif"
                } else if (isActive == 1){
                    "Aktif"
                } else {
                    "Ditolak Admin"
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