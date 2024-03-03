package com.sukacolab.app.ui.feature.user.ur_project.ui_state

import com.sukacolab.app.data.source.network.response.DetailProject

sealed class UrDetailProjectUiState {
    class Success(val data: DetailProject) : UrDetailProjectUiState()
    class Failure(val error: Throwable) : UrDetailProjectUiState()
    object Loading : UrDetailProjectUiState()
    object Empty : UrDetailProjectUiState()
}