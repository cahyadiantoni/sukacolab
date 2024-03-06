package com.sukacolab.app.ui.component.alert

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sukacolab.app.util.Constant.ABOUT_APP
import com.sukacolab.app.util.Constant.ABOUT_ONE
import com.sukacolab.app.util.Constant.ABOUT_TWO

@Composable
fun AlertAbout(
    openDialog: MutableState<Boolean>,
) {
    val colorDivider = MaterialTheme.colorScheme.onBackground
    AlertDialogCustomDesc(
        openDialog = { openDialog.value = false },
        title = "Tentang Aplikasi",
        desc = {
            LazyColumn {
                item {
                    Text(text = ABOUT_APP)
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(text = ABOUT_ONE)
                    Divider(color = colorDivider)
                    Text(text = ABOUT_TWO)
                    Divider(color = colorDivider)
                }
            }
        },
        backButton = "Ok",
        modifier = Modifier.padding(vertical = 12.dp)
    )
}