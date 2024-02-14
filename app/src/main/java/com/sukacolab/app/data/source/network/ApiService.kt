package com.sukacolab.app.data.source.network

import com.sukacolab.app.data.source.network.request.LoginRequest
import com.sukacolab.app.data.source.network.response.LoginResponse
import com.sukacolab.app.ui.feature.profile.ProfileResponse
import com.sukacolab.app.ui.feature.register.model.RegisterRequest
import com.sukacolab.app.ui.feature.register.model.RegisterResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @POST("/api/auth/register-user")
    fun register(
        @Body request: RegisterRequest
    ): Call<RegisterResponse>

    @POST("/api/auth/login")
    fun login(
        @Body request: LoginRequest
    ): Call<LoginResponse>

    @GET("/v1/users/{userId}")
    suspend fun getProfile(
        @Header("Authorization") token: String,
        @Path("userId") userId: String
    ): ProfileResponse

    @POST("/v1/auth/update-user/{userId}")
    suspend fun editProfile(
        @Header("Authorization") token: String,
        @Path("userId") userId: String,
        @Body request: RegisterRequest
    ): RegisterResponse
}