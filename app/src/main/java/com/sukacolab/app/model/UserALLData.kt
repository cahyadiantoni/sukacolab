package com.sukacolab.app.model

data class UserALLData(
    val educations: List<Education>,
    val experiences: List<Experience>,
    val message: String,
    val skills: List<Skill>,
    val status: String,
    val user: User
)