package com.sukacolab.app.ui.feature.user.bookmark.ui_state

import com.sukacolab.app.data.source.network.response.Project

sealed class BookmarkUiState {
    class Success(val data: List<Project>) : BookmarkUiState()
    class Failure(val error: Throwable) : BookmarkUiState()
    object Loading : BookmarkUiState()
    object Empty : BookmarkUiState()
}