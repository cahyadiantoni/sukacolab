package com.sukacolab.app.ui.feature.user.profile.uiState

import com.sukacolab.app.data.source.network.response.ProfileItem

sealed class ProfileUiState {
    class Success(val data: ProfileItem) : ProfileUiState()
    class Failure(val error: Throwable) : ProfileUiState()
    object Loading : ProfileUiState()
    object Empty : ProfileUiState()
}
