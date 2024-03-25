package com.sukacolab.app.data.repository

import android.service.controls.ControlsProviderService.TAG
import android.util.Log
import com.sukacolab.app.data.source.local.AuthPreferences
import com.sukacolab.app.data.source.network.ApiService
import com.sukacolab.app.data.source.network.request.LoginRequest
import com.sukacolab.app.domain.repository.AuthRepository
import com.sukacolab.app.util.Resource
import retrofit2.HttpException
import java.io.IOException

class DefaultAuthRepository : AuthRepository {
    override suspend fun login(loginRequest: LoginRequest): Resource<Unit> {
        // Implement the login functionality here
        // You can perform network requests, handle responses, and return the appropriate Resource result
        // For example:
        return try {
            // Simulate a successful login
            Resource.Success(Unit)
        } catch (exception: Exception) {
            // Handle any error that occurred during the login process
            Resource.Error(exception.message ?: "Login failed")
        }
    }
}