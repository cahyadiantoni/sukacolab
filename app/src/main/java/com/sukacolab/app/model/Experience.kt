package com.sukacolab.app.model

data class Experience(
    val company: String,
    var description: String?=null,
    var endDate: String?=null,
    var id: Int?=null,
    val startDate: String,
    val title: String
)