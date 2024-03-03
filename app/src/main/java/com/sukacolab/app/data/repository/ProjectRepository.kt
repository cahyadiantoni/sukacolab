package com.sukacolab.app.data.repository

import android.util.Log
import com.sukacolab.app.data.source.local.AuthPreferences
import com.sukacolab.app.data.source.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ProjectRepository(
    private val apiService: ApiService,
    private val authPreferences: AuthPreferences
) {
    fun getUrProject() = flow {
        val token = authPreferences.getAuthToken()
        Log.d("Hit API Experience", "get Experience")
        emit(apiService.getUrProject(
            token = "Bearer $token"
        ).data)
    }.flowOn(Dispatchers.IO)

    fun getReviewProject() = flow {
        val token = authPreferences.getAuthToken()
        Log.d("Hit API Experience", "get Experience")
        emit(apiService.getReviewProject(
            token = "Bearer $token"
        ).data)
    }.flowOn(Dispatchers.IO)

    fun getProject(active: Int, take: Int) = flow {
        val token = authPreferences.getAuthToken()
        Log.d("Hit API Experience", "get Experience")
        emit(apiService.getProject(
            token = "Bearer $token",
            active = active,
            take = take
        ).data)
    }.flowOn(Dispatchers.IO)

    fun getJoinedProject() = flow {
        val token = authPreferences.getAuthToken()
        Log.d("Hit API Experience", "get Experience")
        emit(apiService.getJoinedProject(
            token = "Bearer $token",
        ).data)
    }.flowOn(Dispatchers.IO)

    fun getBookmarkProject() = flow {
        val token = authPreferences.getAuthToken()
        Log.d("Hit API Experience", "get Experience")
        emit(apiService.getBookmarkProject(
            token = "Bearer $token",
        ).data)
    }.flowOn(Dispatchers.IO)

    fun getDetailProject(projectId: String) = flow {
        val token = authPreferences.getAuthToken()
        Log.d("Hit API Experience", "get Experience")
        emit(apiService.getDetailProject(
            token = "Bearer $token",
            projectId = projectId,
        ).data)
    }.flowOn(Dispatchers.IO)
}