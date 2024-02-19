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
    id: Int,
    image: String,
    position: String,
    company: String,
    status: Int,
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .fillMaxSize()
            .clickable {

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

//            AsyncImage(
//                model = ImageRequest.Builder(LocalContext.current)
//                    .data(image)
//                    .crossfade(true)
//                    .build(),
//                contentDescription = null,
//                contentScale = ContentScale.Crop,
//                placeholder = painterResource(id = R.drawable.img_logo),
//                modifier = Modifier
//                    .size(60.dp)
//            )
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
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = company,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                )


                val changeColor = if (status == 1) {
                    Color.Gray
                } else if (status == 2){
                    Color.Red
                } else if (status == 3){
                    Color.Green
                } else{
                    Color.Blue
                }

                val textContent = if (status == 1) {
                    "Submitted"
                } else if (status == 2){
                    "Rejected"
                } else if (status == 3){
                    "Accepted"
                } else {
                    "OnSelection"
                }

                Box(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(top = 5.dp)
                        .border(1.dp, changeColor, shape = RoundedCornerShape(20.dp))
                ) {
                    Row(){
                        Text(
                            text = textContent, modifier = Modifier
                                .padding(horizontal = 5.dp),
                            fontSize = 12.sp,
                            color = changeColor
                        )
                    }

                }
            }
        }
    }
}