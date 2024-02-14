package com.sukacolab.app.ui.feature.register.model

data class RegisterRequest(
    val email: String,
    val password: String,
    val password_confirmation: String,
    val full_name: String
)