package com.example.kotlin_university_listing.ui.main

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_university_listing.R
import com.example.kotlin_university_listing.data.model.Province
import com.example.kotlin_university_listing.data.model.ProvinceResponse
import com.example.kotlin_university_listing.data.model.RecyclerViewData
import com.example.kotlin_university_listing.data.remote.ServiceBuilder
import com.example.kotlin_university_listing.ui.adapter.MainActivityItemAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView:RecyclerView
    private lateinit var mList:MutableList<RecyclerViewData>
    private lateinit var adapter : MainActivityItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.main_rc_view)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        mList = mutableListOf()

        var nestedList1 : MutableList<String> = mutableListOf()
        nestedList1.add("selam")

        var nestedList2 : MutableList<String> = mutableListOf()
        nestedList2.add("iyi")

        mList.add(RecyclerViewData(nestedList1,"Deneme Adımı"))

        mList.add(RecyclerViewData(nestedList2,"asdsadsa Adımıdasdas"))

        adapter = MainActivityItemAdapter(mList)
        recyclerView.adapter = adapter


        RequestApi()///////Api Çekme Fonksiyounu
    }


    fun RequestApi(){
        ///////Api Çekme
        val call = ServiceBuilder.service.getProvinces()
        call.enqueue(object : Callback<ProvinceResponse> {
            override fun onResponse(call: Call<ProvinceResponse>, response: Response<ProvinceResponse>) {
                if (response.isSuccessful) {
                    val provinceResponse = response.body()
                    val provinces: List<Province> = provinceResponse!!.data
                    if (response != null) {
                        Log.e("Response Body", response.toString())

                        for (university in provinces[1].universities) {
                            Log.e("University", university.name)
                        }
                    } else {
                        Log.e("Response Body Null", "Response body is null")
                    }
                } else {
                    Log.e("Response Unsuccessful", response.message())
                }
            }

            override fun onFailure(call: Call<ProvinceResponse>, t: Throwable) {
                Log.e("API Call Failed", t.message ?: "Unknown error")
            }
        })
        /////////// Api çekmenin bittiği yer
    }





}