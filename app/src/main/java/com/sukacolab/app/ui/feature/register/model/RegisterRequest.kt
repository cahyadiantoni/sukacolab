package com.sukacolab.app.ui.feature.register.model

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String,
)