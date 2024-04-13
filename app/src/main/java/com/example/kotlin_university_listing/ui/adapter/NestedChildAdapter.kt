package com.example.kotlin_university_listing.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_university_listing.R

class NestedChildAdapter(private val mList: List<String>) : RecyclerView.Adapter<NestedChildAdapter.NestedChildAdapterHolder>() {
    inner class NestedChildAdapterHolder(view: View): RecyclerView.ViewHolder(view) {
        val mTv: TextView = itemView.findViewById(R.id.nestedChildItemText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NestedChildAdapterHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.nested_child_item, parent, false)
        return NestedChildAdapterHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: NestedChildAdapterHolder, position: Int) {
        holder.mTv.text = mList[position]
    }
}