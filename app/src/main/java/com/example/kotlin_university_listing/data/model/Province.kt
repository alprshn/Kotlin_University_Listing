package com.example.kotlin_university_listing.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Province(
    @SerializedName("id")
    val id: Int,
    @SerializedName("province")
    val province: String,
    @SerializedName("universities")
    val universities: List<University>
)