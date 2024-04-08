package com.example.kotlin_university_listing.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_university_listing.R

class NestedAdapter(private val mList:List<String>) : RecyclerView.Adapter<NestedAdapter.NestedAdapterHolder>(){
    inner class NestedAdapterHolder(view: View):RecyclerView.ViewHolder(view) {
        val mTv: TextView = itemView.findViewById(R.id.nestedItemText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NestedAdapter.NestedAdapterHolder {
        var view:View = LayoutInflater.from(parent.context).inflate(R.layout.nested_item, parent, false)
        return NestedAdapterHolder(view)
    }

    override fun onBindViewHolder(holder: NestedAdapter.NestedAdapterHolder, position: Int) {
        holder.mTv.text = mList[position]
    }

    override fun getItemCount(): Int {
    return mList.size
    }

}