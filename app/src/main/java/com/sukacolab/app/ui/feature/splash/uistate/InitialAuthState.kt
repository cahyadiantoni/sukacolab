package com.sukacolab.app.ui.feature.splash.uistate

sealed class InitialAuthState {
    object Authenticated : InitialAuthState()
    object NotAuthenticated : InitialAuthState()
    object Loading : InitialAuthState()
}
