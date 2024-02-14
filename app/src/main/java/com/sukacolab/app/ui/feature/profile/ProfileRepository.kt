package com.sukacolab.app.ui.feature.profile

import android.util.Log
import com.sukacolab.app.data.source.local.AuthPreferences
import com.sukacolab.app.data.source.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

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