package com.sukacolab.app.ui.feature.user.profile.uiState

import com.sukacolab.app.data.source.network.response.Experience

sealed class ExperienceUiState {
    class Success(val data: List<Experience>) : ExperienceUiState()
    class Failure(val error: Throwable) : ExperienceUiState()
    object Loading : ExperienceUiState()
    object Empty : ExperienceUiState()
}