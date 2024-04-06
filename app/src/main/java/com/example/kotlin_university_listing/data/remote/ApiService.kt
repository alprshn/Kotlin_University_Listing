package com.example.kotlin_university_listing.data.remote

import com.example.kotlin_university_listing.data.model.Province
import retrofit2.Call
import retrofit2.http.GET

interface ApiService{
    @GET("https://storage.googleapis.com/invio-com/usg-challenge/universities-at-turkey/page-1.json") // API'nin tam URL'sini buraya yazın
    fun getProvinces(): Call<List<Province>>
}