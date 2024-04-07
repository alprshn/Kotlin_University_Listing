package com.example.kotlin_university_listing.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Province(
    @SerializedName("id") @Expose
    val id: Int,
    @SerializedName("province") @Expose
    val province: String,
    @SerializedName("universities") @Expose
    val universities: List<University>
)