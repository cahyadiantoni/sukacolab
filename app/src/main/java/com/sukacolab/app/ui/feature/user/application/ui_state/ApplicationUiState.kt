package com.sukacolab.app.ui.feature.user.application.ui_state

import com.sukacolab.app.data.source.network.response.JoinedProject

sealed class ApplicationUiState {
    class Success(val data: List<JoinedProject>) : ApplicationUiState()
    class Failure(val error: Throwable) : ApplicationUiState()
    object Loading : ApplicationUiState()
    object Empty : ApplicationUiState()
}