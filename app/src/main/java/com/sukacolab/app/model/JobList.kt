package com.sukacolab.app.model

data class JobList(
    val currentPage: Int,
    val jobListings: List<JobItem>,
    val last: Boolean,
    val totalItems: Int,
    val totalPages: Int
)