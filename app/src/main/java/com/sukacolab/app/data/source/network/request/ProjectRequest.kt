package com.sukacolab.app.data.source.network.request

import com.google.gson.annotations.SerializedName

data class ProjectRequest (
    @SerializedName("name")
    val name: String,
    @SerializedName("position")
    val position: String,
    @SerializedName("location")
    val location: String,
    @SerializedName("tipe")
    val tipe: String,
    @SerializedName("is_paid")
    val isPaid: Boolean,
    @SerializedName("time")
    val time: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("requirements")
    val requirements: String,
)