package com.sukacolab.app.ui.feature.user.search.ui_state

import com.sukacolab.app.data.source.network.response.Project

sealed class SearchUiState {
    class Success(val data: List<Project>) : SearchUiState()
    class Failure(val error: Throwable) : SearchUiState()
    object Loading : SearchUiState()
    object Empty : SearchUiState()
}