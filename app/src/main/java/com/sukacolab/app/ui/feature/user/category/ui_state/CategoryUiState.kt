package com.sukacolab.app.ui.feature.user.category.ui_state

import com.sukacolab.app.data.source.network.response.Project

sealed class CategoryUiState {
    class Success(val data: List<Project>) : CategoryUiState()
    class Failure(val error: Throwable) : CategoryUiState()
    object Loading : CategoryUiState()
    object Empty : CategoryUiState()
}