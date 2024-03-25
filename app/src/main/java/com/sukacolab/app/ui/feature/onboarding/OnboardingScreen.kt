package com.sukacolab.app.ui.feature.onboarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.sukacolab.app.R
import com.sukacolab.app.ui.component.AppText
import com.sukacolab.app.ui.component.LottieAnim
import com.sukacolab.app.ui.feature.splash.SplashViewModel
import com.sukacolab.app.ui.navigation.Screen
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreen(
    navController: NavController,
) {
    val viewModel: SplashViewModel = getViewModel()
    val pagerState = rememberPagerState(0)
    val anim = listOf(
        R.raw.one,
        R.raw.two,
        R.raw.three,
        R.raw.four,
    )
    val map = mapOf(
        "Realisasikan Ide Proyek IT Anda!" to "Memudahkan mahasiswa Fasilkom Unsika untuk mengimplementasikan ide proyek IT mereka.",
        "Tingkatkan Portofolio IT Sebelum Lulus!" to "Aplikasi yang membantu mahasiswa Fasilkom Unsika membangun portofolio yang kuat di bidang IT sebelum lulus kuliah.",
        "Wujudkan Keahlian Anda dalam Dunia Freelance IT!" to "Platform khusus untuk mahasiswa Fasilkom Unsika yang ingin bekerja freelance di bidang IT.",
        "Temukan dan Bentuk Tim untuk Proyek IT!" to "Aplikasi yang memudahkan mahasiswa Fasilkom Unsika menemukan dan membentuk tim untuk mengerjakan proyek IT bersama-sama."

    ).toList()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.tertiary),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = CenterHorizontally
    ) {
        HorizontalPager(
            count = 4,
            state = pagerState,
            contentPadding = PaddingValues(5.dp),
            modifier = Modifier
                .fillMaxSize()
                .weight(5f)
        ) { index ->
            Pager(title = map[index].first, desc = map[index].second, anim = anim[index])
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .height(50.dp),
            contentAlignment = Center
        ) {
            this@Column.AnimatedVisibility(
                visible = pagerState.currentPage == 3,
                exit = fadeOut(),
                enter = fadeIn()
            ) {
                ElevatedButton(
                    onClick = {
                         viewModel.saveOnboardingStatus(true)
                         navController.navigate(Screen.Login.route) {
                                popUpTo(Screen.Onboarding.route) { inclusive = true }
                        }
                    },
                    modifier = Modifier.size(height = 50.dp, width = 200.dp),
                    colors = ButtonDefaults.elevatedButtonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                    )
                ) {
                    AppText(
                        text = "Mulai Sekarang",
                        size = 18,
                        color = Color.White,
                    )
                }
            }
            if (pagerState.currentPage != 3) {
                HorizontalPagerIndicator(
                    pagerState = pagerState,
                    activeColor = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}

@Composable
fun Pager(
    title: String,
    desc: String,
    anim: Int
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LottieAnim(anim = anim, modifier = Modifier.height(300.dp))
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            text = title,
            fontSize = 28.sp,
            color = MaterialTheme.colorScheme.secondary,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            lineHeight = 40.sp
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            text = desc,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.secondary,
            textAlign = TextAlign.Center
        )
    }
}