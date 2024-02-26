package com.sukacolab.app.data.source.network.response

import com.google.gson.annotations.SerializedName

data class DetailEducationResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: DetailEducation
)

data class DetailEducation(
    @SerializedName("id")
    val id: Int,
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("instansi")
    val instansi: String,
    @SerializedName("major")
    val major: String,
    @SerializedName("start_date")
    val startDate: String,
    @SerializedName("end_date")
    val endDate: String,
    @SerializedName("is_now")
    val isNow: Int,
)