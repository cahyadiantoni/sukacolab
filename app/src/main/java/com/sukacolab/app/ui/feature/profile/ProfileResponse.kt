package com.sukacolab.app.ui.feature.profile

import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: ProfileItem
)