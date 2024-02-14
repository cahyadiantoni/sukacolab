package com.sukacolab.app.data.source.network.response

import com.google.gson.annotations.SerializedName

data class BaseResponse(
    @SerializedName("message") val message: String,
    @SerializedName("data") val error: Boolean,
)
