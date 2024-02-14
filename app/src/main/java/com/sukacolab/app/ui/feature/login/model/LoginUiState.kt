package com.sukacolab.app.ui.feature.login.model

import com.sukacolab.app.domain.model.User
import com.sukacolab.app.ui.feature.register.model.RegisterRequest

sealed interface LoginUiState {
    object Idle : LoginUiState
    object Loading : LoginUiState
    object Empty : LoginUiState

    data class Success(val data: RegisterRequest) : LoginUiState

    data class Error(val error: RegisterRequest) : LoginUiState
}