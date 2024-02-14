package com.sukacolab.app.domain.repository

import com.sukacolab.app.data.source.network.request.LoginRequest
import com.sukacolab.app.util.Resource

interface AuthRepository {
    suspend fun login(loginRequest: LoginRequest): Resource<Unit>
}