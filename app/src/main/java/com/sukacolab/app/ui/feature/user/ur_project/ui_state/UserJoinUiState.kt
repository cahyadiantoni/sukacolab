package com.sukacolab.app.ui.feature.user.ur_project.ui_state

import com.sukacolab.app.data.source.network.response.UserJoin

sealed class UserJoinUiState {
    class Success(val data: List<UserJoin>) : UserJoinUiState()
    class Failure(val error: Throwable) : UserJoinUiState()
    object Loading : UserJoinUiState()
    object Empty : UserJoinUiState()
}