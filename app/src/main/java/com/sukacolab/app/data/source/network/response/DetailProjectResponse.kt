package com.sukacolab.app.data.source.network.response

import com.google.gson.annotations.SerializedName


data class DetailProjectResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: DetailProject
)

data class DetailProject(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("position")
    val position: String,
    @SerializedName("location")
    val location: String,
    @SerializedName("tipe")
    val tipe: String,
    @SerializedName("is_paid")
    val isPaid: Int,
    @SerializedName("time")
    val time: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("requirements")
    val requirements: String,
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("is_active")
    val isActive: Int,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("is_bookmarked")
    val isBookmarked: Int,
    @SerializedName("status_applied")
    val statusApplied: Int?,
    @SerializedName("user_name")
    val userName: String,
    @SerializedName("user_photo")
    val userPhoto: String,
)