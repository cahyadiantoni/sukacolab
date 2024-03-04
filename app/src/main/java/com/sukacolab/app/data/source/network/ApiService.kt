package com.sukacolab.app.data.source.network

import com.sukacolab.app.data.source.network.request.CertificationRequest
import com.sukacolab.app.data.source.network.request.EditAboutRequest
import com.sukacolab.app.data.source.network.request.EditMeRequest
import com.sukacolab.app.data.source.network.request.EducationRequest
import com.sukacolab.app.data.source.network.request.ExperienceRequest
import com.sukacolab.app.data.source.network.request.LoginRequest
import com.sukacolab.app.data.source.network.request.ProjectRequest
import com.sukacolab.app.data.source.network.request.SettingEmailRequest
import com.sukacolab.app.data.source.network.request.SettingPasswordRequest
import com.sukacolab.app.data.source.network.request.SkillRequest
import com.sukacolab.app.data.source.network.response.BaseResponse
import com.sukacolab.app.data.source.network.response.CertificationResponse
import com.sukacolab.app.data.source.network.response.DetailCertificationResponse
import com.sukacolab.app.data.source.network.response.DetailEducationResponse
import com.sukacolab.app.data.source.network.response.DetailExperienceResponse
import com.sukacolab.app.data.source.network.response.DetailProjectResponse
import com.sukacolab.app.data.source.network.response.DetailSkillResponse
import com.sukacolab.app.data.source.network.response.EducationResponse
import com.sukacolab.app.data.source.network.response.ExperienceResponse
import com.sukacolab.app.data.source.network.response.JoinedProjectResponse
import com.sukacolab.app.data.source.network.response.LoginResponse
import com.sukacolab.app.data.source.network.response.ProfileOtherResponse
import com.sukacolab.app.data.source.network.response.ProfileResponse
import com.sukacolab.app.data.source.network.response.Project
import com.sukacolab.app.data.source.network.response.ProjectResponse
import com.sukacolab.app.data.source.network.response.SkillResponse
import com.sukacolab.app.data.source.network.response.UserJoinResponse
import com.sukacolab.app.ui.feature.register.model.RegisterRequest
import com.sukacolab.app.ui.feature.register.model.RegisterResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @POST("/api/auth/register")
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

    @GET("/api/profile/experience/{take}/{userId}")
    suspend fun getExperience(
        @Header("Authorization") token: String,
        @Path("take") take: Int,
        @Path("userId") userId: Int,
    ): ExperienceResponse

    @GET("/api/profile/certification/{take}/{userId}")
    suspend fun getCertification(
        @Header("Authorization") token: String,
        @Path("take") take: Int,
        @Path("userId") userId: Int,
    ): CertificationResponse

    @GET("/api/profile/skill/{take}/{userId}")
    suspend fun getSkill(
        @Header("Authorization") token: String,
        @Path("take") take: Int,
        @Path("userId") userId: Int,
    ): SkillResponse

    @GET("/api/profile/education/{take}/{userId}")
    suspend fun getEducation(
        @Header("Authorization") token: String,
        @Path("take") take: Int,
        @Path("userId") userId: Int,
    ): EducationResponse

    @GET("/api/profile/experience/detail/{id}")
    suspend fun getDetailExperience(
        @Header("Authorization") token: String,
        @Path("id") id: String,
    ): DetailExperienceResponse

    @GET("/api/profile/certification/detail/{id}")
    suspend fun getDetailCertification(
        @Header("Authorization") token: String,
        @Path("id") id: String,
    ): DetailCertificationResponse

    @GET("/api/profile/skill/detail/{id}")
    suspend fun getDetailSkill(
        @Header("Authorization") token: String,
        @Path("id") id: String,
    ): DetailSkillResponse

    @GET("/api/profile/education/detail/{id}")
    suspend fun getDetailEducation(
        @Header("Authorization") token: String,
        @Path("id") id: String,
    ): DetailEducationResponse

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

    @POST("/api/profile/me/edit")
    fun editMe(
        @Header("Authorization") token: String,
        @Body request: EditMeRequest
    ): Call<BaseResponse>

    @POST("/api/profile/about/edit")
    fun editAbout(
        @Header("Authorization") token: String,
        @Body request: EditAboutRequest
    ): Call<BaseResponse>

    @POST("/api/profile/experience/add")
    fun addExperience(
        @Header("Authorization") token: String,
        @Body request: ExperienceRequest
    ): Call<BaseResponse>

    @PUT("/api/profile/experience/edit/{id}")
    fun editExperience(
        @Header("Authorization") token: String,
        @Path("id") id: String,
        @Body request: ExperienceRequest
    ): Call<BaseResponse>

    @DELETE("/api/profile/experience/delete/{id}")
    fun deleteExperience(
        @Header("Authorization") token: String,
        @Path("id") id: String,
    ): Call<BaseResponse>

    @POST("/api/profile/certification/add")
    fun addCertification(
        @Header("Authorization") token: String,
        @Body request: CertificationRequest
    ): Call<BaseResponse>

    @PUT("/api/profile/certification/edit/{id}")
    fun editCertification(
        @Header("Authorization") token: String,
        @Path("id") id: String,
        @Body request: CertificationRequest
    ): Call<BaseResponse>

    @DELETE("/api/profile/certification/delete/{id}")
    fun deleteCertification(
        @Header("Authorization") token: String,
        @Path("id") id: String,
    ): Call<BaseResponse>

    @POST("/api/profile/skill/add")
    fun addSkill(
        @Header("Authorization") token: String,
        @Body request: SkillRequest
    ): Call<BaseResponse>

    @PUT("/api/profile/skill/edit/{id}")
    fun editSkill(
        @Header("Authorization") token: String,
        @Path("id") id: String,
        @Body request: SkillRequest
    ): Call<BaseResponse>

    @DELETE("/api/profile/skill/delete/{id}")
    fun deleteSkill(
        @Header("Authorization") token: String,
        @Path("id") id: String,
    ): Call<BaseResponse>

    @POST("/api/profile/education/add")
    fun addEducation(
        @Header("Authorization") token: String,
        @Body request: EducationRequest
    ): Call<BaseResponse>

    @PUT("/api/profile/education/edit/{id}")
    fun editEducation(
        @Header("Authorization") token: String,
        @Path("id") id: String,
        @Body request: EducationRequest
    ): Call<BaseResponse>

    @DELETE("/api/profile/education/delete/{id}")
    fun deleteEducation(
        @Header("Authorization") token: String,
        @Path("id") id: String,
    ): Call<BaseResponse>

    @POST("/api/profile/me/edit")
    fun editProfile(
        @Header("Authorization") token: String,
        @Body request: EditMeRequest
    ): Call<BaseResponse>

    @Multipart
    @POST("/api/profile/me/photo/edit")
    fun changePhoto(
        @Header("Authorization") token: String,
        @Part photo: MultipartBody.Part,
    ): Call<BaseResponse>

    @Multipart
    @POST("/api/profile/me/resume/edit")
    fun changeResume(
        @Header("Authorization") token: String,
        @Part resume: MultipartBody.Part,
    ): Call<BaseResponse>

    @GET("/api/project/me")
    suspend fun getUrProject(
        @Header("Authorization") token: String,
    ): ProjectResponse

    @GET("/api/project/review")
    suspend fun getReviewProject(
        @Header("Authorization") token: String,
    ): ProjectResponse

    @GET("/api/project/{active}/{take}")
    suspend fun getProject(
        @Header("Authorization") token: String,
        @Path("active") active: Int,
        @Path("take") take: Int,
    ): ProjectResponse

    @GET("/api/project/detail/{projectId}")
    suspend fun getDetailProject(
        @Header("Authorization") token: String,
        @Path("projectId") projectId: String,
    ): DetailProjectResponse

    @POST("/api/project/join/{projectId}")
    fun joinProject(
        @Header("Authorization") token: String,
        @Path("projectId") projectId: String,
    ): Call<BaseResponse>

    @GET("/api/project/join")
    suspend fun getJoinedProject(
        @Header("Authorization") token: String,
    ): JoinedProjectResponse

    @GET("/api/project/bookmark")
    suspend fun getBookmarkProject(
        @Header("Authorization") token: String,
    ): ProjectResponse

    @POST("/api/project/bookmark/{projectId}")
    fun bookmarkProject(
        @Header("Authorization") token: String,
        @Path("projectId") projectId: String,
    ): Call<BaseResponse>

    @POST("/api/project/review/{projectId}/{review}")
    fun reviewProject(
        @Header("Authorization") token: String,
        @Path("projectId") projectId: String,
        @Path("review") review: String,
    ): Call<BaseResponse>

    @POST("/api/project/add")
    fun addProject(
        @Header("Authorization") token: String,
        @Body request: ProjectRequest
    ): Call<BaseResponse>

    @GET("/api/project/search/{search}")
    suspend fun searchProject(
        @Header("Authorization") token: String,
        @Path("search") search: String,
    ): ProjectResponse

    @GET("/api/project/userjoin/{projectId}")
    suspend fun getUserJoin(
        @Header("Authorization") token: String,
        @Path("projectId") projectId: String,
    ): UserJoinResponse

    @GET("/api/project/user/{userId}/{projectId}")
    suspend fun getProfileUserJoin(
        @Header("Authorization") token: String,
        @Path("userId") userId: String,
        @Path("projectId") projectId: String,
    ): ProfileOtherResponse

    @POST("/api/project/userjoin/review/{userId}/{projectId}/{review}")
    fun userJoinReview(
        @Header("Authorization") token: String,
        @Path("userId") userId: String,
        @Path("projectId") projectId: String,
        @Path("review") review: String,
    ): Call<BaseResponse>
}