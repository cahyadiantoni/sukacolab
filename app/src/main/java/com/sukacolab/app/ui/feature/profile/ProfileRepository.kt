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
        Log.d("neo-tag", "profileDetails: ${token.toString()}")

        emit(
            apiService.getProfile(
                token = "bearer $token"
            ).data
        )
    }.flowOn(Dispatchers.IO)
}