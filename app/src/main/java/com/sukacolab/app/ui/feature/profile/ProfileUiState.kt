package com.sukacolab.app.ui.feature.profile

sealed class ProfileUiState {
    class Success(val data: ProfileItem) : ProfileUiState()
    class Failure(val error: Throwable) : ProfileUiState()
    object Loading : ProfileUiState()
    object Empty : ProfileUiState()
}
