package com.sukacolab.app.ui.feature.user.profile.ui_state

import com.sukacolab.app.data.source.network.response.DetailCertification

sealed class DetailCertificationUiState {
    class Success(val data: DetailCertification) : DetailCertificationUiState()
    class Failure(val error: Throwable) : DetailCertificationUiState()
    object Loading : DetailCertificationUiState()
    object Empty : DetailCertificationUiState()
}