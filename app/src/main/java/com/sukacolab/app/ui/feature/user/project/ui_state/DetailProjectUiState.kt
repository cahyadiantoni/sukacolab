package com.sukacolab.app.ui.feature.user.project.ui_state

import com.sukacolab.app.data.source.network.response.DetailProject

sealed class DetailProjectUiState {
    class Success(val data: DetailProject) : DetailProjectUiState()
    class Failure(val error: Throwable) : DetailProjectUiState()
    object Loading : DetailProjectUiState()
    object Empty : DetailProjectUiState()
}