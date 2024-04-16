package com.example.kotlin_university_listing.ui.main

import android.os.Bundle
import android.util.Log
import android.widget.ExpandableListView
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
import com.example.kotlin_university_listing.ui.adapter.ThreeLevelListAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var expandableListView: ExpandableListView

    val cityNames = mutableListOf<String>()
    private val q1 = arrayOf("List View", "Grid View")
    private val q2 = arrayOf("Linear Layout", "Relative Layout")
    private val q3 = arrayOf("Recycle View")
    private val des1 = arrayOf("A layout that organizes its children into a single horizontal or vertical row. It creates a scrollbar if the length of the window exceeds the length of the screen.")
    private val des2 = arrayOf("Enables you to specify the location of child objects relative to each other (child A to the left of child B) or to the parent (aligned to the top of the parent).")
    private val des3 = arrayOf("This list contains linear layout information")
    private val des4 = arrayOf("This list contains relative layout information,Displays a scrolling grid of columns and rows")
    private val des5 = arrayOf("Under the RecyclerView model, several different components work together to display your data. Some of these components can be used in their unmodified form; for example, your app is likely to use the RecyclerView class directly. In other cases, we provide an abstract class, and your app is expected to extend it; for example, every app that uses RecyclerView needs to define its own view holder, which it does by extending the abstract RecyclerView.ViewHolder class.")

    private val thirdLevelq1 = LinkedHashMap<String, Array<String>>()
    private val thirdLevelq2 = LinkedHashMap<String, Array<String>>()
    private val thirdLevelq3 = LinkedHashMap<String, Array<String>>()

    private val secondLevel = ArrayList<Array<String>>()
    private val data = ArrayList<LinkedHashMap<String, Array<String>>>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        RequestApi()
    }



    private fun RequestApi(){
        val recyclerViewDataList = mutableListOf<RecyclerViewData>()
        val call = ServiceBuilder.service.getProvinces()
        call.enqueue(object : Callback<ProvinceResponse> {
            override fun onResponse(call: Call<ProvinceResponse>, response: Response<ProvinceResponse>) {
                if (response.isSuccessful) {
                    val provinceResponse = response.body()
                    val provinces: List<Province> = provinceResponse!!.data
                    if (response != null) {
                        Log.e("Response Body", response.toString())

                        for (i in 0..provinces.size-1 ) {
                            Log.e("University", provinces[i].province)
                            cityNames.add(provinces[i].province)
                        }
                        val threeLevelListAdapterAdapter = ThreeLevelListAdapter(this@MainActivity, cityNames.toTypedArray(), secondLevel, data)
                        setUpAdapter(threeLevelListAdapterAdapter)
                        // adapter.notifyDataSetChanged() // Adaptördeki değişiklikleri güncelle

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
    private fun setUpAdapter(threeLevelListAdapterAdapter:ThreeLevelListAdapter) {
        secondLevel.add(q1)
        secondLevel.add(q2)
        secondLevel.add(q3)
        thirdLevelq1[q1[0]] = arrayOf(des1[0], des2[0])
        thirdLevelq1[q1[1]] = des2
        thirdLevelq2[q2[0]] = des3
        thirdLevelq2[q2[1]] = des4
        thirdLevelq3[q3[0]] = des5

        data.add(thirdLevelq1)
        data.add(thirdLevelq2)
        data.add(thirdLevelq3)

        expandableListView = findViewById(R.id.expandable_listview)
        expandableListView.setAdapter(threeLevelListAdapterAdapter)
        expandableListView.setOnGroupExpandListener(object : ExpandableListView.OnGroupExpandListener {
            var previousGroup = -1

            override fun onGroupExpand(groupPosition: Int) {
                if (groupPosition != previousGroup)
                    expandableListView.collapseGroup(previousGroup)
                previousGroup = groupPosition
            }
        })
    }






}