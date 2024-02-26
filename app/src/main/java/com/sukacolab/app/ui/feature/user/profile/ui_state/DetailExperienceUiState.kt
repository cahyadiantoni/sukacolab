package com.sukacolab.app.ui.feature.user.profile.ui_state

import com.sukacolab.app.data.source.network.response.DetailExperience
import com.sukacolab.app.data.source.network.response.DetailExperienceResponse

sealed class DetailExperienceUiState {
    class Success(val data: DetailExperience) : DetailExperienceUiState()
    class Failure(val error: Throwable) : DetailExperienceUiState()
    object Loading : DetailExperienceUiState()
    object Empty : DetailExperienceUiState()
}