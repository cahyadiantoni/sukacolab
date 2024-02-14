package com.sukacolab.app.domain.model

import com.sukacolab.app.util.Resource

data class AuthResult(
    val passwordError: String? = null,
    val emailError : String? = null,
    val result: Resource<Unit>? = null
)