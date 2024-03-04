package com.sukacolab.app.ui.feature.user.profile_other.ui_state

import com.sukacolab.app.data.source.network.response.ProfileOtherItem

sealed class ProfileUiState {
    class Success(val data: ProfileOtherItem) : ProfileUiState()
    class Failure(val error: Throwable) : ProfileUiState()
    object Loading : ProfileUiState()
    object Empty : ProfileUiState()
}
