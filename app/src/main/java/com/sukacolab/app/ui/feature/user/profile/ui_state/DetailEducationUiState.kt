package com.sukacolab.app.ui.feature.user.profile.ui_state

import com.sukacolab.app.data.source.network.response.DetailEducation

sealed class DetailEducationUiState {
    class Success(val data: DetailEducation) : DetailEducationUiState()
    class Failure(val error: Throwable) : DetailEducationUiState()
    object Loading : DetailEducationUiState()
    object Empty : DetailEducationUiState()
}