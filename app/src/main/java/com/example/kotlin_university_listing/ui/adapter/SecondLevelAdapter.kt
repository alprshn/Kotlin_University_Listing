package com.example.kotlin_university_listing.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.example.kotlin_university_listing.R

class SecondLevelAdapter(private val context: Context, private val headers: Array<String>, private val data: List<Array<String>>) : BaseExpandableListAdapter()  {
    override fun getGroupCount(): Int {
        return headers.size
    }

    override fun getChildrenCount(p0: Int): Int {
        var children: Array<String>
        children = data.get(p0)
        return children.size
    }

    override fun getGroup(p0: Int): Any {
        return headers[p0]
    }

    override fun getChild(p0: Int, p1: Int): Any {
        var childData: Array<String>
        childData = data.get(p0)
        return childData[p1]
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
        val view = p2 ?: inflater.inflate(R.layout.row_second, null)
        val text = view.findViewById<TextView>(R.id.rowSecondText)
        val groupText = getGroup(p0).toString()
        text.text = groupText
        return view
    }

    override fun getChildView(p0: Int, p1: Int, p2: Boolean, p3: View?, p4: ViewGroup?): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = p3 ?: inflater.inflate(R.layout.row_third, null)

        val textView = view.findViewById<TextView>(R.id.rowThirdText)

        val childArray = data[p0]
        val text = childArray[p1]

        textView.text = text

        return view
    }

    override fun isChildSelectable(p0: Int, p1: Int): Boolean {
        return true
    }
}