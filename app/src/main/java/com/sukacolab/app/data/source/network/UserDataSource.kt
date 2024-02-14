package com.sukacolab.app.data.source.network

import com.sukacolab.app.data.source.network.request.LoginRequest
import com.sukacolab.app.data.source.network.response.LoginResponse

interface UserDataSource {
    suspend fun login(request: LoginRequest) : LoginResponse
}