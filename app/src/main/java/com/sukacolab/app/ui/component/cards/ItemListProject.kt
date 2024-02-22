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
            .padding(horizontal = 16.dp, vertical = 4.dp)
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


//                val changeColor = if (type == 1) {
//                    darkGold
//                } else if (type == 2){
//                    darkGreen
//                } else if (type == 3){
//                    Color.Blue
//                } else{
//                    Color.Gray
//                }
//
//                val textContent = if (type == 1) {
//                    "Loker"
//                } else if (type == 2){
//                    "Kompetisi"
//                } else if (type == 3){
//                    "Portofolio"
//                } else {
//                    "Lainnya"
//                }
//
//                Box(
//                    modifier = Modifier
//                        .wrapContentSize()
//                        .padding(top = 5.dp)
//                        .border(1.dp, changeColor, shape = RoundedCornerShape(20.dp))
//                ) {
//
//                    Text(
//                        text = textContent, modifier = Modifier
//                            .padding(horizontal = 5.dp),
//                        fontSize = 12.sp,
//                        color = changeColor
//                    )
//                }
            }
        }
    }
}