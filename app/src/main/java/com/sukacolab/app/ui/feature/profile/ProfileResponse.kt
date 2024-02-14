package com.sukacolab.app.ui.feature.profile

import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    @SerializedName("data")
    val `data`: ProfileItem,
    @SerializedName("message")
    val message: String
)