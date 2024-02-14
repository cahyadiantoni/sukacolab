package com.sukacolab.app.ui.feature.profile

import com.google.gson.annotations.SerializedName

data class ProfileItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("email")
    val email: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("is_complete_personality_test")
    val isPersonalityTest: Boolean,
    @SerializedName("is_complete_face_test")
    val isFaceTest: Boolean,
    @SerializedName("is_subscription_active")
    val isSubscription: Boolean,
    @SerializedName("face_shape")
    val faceShape: String,
    @SerializedName("personality_type")
    val personalityType: String
)