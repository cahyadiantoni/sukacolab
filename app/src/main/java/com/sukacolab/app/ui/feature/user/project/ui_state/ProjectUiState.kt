package com.sukacolab.app.ui.feature.user.project.ui_state

import com.sukacolab.app.data.source.network.response.Project

sealed class ProjectUiState {
    class Success(val data: List<Project>) : ProjectUiState()
    class Failure(val error: Throwable) : ProjectUiState()
    object Loading : ProjectUiState()
    object Empty : ProjectUiState()
}