package com.sukacolab.app.data.source.network.response

import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: ProfileItem
)

data class ProfileItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("photo")
    val photo: String,
    @SerializedName("summary")
    val summary: String,
    @SerializedName("linkedin")
    val linkedin: String,
    @SerializedName("github")
    val github: String,
    @SerializedName("whatsapp")
    val whatsapp: String,
    @SerializedName("instagram")
    val instagram: String,
    @SerializedName("resume")
    val resume: String,
    @SerializedName("about")
    val about: String,
)

