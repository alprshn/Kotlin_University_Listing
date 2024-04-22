package com.example.kotlin_university_listing.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ExpandableListView
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_university_listing.R
import com.example.kotlin_university_listing.data.model.Province
import com.example.kotlin_university_listing.data.model.ProvinceResponse
import com.example.kotlin_university_listing.data.model.University
import com.example.kotlin_university_listing.data.remote.ServiceBuilder
import com.example.kotlin_university_listing.ui.adapter.ThreeLevelListAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var expandableListView: ExpandableListView

    val cityNames = mutableListOf<String>()
    val universityNames = ArrayList<Array<String>>()

  //  private val des1 = arrayOf("A layout that organizes its children into a single horizontal or vertical row. It creates a scrollbar if the length of the window exceeds the length of the screen.")
    //private val des2 = arrayOf("Enables you to specify the location of child objects relative to each other (child A to the left of child B) or to the parent (aligned to the top of the parent).")
    //private val des3 = arrayOf("This list contains linear layout information")
    //private val des4 = arrayOf("This list contains relative layout information,Displays a scrolling grid of columns and rows")
    //private val des5 = arrayOf("Under the RecyclerView model, several different components work together to display your data. Some of these components can be used in their unmodified form; for example, your app is likely to use the RecyclerView class directly. In other cases, we provide an abstract class, and your app is expected to extend it; for example, every app that uses RecyclerView needs to define its own view holder, which it does by extending the abstract RecyclerView.ViewHolder class.")

    private val thirdLevelq1 = LinkedHashMap<String, Array<String>>()


    //private val secondLevel = ArrayList<Array<String>>()
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
        expandableListView = findViewById(R.id.expandable_listview)


        FavoriteListButton()
        RequestApi()
    }


    private fun FavoriteListButton(){
        val favoriteListButton = findViewById<ImageView>(R.id.favorite_list)

        favoriteListButton.setOnClickListener {
            val intent = Intent(this, FavoriteListActivity::class.java)
            startActivity(intent)
        }
    }
    private fun RequestApi(){
        val call = ServiceBuilder.service.getProvinces()
        call.enqueue(object : Callback<ProvinceResponse> {
            override fun onResponse(call: Call<ProvinceResponse>, response: Response<ProvinceResponse>) {
                if (response.isSuccessful) {
                    val provinceResponse = response.body()
                    val provinces: List<Province> = provinceResponse!!.data
                    if (response != null) {
                        Log.e("Response Body", response.toString())

                        for (i in 0..provinces.size-1 ) {
                            //Log.e("University", provinces[i].province)
                            cityNames.add(provinces[i].province)
                            val universitiesInProvince = provinces[i].universities.map { it.name }.toTypedArray()
                            universityNames.add(universitiesInProvince)


                            for (university in provinces[i].universities) {
                                val universityInfo = arrayOf(
                                    "Phone: ${university.phone}",
                                    "Fax: ${university.fax}",
                                    "Website: ${university.website}",
                                    "Email: ${university.email}",
                                    "Address: ${university.address}",
                                    "Rector: ${university.rector}"
                                )

                                // Üniversite adını anahtar olarak kullanarak üçüncü seviye verilerini ekleyin
                                thirdLevelq1[university.name] = universityInfo

                            }
                            data.add(thirdLevelq1)

                        }
                        val threeLevelListAdapterAdapter = ThreeLevelListAdapter(applicationContext, cityNames.toTypedArray(), universityNames, data)
                        setUpAdapter(threeLevelListAdapterAdapter)
                        threeLevelListAdapterAdapter.notifyDataSetChanged() // Adaptördeki değişiklikleri güncelle

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
       // secondLevel.add(q1)
        //secondLevel.add(q2)
        //secondLevel.add(q3)
        //thirdLevelq1[q1[0]] = arrayOf(des1[0], des2[0])
        //thirdLevelq1[q1[1]] = des2
       // thirdLevelq2[q2[0]] = des3
       // thirdLevelq2[q2[1]] = des4
       // thirdLevelq3[q3[0]] = des5

        //data.add(thirdLevelq1)
        //data.add(thirdLevelq2)
        //data.add(thirdLevelq3)

        Log.e("deneme",expandableListView.toString())
        expandableListView.setOnGroupClickListener(object :ExpandableListView.OnGroupClickListener{
            override fun onGroupClick(
                p0: ExpandableListView?,
                p1: View?,
                p2: Int,
                p3: Long
            ): Boolean {
                Log.e("deneme","başardın")

                return false

            }

        })

       // expandableListView.selectedPosition



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