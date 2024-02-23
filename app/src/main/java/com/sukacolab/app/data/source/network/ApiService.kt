package com.sukacolab.app.data.source.network

import com.sukacolab.app.data.source.network.request.LoginRequest
import com.sukacolab.app.data.source.network.response.CertificationResponse
import com.sukacolab.app.data.source.network.response.EducationResponse
import com.sukacolab.app.data.source.network.response.Experience
import com.sukacolab.app.data.source.network.response.ExperienceResponse
import com.sukacolab.app.data.source.network.response.LoginResponse
import com.sukacolab.app.data.source.network.response.ProfileResponse
import com.sukacolab.app.data.source.network.response.SkillResponse
import com.sukacolab.app.ui.feature.register.model.RegisterRequest
import com.sukacolab.app.ui.feature.register.model.RegisterResponse
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

    @GET("/api/auth/me")
    suspend fun getProfile(
        @Header("Authorization") token: String
    ): ProfileResponse

    @GET("/api/auth/experience")
    suspend fun getExperience(
        @Header("Authorization") token: String,
    ): ExperienceResponse

    @GET("/api/auth/certification")
    suspend fun getCertification(
        @Header("Authorization") token: String,
    ): CertificationResponse

    @GET("/api/auth/skill")
    suspend fun getSkill(
        @Header("Authorization") token: String,
    ): SkillResponse

    @GET("/api/auth/education")
    suspend fun getEducation(
        @Header("Authorization") token: String,
    ): EducationResponse

    @POST("/v1/auth/update-user/{userId}")
    suspend fun editProfile(
        @Header("Authorization") token: String,
        @Path("userId") userId: String,
        @Body request: RegisterRequest
    ): RegisterResponse
}