package com.sukacolab.app.ui.feature.user.profile.ui_state

import com.sukacolab.app.data.source.network.response.Certification

sealed class CertificationUiState {
    class Success(val data: List<Certification>) : CertificationUiState()
    class Failure(val error: Throwable) : CertificationUiState()
    object Loading : CertificationUiState()
    object Empty : CertificationUiState()
}