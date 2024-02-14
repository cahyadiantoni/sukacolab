package com.sukacolab.app.model

data class AddUser(
    val message: String,
    val status: String,
    val user: User,
    val uid: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
)