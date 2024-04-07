package com.example.kotlin_university_listing

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kotlin_university_listing.data.model.Province
import com.example.kotlin_university_listing.data.remote.ServiceBuilder
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
        call.enqueue(object : Callback<Province> {
            override fun onResponse(call: Call<Province>, response: Response<Province>) {
                if (response.isSuccessful) {
                    val province = response.body()
                    if (province != null) {
                        Log.e("Response Body", province.toString())

                        for (university in province.universities) {
                            Log.e("University", university.name)
                            // Diğer üniversite özelliklerini burada kullanabilirsiniz
                        }
                    } else {
                        Log.e("Response Body Null", "Response body is null")
                    }
                } else {
                    Log.e("Response Unsuccessful", response.message())
                }
            }

            override fun onFailure(call: Call<Province>, t: Throwable) {
                Log.e("API Call Failed", t.message ?: "Unknown error")
            }
        })


    }
}