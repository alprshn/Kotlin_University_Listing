package com.example.kotlin_university_listing.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object  ServiceBuilder {
    private const val BASE_URL = "https://storage.googleapis.com/invio-com/usg-challenge/universities-at-turkey/page-1.json"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: ApiService = retrofit.create(ApiService::class.java)
}
}