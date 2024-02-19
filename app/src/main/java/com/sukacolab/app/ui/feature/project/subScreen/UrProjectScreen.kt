package com.sukacolab.app.ui.feature.project.subScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.SizeMode
import com.sukacolab.app.ui.component.cards.ItemListAppStatus
import com.sukacolab.app.ui.component.cards.ItemListUrProject


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UrProjectScreen(){
    LazyColumn(
        state = LazyListState(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            val itemSize: Dp = (LocalConfiguration.current.screenWidthDp.dp)
            com.google.accompanist.flowlayout.FlowRow(
                mainAxisSize = SizeMode.Expand,
                mainAxisAlignment = FlowMainAxisAlignment.SpaceBetween,
                modifier = Modifier
                    .padding(top = 4.dp),
            ) {
                Box(
                    modifier = androidx.compose.ui.Modifier
                        .width(itemSize),
                    contentAlignment = Alignment.Center
                ) {
                    ItemListUrProject(
                        id = 123,
                        image = "Test",
                        position = "UI/UX Designer",
                        company = "Universitas Singaperbangsa Karawang",
                        date = "16 Februari 2024",
                        type = 0
                    )
                }

                Box(
                    modifier = androidx.compose.ui.Modifier
                        .width(itemSize),
                    contentAlignment = Alignment.Center
                ) {
                    ItemListUrProject(
                        id = 123,
                        image = "Test",
                        position = "Android Developer",
                        company = "PT. Sukacode Solusi Teknologi",
                        date = "14 Februari 2024",
                        type = 1
                    )
                }

                Box(
                    modifier = androidx.compose.ui.Modifier
                        .width(itemSize),
                    contentAlignment = Alignment.Center
                ) {
                    ItemListUrProject(
                        id = 123,
                        image = "Test",
                        position = "Web Developer",
                        company = "CV. Karib Solutions",
                        date = "2 Februari 2024",
                        type = 2
                    )
                }

                Box(
                    modifier = androidx.compose.ui.Modifier
                        .width(itemSize),
                    contentAlignment = Alignment.Center
                ) {
                    ItemListUrProject(
                        id = 123,
                        image = "Test",
                        position = "Project Manager",
                        company = "PT. Maju Mundur",
                        date = "2 Januari 2024",
                        type = 0
                    )
                }
            }
        }
    }
}