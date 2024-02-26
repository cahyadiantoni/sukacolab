package com.sukacolab.app.data.source.network

import com.sukacolab.app.data.source.network.request.LoginRequest
import com.sukacolab.app.data.source.network.request.SettingEmailRequest
import com.sukacolab.app.data.source.network.request.SettingPasswordRequest
import com.sukacolab.app.data.source.network.response.BaseResponse
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

    @GET("/api/profile/me")
    suspend fun getProfile(
        @Header("Authorization") token: String
    ): ProfileResponse

    @GET("/api/profile/experience")
    suspend fun getExperience(
        @Header("Authorization") token: String,
    ): ExperienceResponse

    @GET("/api/profile/certification")
    suspend fun getCertification(
        @Header("Authorization") token: String,
    ): CertificationResponse

    @GET("/api/profile/skill")
    suspend fun getSkill(
        @Header("Authorization") token: String,
    ): SkillResponse

    @GET("/api/profile/education")
    suspend fun getEducation(
        @Header("Authorization") token: String,
    ): EducationResponse

    @GET("/api/profile/experience/all")
    suspend fun getAllExperience(
        @Header("Authorization") token: String,
    ): ExperienceResponse

    @GET("/api/profile/certification/all")
    suspend fun getAllCertification(
        @Header("Authorization") token: String,
    ): CertificationResponse

    @GET("/api/profile/skill/all")
    suspend fun getAllSkill(
        @Header("Authorization") token: String,
    ): SkillResponse

    @GET("/api/profile/education/all")
    suspend fun getAllEducation(
        @Header("Authorization") token: String,
    ): EducationResponse

    @POST("/api/auth/setting/email")
    fun setEmail(
        @Header("Authorization") token: String,
        @Body request: SettingEmailRequest
    ): Call<BaseResponse>

    @POST("/api/auth/setting/password")
    fun setPassword(
        @Header("Authorization") token: String,
        @Body request: SettingPasswordRequest
    ): Call<BaseResponse>

    @POST("/v1/auth/update-user/{userId}")
    suspend fun editProfile(
        @Header("Authorization") token: String,
        @Path("userId") userId: String,
        @Body request: RegisterRequest
    ): RegisterResponse
}