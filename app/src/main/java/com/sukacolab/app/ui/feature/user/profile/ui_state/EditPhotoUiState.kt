package com.sukacolab.app.ui.feature.user.profile.ui_state

import com.sukacolab.app.data.source.network.response.BaseResponse

sealed class EditPhotoUiState {
    class Success(val data: BaseResponse) : EditPhotoUiState()
    class Failure(val error: Throwable) : EditPhotoUiState()
    object Loading : EditPhotoUiState()
    object Empty : EditPhotoUiState()
}
