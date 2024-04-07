package com.example.kotlin_university_listing.data.model

import com.google.gson.annotations.SerializedName

data class ProvinceResponse (
    @SerializedName("data")
    val data: List<Province>
)