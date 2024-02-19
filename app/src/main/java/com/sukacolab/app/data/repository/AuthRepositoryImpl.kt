package com.sukacolab.app.data.repository

import android.service.controls.ControlsProviderService.TAG
import android.util.Log
import com.sukacolab.app.data.source.local.AuthPreferences
import com.sukacolab.app.data.source.network.ApiService
import com.sukacolab.app.data.source.network.request.LoginRequest
import com.sukacolab.app.domain.repository.AuthRepository
import com.sukacolab.app.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException

class ProfileRepository(
    private val apiService: ApiService,
    private val authPreferences: AuthPreferences
) {
    fun profileDetails() = flow{
        val token = authPreferences.getAuthToken()
        val userId = authPreferences.getAuthId()
        Log.d("neo-tag", "profileDetails: ${userId.toString()}")

        emit(
            apiService.getProfile(
                token = "Bearer $token",
                userId = userId.toString()
            ).data
        )
    }.flowOn(Dispatchers.IO)
}