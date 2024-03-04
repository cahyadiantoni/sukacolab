package com.sukacolab.app.ui.feature.user.profile_other.ui_state

import com.sukacolab.app.data.source.network.response.Education

sealed class EducationUiState {
    class Success(val data: List<Education>) : EducationUiState()
    class Failure(val error: Throwable) : EducationUiState()
    object Loading : EducationUiState()
    object Empty : EducationUiState()
}