package com.example.kotlin_university_listing.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class University(
    @SerializedName("name") @Expose
    val name: String,
    @SerializedName("phone") @Expose
    val phone: String,
    @SerializedName("fax") @Expose
    val fax: String,
    @SerializedName("website") @Expose
    val website: String,
    @SerializedName("email") @Expose
    val email: String,
    @SerializedName("address") @Expose
    val address: String,
    @SerializedName("rector") @Expose
    val rector: String
)