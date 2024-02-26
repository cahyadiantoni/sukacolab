package com.sukacolab.app.data.source.network.response

import com.google.gson.annotations.SerializedName

data class DetailExperienceResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val `data`: DetailExperience
)

data class DetailExperience(
    @SerializedName("id")
    val id: Int,
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("company")
    val company: String,
    @SerializedName("role")
    val role: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("start_date")
    val startDate: String,
    @SerializedName("end_date")
    val endDate: String,
    @SerializedName("is_now")
    val isNow: Int,
)