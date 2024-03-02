package com.sukacolab.app.ui.feature.admin.review.ui_state

import com.sukacolab.app.data.source.network.response.Project

sealed class ReviewUiState {
    class Success(val data: List<Project>) : ReviewUiState()
    class Failure(val error: Throwable) : ReviewUiState()
    object Loading : ReviewUiState()
    object Empty : ReviewUiState()
}