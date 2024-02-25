package com.sukacolab.app.data.source.network.request

import com.google.gson.annotations.SerializedName

data class SettingEmailRequest(
    @SerializedName("email")
    val email: String
)

data class SettingPasswordRequest(
    @SerializedName("old_password")
    val oldPassword: String,
    @SerializedName("password")
    val password: String
)