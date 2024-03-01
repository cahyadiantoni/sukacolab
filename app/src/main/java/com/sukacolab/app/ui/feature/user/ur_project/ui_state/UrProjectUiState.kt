package com.sukacolab.app.ui.feature.user.ur_project.ui_state

import com.sukacolab.app.data.source.network.response.Project

sealed class UrProjectUiState {
    class Success(val data: List<Project>) : UrProjectUiState()
    class Failure(val error: Throwable) : UrProjectUiState()
    object Loading : UrProjectUiState()
    object Empty : UrProjectUiState()
}