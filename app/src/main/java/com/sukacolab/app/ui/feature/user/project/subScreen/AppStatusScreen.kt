package com.sukacolab.app.ui.feature.user.project.subScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.sukacolab.app.ui.component.cards.ItemListAppStatus


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppStatusScreen(){
    LazyColumn(
        state = LazyListState(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            ItemListAppStatus(
                id = 123,
                image = "Test",
                position = "UI/UX Designer",
                company = "Universitas Singaperbangsa Karawang",
                status = 3
            )
        }
    }
}