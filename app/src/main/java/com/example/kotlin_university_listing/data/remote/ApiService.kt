package com.example.kotlin_university_listing.data.remote

import com.example.kotlin_university_listing.data.model.Province
import com.example.kotlin_university_listing.data.model.ProvinceResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("page-1.json")
    fun getProvinces(): Call<ProvinceResponse>
}