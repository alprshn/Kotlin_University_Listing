package com.example.kotlin_university_listing.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_university_listing.R

class NestedAdapter(private val mList:List<String>) : RecyclerView.Adapter<NestedAdapter.NestedAdapterHolder>(){
    inner class NestedAdapterHolder(view: View):RecyclerView.ViewHolder(view) {
        val mTv: TextView = itemView.findViewById(R.id.nestedItemText)
        val linearLayout: LinearLayout = itemView.findViewById(R.id.nested_linear_layout)
        val expandableLayout: RelativeLayout = itemView.findViewById(R.id.nested_expandable_layout)
        val mArrowImage: ImageView = itemView.findViewById(R.id.nested_arro_imageview)
        val nestedRecyclerView: RecyclerView = itemView.findViewById(R.id.nested_child_rv)
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