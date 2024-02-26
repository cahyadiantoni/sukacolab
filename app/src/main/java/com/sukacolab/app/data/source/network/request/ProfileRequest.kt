package com.sukacolab.app.data.source.network.request

import com.google.gson.annotations.SerializedName
import java.io.File

data class EditMe(
    @SerializedName("name")
    val name: String,
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
    @SerializedName("photo")
    val photo: File,
    @SerializedName("resume")
    val resume: File
)

data class EditAbout(
    @SerializedName("about")
    val about: String,
)

data class Experience(
    @SerializedName("title")
    val title: String,
    @SerializedName("company")
    val company: String,
    @SerializedName("role")
    val role: String,
    @SerializedName("start_date")
    val startDate: String,
    @SerializedName("end_date")
    val endDate: String,
    @SerializedName("is_now")
    val isNow: Boolean
)

data class Certification(
    @SerializedName("name")
    val name: String,
    @SerializedName("publisher")
    val publisher: String,
    @SerializedName("credential")
    val credential: String,
    @SerializedName("publish_date")
    val publishDate: String,
    @SerializedName("expire_date")
    val expireDate: String
)

data class Skill(
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String
)

data class Education(
    @SerializedName("instansi")
    val instansi: String,
    @SerializedName("major")
    val major: String,
    @SerializedName("start_date")
    val startDate: String,
    @SerializedName("end_date")
    val endDate: String,
    @SerializedName("is_now")
    val isNow: Boolean
)
