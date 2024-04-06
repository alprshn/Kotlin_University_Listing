package com.example.kotlin_university_listing

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kotlin_university_listing.data.remote.ServiceBuilder
import android.util.Log
import com.example.kotlin_university_listing.data.model.Province
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val call = ServiceBuilder.service.getProvinces()
        call.enqueue(object : Callback<List<Province>> {
            override fun onResponse(call: Call<List<Province>>, response: Response<List<Province>>) {
                if (response.isSuccessful) {
                    val provinces = response.body()
                    provinces?.let {
                        for (province in provinces) {
                            Log.d("Province", province.name)
                            for (university in province.universities) {
                                Log.d("University", university.name)
                                // Diğer üniversite özelliklerini burada kullanabilirsiniz
                            }
                        }
                    }
                } else {
                    Log.e("Response Unsuccessful", response.message())
                }
            }

            override fun onFailure(call: Call<List<Province>>, t: Throwable) {
                Log.e("API Call Failed", t.message ?: "Unknown error")
            }
        })
    }
}