package com.sukacolab.app.ui.feature.user.home.ui_state

import com.sukacolab.app.data.source.network.response.Project

sealed class HomeUiState {
    class Success(val data: List<Project>) : HomeUiState()
    class Failure(val error: Throwable) : HomeUiState()
    object Loading : HomeUiState()
    object Empty : HomeUiState()
}