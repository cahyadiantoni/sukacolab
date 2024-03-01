package com.sukacolab.app.data.repository

import android.util.Log
import com.sukacolab.app.data.source.local.AuthPreferences
import com.sukacolab.app.data.source.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

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

    fun getExperience(take: Int) = flow {
        val token = authPreferences.getAuthToken()
        Log.d("Hit API Experience", "get Experience")
        emit(apiService.getExperience(
            token = "Bearer $token",
            take = take
        ).data)
    }.flowOn(Dispatchers.IO)

    fun getCertification(take: Int) = flow {
        val token = authPreferences.getAuthToken()
        Log.d("Hit API Certification", "get Certification")
        emit(apiService.getCertification(
            token = "Bearer $token",
            take = take
        ).data)
    }.flowOn(Dispatchers.IO)

    fun getSkill(take: Int) = flow {
        val token = authPreferences.getAuthToken()
        Log.d("Hit API Skill", "get Skill")
        emit(apiService.getSkill(
            token = "Bearer $token",
            take = take
        ).data)
    }.flowOn(Dispatchers.IO)

    fun getEducation(take: Int) = flow {
        val token = authPreferences.getAuthToken()
        Log.d("Hit API Education", "get Education")
        emit(apiService.getEducation(
            token = "Bearer $token",
            take = take
        ).data)
    }.flowOn(Dispatchers.IO)

    fun getDetailExperience(id: String) = flow {
        val token = authPreferences.getAuthToken()
        Log.d("Hit API Experience", "get Experience")
        emit(apiService.getDetailExperience(
            token = "Bearer $token",
            id = id
        ).data)
    }.flowOn(Dispatchers.IO)

    fun getDetailCertification(id: String) = flow {
        val token = authPreferences.getAuthToken()
        Log.d("Hit API Certification", "get Certification")
        emit(apiService.getDetailCertification(
            token = "Bearer $token",
            id = id
        ).data)
    }.flowOn(Dispatchers.IO)

    fun getDetailSkill(id: String) = flow {
        val token = authPreferences.getAuthToken()
        Log.d("Hit API Skill", "get Skill")
        emit(apiService.getDetailSkill(
            token = "Bearer $token",
            id = id
        ).data)
    }.flowOn(Dispatchers.IO)

    fun getDetailEducation(id: String) = flow {
        val token = authPreferences.getAuthToken()
        Log.d("Hit API Education", "get Education")
        emit(apiService.getDetailEducation(
            token = "Bearer $token",
            id = id
        ).data)
    }.flowOn(Dispatchers.IO)

}