package com.sukacolab.app.data.source.network.response

import com.google.gson.annotations.SerializedName

data class CertificationResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: List<Certification>
)

data class Certification(
    @SerializedName("id")
    val id: Int,
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("publisher")
    val publisher: String,
    @SerializedName("credential")
    val credential: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("publish_date")
    val publishDate: String,
    @SerializedName("expire_date")
    val expireDate: String,
)