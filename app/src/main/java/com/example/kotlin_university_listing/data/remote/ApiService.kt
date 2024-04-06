package com.example.kotlin_university_listing.data.remote

data class ApiService(
    val url: String?,

    val hours: String?,
    val days: String?,

    val start: String?,
    val finish: String?,

    val title: String?,
    val description: String?,
    val name: String?
)