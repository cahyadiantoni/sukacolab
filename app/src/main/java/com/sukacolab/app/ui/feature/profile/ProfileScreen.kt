package com.sukacolab.app.ui.feature.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sukacolab.app.R
import com.sukacolab.app.ui.navigation.Screen
import com.sukacolab.app.ui.theme.primaryColor

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(){
    Scaffold(
        modifier = Modifier
            .background(primaryColor),
        topBar = {
            SmallTopAppBar(
                title = {
                    Text(text = "Profilmu", color = Color.White)
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = primaryColor),
                actions = {
                    IconButton(onClick = {
                    }) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                    IconButton(onClick = {

                    }) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
            )
        }
    ){
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            item {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(190.dp)
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.background),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp),
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
                        text = "Cahya Diantoni",
                        color = Color.Black,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(start = 20.dp, end = 20.dp)
                    )

                    Text(
                        text = "Software Engineer | Android Application Development | Kotlin | Jetpack Compose",
                        color = Color.DarkGray,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(start = 20.dp, end = 20.dp)
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 35.dp, end = 35.dp, top = 20.dp, bottom = 20.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.linkedin),
                        contentDescription = null,
                        modifier = Modifier.size(40.dp),
                    )

                    Image(
                        painter = painterResource(id = R.drawable.github),
                        contentDescription = null,
                        modifier = Modifier.size(40.dp),
                    )

                    Image(
                        painter = painterResource(id = R.drawable.whatsapp),
                        contentDescription = null,
                        modifier = Modifier.size(40.dp),
                    )

                    Image(
                        painter = painterResource(id = R.drawable.instagram),
                        contentDescription = null,
                        modifier = Modifier.size(40.dp),
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 50.dp, end = 50.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ){
                    Button(
                        onClick = {},
                        modifier = Modifier
                            .weight(0.2f)
                            .border(3.dp, primaryColor, shape = RoundedCornerShape(20.dp)),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White
                        )
                    ) {

                        Text(text = "Show Resume", color = primaryColor)

                    }
                }
                Spacer(modifier = Modifier.size(10.dp))

                borderCompose()

                AboutCompose()
                borderCompose()

                ExperienceCompose()
                borderCompose()

                LicenseCompose()
                borderCompose()

                SkillsCompose()
                borderCompose()

                EducationCompose()
                borderCompose()

                MenuCompose()
                borderCompose()

            }

        }
    }
}

@Preview
@Composable
fun SkillsCompose() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {

        Column(modifier = Modifier.padding(20.dp)) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Column(modifier = Modifier.wrapContentSize()) {

                    Text(
                        text = "Skills", color = Color.Black,
                        fontWeight = FontWeight.Bold,
                    )

                }

                Row(modifier = Modifier.wrapContentSize(),
                    horizontalArrangement = Arrangement.spacedBy(20.dp)) {

                    Icon(
                        imageVector = Icons.Default.Add, contentDescription = null,
                        tint = primaryColor,
                    )
                    Icon(
                        imageVector = Icons.Default.Edit, contentDescription = null,
                        tint = primaryColor,
                    )
                }
            }

            Text(
                text = "Kotlin",
                color = Color.DarkGray,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            ) {

                Image(painter = painterResource(id = R.drawable.img_logo),
                    modifier = Modifier.size(40.dp),
                    contentDescription = null,
                    contentScale = ContentScale.Crop)

                Text(
                    text = "Associate Software Engineer at IBM",
                    color = Color.Black,
                    fontSize = 15.sp,
                    modifier = Modifier.padding(7.dp)
                )
            }

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                color = Color.LightGray
            )

            Text(
                text = "Android",
                color = Color.DarkGray,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            ) {

                Image(painter = painterResource(id = R.drawable.img_logo),
                    modifier = Modifier.size(40.dp),
                    contentDescription = null,
                    contentScale = ContentScale.Crop)

                Text(
                    text = "Associate Software Engineer at IBM",
                    color = Color.Black,
                    fontSize = 15.sp,
                    modifier = Modifier.padding(7.dp)
                )
            }

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                color = Color.LightGray
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp),
                contentAlignment = Alignment.Center
            ) {

                Row(
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    modifier = Modifier.wrapContentSize()
                ) {

                    Text(
                        text = "Show All 29 skills",
                        color = primaryColor,
                        fontWeight = FontWeight.Bold
                    )

                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = null,
                        tint = primaryColor
                    )
                }
            }

        }

    }
}


@Preview
@Composable
fun LicenseCompose() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {

        Column(modifier = Modifier.padding(20.dp)) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Column(modifier = Modifier.wrapContentSize()) {

                    Text(
                        text = "Licenses & certifications", color = Color.Black,
                        fontWeight = FontWeight.Bold,
                    )

                }

                Row(
                    modifier = Modifier.wrapContentSize(),
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {

                    Icon(
                        imageVector = Icons.Default.Add, contentDescription = null,
                        tint = primaryColor
                    )
                    Icon(
                        imageVector = Icons.Default.Edit, contentDescription = null,
                        tint = primaryColor
                    )

                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp)
            ) {

                Image(
                    painter = painterResource(id = R.drawable.background),
                    contentDescription = null,
                    modifier = Modifier.size(60.dp),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.size(10.dp))

                Column(modifier = Modifier.fillMaxWidth()) {

                    Text(
                        text = "Azure Developer Associate",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Microsoft",
                        color = Color.DarkGray,
                    )

                    Text(
                        text = "Issued Jan 2023 - Expired Jan 2024",
                        color = Color.Gray,
                    )

                    Box(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(top = 10.dp)
                            .border(1.dp, Color.DarkGray, shape = RoundedCornerShape(20.dp))
                    ) {

                        Text(
                            text = "Show credential", modifier = Modifier
                                .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp),
                            color = Color.DarkGray
                        )
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp)
            ) {

                Image(
                    painter = painterResource(id = R.drawable.background),
                    contentDescription = null,
                    modifier = Modifier.size(60.dp),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.size(10.dp))

                Column(modifier = Modifier.fillMaxWidth()) {

                    Text(
                        text = "Android Application Development",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Coding Blocks",
                        color = Color.DarkGray,
                    )

                    Box(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(top = 10.dp)
                            .border(1.dp, Color.DarkGray, shape = RoundedCornerShape(20.dp))
                    ) {

                        Text(
                            text = "Show credential", modifier = Modifier
                                .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp),
                            color = Color.DarkGray
                        )
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun EducationCompose() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {

        Column(modifier = Modifier.padding(20.dp)) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Column(modifier = Modifier.wrapContentSize()) {

                    Text(
                        text = "Education", color = Color.Black,
                        fontWeight = FontWeight.Bold,
                    )

                }

                Row(
                    modifier = Modifier.wrapContentSize(),
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {

                    Icon(
                        imageVector = Icons.Default.Add, contentDescription = null,
                        tint = primaryColor
                    )
                    Icon(
                        imageVector = Icons.Default.Edit, contentDescription = null,
                        tint = primaryColor
                    )

                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp)
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
                        text = "Amity University",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Master of Computer Applications - MCA",
                        color = Color.DarkGray,
                    )

                    Text(
                        text = "2019 - 2022",
                        color = Color.DarkGray,
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp)
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
                        text = "Delhi University",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Bachelor of Science, BSc. (CS)",
                        color = Color.DarkGray,
                    )

                    Text(
                        text = "2015 - 2018",
                        color = Color.DarkGray,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ExperienceCompose() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {

        Column(modifier = Modifier.padding(20.dp)) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Column(modifier = Modifier.wrapContentSize()) {

                    Text(
                        text = "Experience", color = Color.Black,
                        fontWeight = FontWeight.Bold,
                    )

                }

                Row(
                    modifier = Modifier.wrapContentSize(),
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {

                    Icon(
                        imageVector = Icons.Default.Add, contentDescription = null,
                        tint = primaryColor
                    )
                    Icon(
                        imageVector = Icons.Default.Edit, contentDescription = null,
                        tint = primaryColor
                    )

                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp)
            ) {

                Image(
                    painter = painterResource(id = R.drawable.background),
                    contentDescription = null,
                    modifier = Modifier.size(60.dp),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.size(10.dp))

                Column(modifier = Modifier.fillMaxWidth()) {

                    Text(
                        text = "Associate System Engineer",
                        color = Color.DarkGray,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "IBM - Full time",
                        color = Color.DarkGray,
                    )

                    Text(
                        text = "February, 2024 - February, 2024",
                        color = Color.Gray,
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp)
            ) {

                Image(
                    painter = painterResource(id = R.drawable.background),
                    contentDescription = null,
                    modifier = Modifier.size(60.dp),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.size(10.dp))

                Column(modifier = Modifier.fillMaxWidth()) {

                    Text(
                        text = "Software Development Executive",
                        color = Color.DarkGray,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Skrolled - Internship",
                        color = Color.DarkGray,
                    )

                    Text(
                        text = "February, 2024 - February, 2024",
                        color = Color.Gray,
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp)
            ) {

                Image(
                    painter = painterResource(id = R.drawable.background),
                    contentDescription = null,
                    modifier = Modifier.size(60.dp),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.size(10.dp))

                Column(modifier = Modifier.fillMaxWidth()) {

                    Text(
                        text = "Software Development Consultant",
                        color = Color.DarkGray,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Prodevans Technologies - Internship",
                        color = Color.DarkGray,
                    )

                    Text(
                        text = "February, 2024 - February, 2024",
                        color = Color.Gray,
                    )
                }
            }
        }
    }
}

@Composable
fun borderCompose() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(2.dp)
            .background(Color.LightGray)
            .padding(top = 30.dp)
    )
}


@Preview
@Composable
fun AboutCompose() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {

        Column(modifier = Modifier.padding(20.dp)) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = "About", color = Color.Black,
                    fontWeight = FontWeight.Bold,
                )

                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = null,
                    tint = primaryColor
                )
            }

            Text(
                text = "I am a passionate and results-driven programmer with a strong focus on creating innovative and user-friendly Android applications.  I am always eager to stay up-to-date with the latest trends in technologies and embrace best practices to ensure the highest quality standards. If you're seeking someone for an Android Developer or Software Engineer role with a proven track record of delivering outstanding applications, I would be thrilled to connect with you.",
                color = Color.DarkGray,
                modifier = Modifier.padding(top = 20.dp)
            )
        }
    }
}

@Preview
@Composable
fun MenuCompose(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 20.dp, bottom = 10.dp, start = 30.dp, end = 30.dp)
            ) {
                Text(
                    text = "Privacy Policy",
                    color = primaryColor,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.weight(1f)
                )

                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = null,
                    tint = primaryColor,
                    modifier = Modifier.size(24.dp)
                )

            }

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp),
                color = Color.Gray
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 10.dp, bottom = 10.dp, start = 30.dp, end = 30.dp)
            ) {
                Text(
                    text = "About App",
                    color = primaryColor,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.weight(1f)
                )

                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = null,
                    tint = primaryColor,
                    modifier = Modifier.size(24.dp)
                )

            }

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp),
                color = Color.Gray
            )

            Row(
                modifier = Modifier
                    .padding(20.dp)
            ) {

                Button(
                    onClick = {},
                    modifier = Modifier.weight(0.4f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red
                    )
                ) {

                    Text(text = "Logout")

                }
            }
        }
    }
}