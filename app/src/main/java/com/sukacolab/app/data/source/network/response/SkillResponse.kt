package com.sukacolab.app.data.source.network.response

import com.google.gson.annotations.SerializedName

data class SkillResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: List<Skill>
)

data class Skill(
    @SerializedName("id")
    val id: Int,
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
)