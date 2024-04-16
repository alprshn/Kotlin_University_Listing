package com.example.kotlin_university_listing.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.TextView
import com.example.kotlin_university_listing.R

class ThreeLevelListAdapter(private val context: Context, private val parentHeaders:Array<String>, private val secondLevel: List<Array<String>>,private val data: List<LinkedHashMap<String, Array<String>>>): BaseExpandableListAdapter() {
    override fun getGroupCount(): Int {
        return parentHeaders.size
    }

    override fun getChildrenCount(p0: Int): Int {
       return 1
    }

    override fun getGroup(p0: Int): Any {
       return p0
    }

    override fun getChild(p0: Int, p1: Int): Any {
        return p1
    }

    override fun getGroupId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getChildId(p0: Int, p1: Int): Long {
        return p1.toLong()
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    override fun getGroupView(p0: Int, p1: Boolean, p2: View?, p3: ViewGroup?): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = p2 ?: inflater.inflate(R.layout.row_first, null)
        val text = view.findViewById<TextView>(R.id.rowParentText)
        text.text = parentHeaders[p0]
        return view
    }

    override fun getChildView(p0: Int, p1: Int, p2: Boolean, p3: View?, p4: ViewGroup?): View {
        val secondLevelELV = SecondLevelExpandableListView(context)
        val headers = secondLevel[p0]
        val childData = ArrayList<Array<String>>()
        val secondLevelData = data[p0]
        for (key in secondLevelData.keys) {
            childData.add(secondLevelData[key]!!)
        }
        secondLevelELV.setAdapter(SecondLevelAdapter(context, headers, childData))
        secondLevelELV.setGroupIndicator(null)
        secondLevelELV.setOnGroupExpandListener(object : ExpandableListView.OnGroupExpandListener {
            var previousGroup = -1
            override fun onGroupExpand(groupPosition: Int) {
                if (groupPosition != previousGroup) secondLevelELV.collapseGroup(previousGroup)
                previousGroup = groupPosition
            }
        })

        secondLevelELV.setOnChildClickListener { parent, v, groupPosition, childPosition, id ->
            Log.e("deneme", "Başarı ile Tıklandı")
            true // İşlemin başarılı olduğunu belirtmek için true döndürün
        }
        return secondLevelELV
    }

    override fun isChildSelectable(p0: Int, p1: Int): Boolean {
        return true
    }
}