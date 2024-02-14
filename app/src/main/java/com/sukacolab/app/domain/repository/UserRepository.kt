package com.sukacolab.app.domain.repository

import com.sukacolab.app.data.source.network.request.LoginRequest
import com.sukacolab.app.data.source.network.response.LoginResponse

interface UserRepository {

    fun login(request: LoginRequest) : LoginResponse

}