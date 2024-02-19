package com.sukacolab.app.ui.feature.projectDetail

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.CurrencyExchange
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.LockClock
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sukacolab.app.R
import com.sukacolab.app.ui.component.StatelessTopBar
import com.sukacolab.app.ui.theme.primaryColor
import com.sukacolab.app.ui.theme.tertiaryColor

@Composable
fun ProjectDetailScreen(){
    ProjectDetailContent()
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectDetailContent(

){
    Scaffold(
        modifier = Modifier,
        topBar = {
            StatelessTopBar(
                navigationIcon = {
                    IconButton(onClick = {

                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "back",
                            tint = Color.White
                        )
                    }
                },
                title = "Project Detail",
                actionIcon = {
                    IconButton(onClick = {
                    }) {
                        Icon(imageVector = Icons.Default.BookmarkBorder, contentDescription = "", tint = Color.White)
                    }
                }
            )
        }
    ){
        Box(modifier = Modifier.fillMaxSize()){
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                item {

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(160.dp)
                    ) {

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(95.dp)
                                .background(MaterialTheme.colorScheme.primary),
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.BottomStart)
                        ) {

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                            }

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ){
                                Box(
                                    modifier = Modifier
                                        .size(150.dp)
                                        .padding(10.dp)
                                        .clip(CircleShape)
                                        .background(Color.White)
                                ) {

                                    Image(
                                        painter = painterResource(id = R.drawable.img_logo),
                                        contentDescription = null,
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .border(2.dp, Color.LightGray, shape = CircleShape)
                                            .padding(5.dp)
                                            .clip(CircleShape)
                                    )
                                }
                            }
                        }
                    }

                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = "Android Developer",
                            color = Color.Black,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(start = 20.dp, end = 20.dp)
                        )

                        Text(
                            text = "PT. Sukacode Solusi Teknologi",
                            color = Color.DarkGray,
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(top= 8.dp, start = 20.dp, end = 20.dp)
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp, bottom = 8.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Column(
                            modifier = Modifier
                                .width(60.dp)
                                .height(70.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            Icon(
                                imageVector = Icons.Default.LocationOn,
                                contentDescription = "",
                                tint = primaryColor,
                                modifier = Modifier.size(36.dp)
                            )

                            Text(
                                text = "Remote",
                                color = Color.DarkGray,
                                fontSize = 12.sp,
                                textAlign = TextAlign.Center,
                            )
                        }

                        Column(
                            modifier = Modifier
                                .width(60.dp)
                                .height(70.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            Icon(
                                imageVector = Icons.Default.Work,
                                contentDescription = "",
                                tint = primaryColor,
                                modifier = Modifier.size(36.dp)
                            )

                            Text(
                                text = "Loker",
                                color = Color.DarkGray,
                                fontSize = 12.sp,
                                textAlign = TextAlign.Center,
                            )
                        }

                        Column(
                            modifier = Modifier
                                .width(60.dp)
                                .height(70.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            Icon(
                                imageVector = Icons.Default.CurrencyExchange,
                                contentDescription = "",
                                tint = primaryColor,
                                modifier = Modifier.size(36.dp)
                            )

                            Text(
                                text = "Rp. 6jt/bln",
                                color = Color.DarkGray,
                                fontSize = 12.sp,
                                textAlign = TextAlign.Center,
                            )
                        }

                        Column(
                            modifier = Modifier
                                .width(60.dp)
                                .height(70.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            Icon(
                                imageVector = Icons.Default.LockClock,
                                contentDescription = "",
                                tint = primaryColor,
                                modifier = Modifier.size(36.dp)
                            )

                            Text(
                                text = "Part Time",
                                color = Color.DarkGray,
                                fontSize = 12.sp,
                                textAlign = TextAlign.Center,
                            )
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ){
                        Button(
                            onClick = {},
                            modifier = Modifier
                                .weight(0.2f),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = primaryColor
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ) {

                            Text(text = "About", color = Color.White)

                        }

                        Button(
                            onClick = {},
                            modifier = Modifier
                                .weight(0.2f),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = tertiaryColor
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ) {

                            Text(text = "Requirements", color = primaryColor)

                        }
                    }

                    Row(
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 5.dp).fillMaxWidth(),
                    ){
                        Text(
                            text = "Author : ",
                            color = Color.DarkGray,
                            textAlign = TextAlign.Justify,
                            fontWeight = FontWeight.Normal,
                        )

                        Box(
                            modifier = Modifier
                                .size(20.dp)
                                .clip(CircleShape)
                                .background(Color.White)

                        ) {

                            Image(
                                painter = painterResource(id = R.drawable.img_logo),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .clip(CircleShape)
                            )
                        }

                        Text(
                            modifier = Modifier
                                .padding(start = 4.dp),
                            text = "Cahya Diantoni",
                            color = Color.DarkGray,
                            textAlign = TextAlign.Justify,
                            fontWeight = FontWeight.SemiBold,
                        )
                    }

                    Text(
                        text = "I am a passionate and results-driven programmer with a strong focus on creating innovative and user-friendly Android applications.  I am always eager to stay up-to-date with the latest trends in technologies and embrace best practices to ensure the highest quality standards. If you're seeking someone for an Android Developer or Software Engineer role with a proven track record of delivering outstanding applications, I would be thrilled to connect with you.",
                        color = Color.DarkGray,
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 5.dp).fillMaxWidth(),
                        textAlign = TextAlign.Justify,
                        fontWeight = FontWeight.Normal,
                    )
                }
            }
            Spacer(modifier = Modifier.padding(100.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp, horizontal = 30.dp)
                    .align(Alignment.BottomStart),
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                Button(
                    onClick = {},
                    modifier = Modifier
                        .weight(0.2f)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = primaryColor
                    ),
                    shape = RoundedCornerShape(8.dp),
                ) {

                    Text(text = "Join Project", color = Color.White)

                }
            }
        }
    }
}

@Preview
@Composable
fun prevProjectDetail(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        ProjectDetailScreen()
    }
}