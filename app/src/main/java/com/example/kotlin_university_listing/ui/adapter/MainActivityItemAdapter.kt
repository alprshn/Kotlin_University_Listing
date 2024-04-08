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
import com.example.kotlin_university_listing.data.model.RecyclerViewData

class MainActivityItemAdapter(private val mList: List<RecyclerViewData>): RecyclerView.Adapter<MainActivityItemAdapter.MainActivityItemDesignHolder>(){

    inner class MainActivityItemDesignHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val linearLayout: LinearLayout = itemView.findViewById(R.id.linear_layout)
        val expandableLayout: RelativeLayout = itemView.findViewById(R.id.expandable_layout)
        val mTextView: TextView = itemView.findViewById(R.id.itemTv)
        val mArrowImage: ImageView = itemView.findViewById(R.id.arro_imageview)
        val nestedRecyclerView: RecyclerView = itemView.findViewById(R.id.child_rv)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainActivityItemDesignHolder {
    var view:View = LayoutInflater.from(parent.context).inflate(R.layout.each_item, parent, false)
        return MainActivityItemDesignHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: MainActivityItemDesignHolder, position: Int) {
        var model: RecyclerViewData = mList[position]
        holder.mTextView.text = model.itemText

        var isExpandable:Boolean = model.isExpandable
        holder.expandableLayout.visibility = if (isExpandable) View.VISIBLE else View.GONE
        if (isExpandable){
            holder.mArrowImage.setImageResource(R.drawable.arrow_up)
        }
        else{
            holder.mArrowImage.setImageResource(R.drawable.arrow_down)
        }

        holder.linearLayout.setOnClickListener {
            model.isExpandable = !model.isExpandable
        }
    }
}