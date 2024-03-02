package com.sukacolab.app.ui.feature.user.ur_project.ur_project_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material.icons.filled.BuildCircle
import androidx.compose.material.icons.filled.CurrencyExchange
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.LockClock
import androidx.compose.material.icons.filled.Paid
import androidx.compose.material.icons.filled.Preview
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.SizeMode
import com.sukacolab.app.R
import com.sukacolab.app.ui.component.StatelessTopBar
import com.sukacolab.app.ui.component.cards.ItemListProfile
import com.sukacolab.app.ui.navigation.Screen
import com.sukacolab.app.ui.theme.tertiaryColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UrProjectDetailScreen(
    navController: NavController,
    idProject: String,
){
    var openPage = remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier,
        topBar = {
            StatelessTopBar(
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp()
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "back",
                            tint = Color.White
                        )
                    }
                },
                title = "Your Project Detail",
                actionIcon = {
                    IconButton(onClick = {
                        navController.navigate(
                            Screen.ProjectDetail.createRoute(
                                1
                            )
                        )
                    }) {
                        Icon(imageVector = Icons.Default.Preview, contentDescription = "", tint = Color.White)
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

                        Image(
                            painter = painterResource(id = R.drawable.background),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(95.dp),
                            contentScale = ContentScale.Crop
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
                                .height(80.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            Icon(
                                imageVector = Icons.Default.LocationOn,
                                contentDescription = "",
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(36.dp)
                            )

                            Text(
                                text = "Remote",
                                color = Color.DarkGray,
                                fontSize = 12.sp,
                                textAlign = TextAlign.Center,
                                lineHeight = 12.sp
                            )
                        }

                        Column(
                            modifier = Modifier
                                .width(60.dp)
                                .height(80.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            Icon(
                                imageVector = Icons.Default.BuildCircle,
                                contentDescription = "",
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(36.dp)
                            )

                            Text(
                                text = "Loker",
                                color = Color.DarkGray,
                                fontSize = 12.sp,
                                textAlign = TextAlign.Center,
                                lineHeight = 12.sp
                            )
                        }

                        Column(
                            modifier = Modifier
                                .width(60.dp)
                                .height(80.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            Icon(
                                imageVector = Icons.Default.Paid,
                                contentDescription = "",
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(36.dp)
                            )

                            Text(
                                text = "Rp. 6jt/blnasdasd",
                                color = Color.DarkGray,
                                fontSize = 12.sp,
                                textAlign = TextAlign.Center,
                                lineHeight = 12.sp
                            )
                        }

                        Column(
                            modifier = Modifier
                                .width(60.dp)
                                .height(80.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            Icon(
                                imageVector = Icons.Default.Timer,
                                contentDescription = "",
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(36.dp)
                            )

                            Text(
                                text = "Part Time",
                                color = Color.DarkGray,
                                fontSize = 12.sp,
                                textAlign = TextAlign.Center,
                                lineHeight = 12.sp
                            )
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ){
                        val colorButton1 = if(openPage.value){MaterialTheme.colorScheme.tertiary}else{MaterialTheme.colorScheme.primary}
                        val colorText1 = if(openPage.value){MaterialTheme.colorScheme.primary}else{Color.White}
                        Button(
                            onClick = { openPage.value = false },
                            modifier = Modifier
                                .weight(0.2f),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colorButton1
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ) {

                            Text(text = "Selection", color = colorText1)

                        }

                        val colorButton2 = if(openPage.value){MaterialTheme.colorScheme.primary}else{MaterialTheme.colorScheme.tertiary}
                        val colorText2 = if(openPage.value){Color.White}else{MaterialTheme.colorScheme.primary}
                        Button(
                            onClick = { openPage.value = true },
                            modifier = Modifier
                                .weight(0.2f),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colorButton2
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ) {

                            Text(text = "Accepted", color = colorText2)

                        }
                    }
                }
                item {
                    if(openPage.value){
                        val itemSize: Dp = (LocalConfiguration.current.screenWidthDp.dp)
                        com.google.accompanist.flowlayout.FlowRow(
                            mainAxisSize = SizeMode.Expand,
                            mainAxisAlignment = FlowMainAxisAlignment.SpaceBetween,
                            modifier = Modifier
                                .padding(horizontal = 4.dp),
                        ) {
                            Box(
                                modifier = Modifier
                                    .width(itemSize),
                                contentAlignment = Alignment.Center
                            ) {
                                ItemListProfile(
                                    id = 123,
                                    image = "Kanan",
                                    name = "Cahya Kanan"
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .width(itemSize),
                                contentAlignment = Alignment.Center
                            ) {
                                ItemListProfile(
                                    id = 123,
                                    image = "Test",
                                    name = "Muhammad Diantoni"
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .width(itemSize),
                                contentAlignment = Alignment.Center
                            ) {
                                ItemListProfile(
                                    id = 123,
                                    image = "Test",
                                    name = "Cahya Anthan"
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .width(itemSize),
                                contentAlignment = Alignment.Center
                            ) {
                                ItemListProfile(
                                    id = 123,
                                    image = "Test",
                                    name = "Cahya Anthan"
                                )
                            }
                        }
                    }else{
                        val itemSize: Dp = (LocalConfiguration.current.screenWidthDp.dp)
                        com.google.accompanist.flowlayout.FlowRow(
                            mainAxisSize = SizeMode.Expand,
                            mainAxisAlignment = FlowMainAxisAlignment.SpaceBetween,
                            modifier = Modifier
                                .padding(horizontal = 4.dp),
                        ) {
                            Box(
                                modifier = Modifier
                                    .width(itemSize),
                                contentAlignment = Alignment.Center
                            ) {
                                ItemListProfile(
                                    id = 123,
                                    image = "Kiri",
                                    name = "Cahya Kiri"
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .width(itemSize),
                                contentAlignment = Alignment.Center
                            ) {
                                ItemListProfile(
                                    id = 123,
                                    image = "Test",
                                    name = "Muhammad Diantoni"
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .width(itemSize),
                                contentAlignment = Alignment.Center
                            ) {
                                ItemListProfile(
                                    id = 123,
                                    image = "Test",
                                    name = "Cahya Anthan"
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .width(itemSize),
                                contentAlignment = Alignment.Center
                            ) {
                                ItemListProfile(
                                    id = 123,
                                    image = "Test",
                                    name = "Cahya Anthan"
                                )
                            }
                        }
                    }
                    Box(modifier = Modifier.size(100.dp))
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart),
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = tertiaryColor)
                        .padding(vertical = 15.dp, horizontal = 30.dp)
                ) {
                    Button(
                        onClick = {},
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Red
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(text = "Close the Project", color = Color.White)
                    }
                }
            }
        }
    }
}
