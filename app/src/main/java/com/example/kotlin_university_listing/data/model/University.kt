package com.example.kotlin_university_listing.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class University(
    @SerializedName("name")
    val name: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("fax")
    val fax: String,
    @SerializedName("website")
    val website: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("rector")
    val rector: String
)