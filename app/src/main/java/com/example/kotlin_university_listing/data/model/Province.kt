package com.example.kotlin_university_listing.data.model

data class Province(
    val id: Int,
    val name: String,
    val universities: List<University>
)