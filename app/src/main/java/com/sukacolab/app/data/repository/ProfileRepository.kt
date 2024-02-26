package com.sukacolab.app.data.repository

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

    fun getExperience() = flow {
        val token = authPreferences.getAuthToken()
        Log.d("Hit API Experience", "get Experience")
        emit(apiService.getExperience(
            token = "Bearer $token"
        ).data)
    }.flowOn(Dispatchers.IO)

    fun getCertification() = flow {
        val token = authPreferences.getAuthToken()
        Log.d("Hit API Certification", "get Certification")
        emit(apiService.getCertification(
            token = "Bearer $token"
        ).data)
    }.flowOn(Dispatchers.IO)

    fun getSkill() = flow {
        val token = authPreferences.getAuthToken()
        Log.d("Hit API Skill", "get Skill")
        emit(apiService.getSkill(
            token = "Bearer $token"
        ).data)
    }.flowOn(Dispatchers.IO)

    fun getEducation() = flow {
        val token = authPreferences.getAuthToken()
        Log.d("Hit API Education", "get Education")
        emit(apiService.getEducation(
            token = "Bearer $token"
        ).data)
    }.flowOn(Dispatchers.IO)

    fun getAllExperience() = flow {
        val token = authPreferences.getAuthToken()
        Log.d("Hit API Experience", "get Experience")
        emit(apiService.getAllExperience(
            token = "Bearer $token"
        ).data)
    }.flowOn(Dispatchers.IO)

    fun getAllCertification() = flow {
        val token = authPreferences.getAuthToken()
        Log.d("Hit API Certification", "get Certification")
        emit(apiService.getAllCertification(
            token = "Bearer $token"
        ).data)
    }.flowOn(Dispatchers.IO)

    fun getAllSkill() = flow {
        val token = authPreferences.getAuthToken()
        Log.d("Hit API Skill", "get Skill")
        emit(apiService.getAllSkill(
            token = "Bearer $token"
        ).data)
    }.flowOn(Dispatchers.IO)

    fun getAllEducation() = flow {
        val token = authPreferences.getAuthToken()
        Log.d("Hit API Education", "get Education")
        emit(apiService.getAllEducation(
            token = "Bearer $token"
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