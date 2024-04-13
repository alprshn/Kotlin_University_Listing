package com.example.kotlin_university_listing.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_university_listing.R
import com.example.kotlin_university_listing.data.model.RecyclerViewData

class NestedAdapter(private val mList: List<RecyclerViewData>, private val callback: NestedAdapterCallback) : RecyclerView.Adapter<NestedAdapter.NestedAdapterHolder>() {

    inner class NestedAdapterHolder(view: View): RecyclerView.ViewHolder(view) {
        val mTv: TextView = itemView.findViewById(R.id.nestedItemText)
        val linearLayout: LinearLayout = itemView.findViewById(R.id.nested_linear_layout)
        val expandableLayout: RelativeLayout = itemView.findViewById(R.id.nested_expandable_layout)
        val mArrowImage: ImageView = itemView.findViewById(R.id.nested_arro_imageview)
        val nestedRecyclerView: RecyclerView = itemView.findViewById(R.id.nested_child_rv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NestedAdapterHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.nested_item, parent, false)
        return NestedAdapterHolder(view)
    }

    override fun onBindViewHolder(holder: NestedAdapterHolder, position: Int) {
        val model: RecyclerViewData = mList[position]
        holder.mTv.text = model.itemText

        val isExpandable: Boolean = model.isExpandable
        holder.expandableLayout.visibility = if (isExpandable) View.VISIBLE else View.GONE
        holder.mArrowImage.setImageResource(if (isExpandable) R.drawable.arrow_up else R.drawable.arrow_down)

        holder.linearLayout.setOnClickListener {
            model.isExpandable = !model.isExpandable
            notifyItemChanged(holder.adapterPosition)
            callback.onNestedItemExpanded(holder.adapterPosition)
            //model.isExpandable = !model.isExpandable
            //callback.onExpandStateChanged(holder.adapterPosition, model.isExpandable)
        }

        val adapter = NestedChildAdapter(model.nestedList)
        holder.nestedRecyclerView.layoutManager = LinearLayoutManager(holder.itemView.context)
        holder.nestedRecyclerView.setHasFixedSize(true)
        holder.nestedRecyclerView.adapter = adapter


    }

    interface NestedAdapterCallback {
        fun onNestedItemExpanded(position: Int)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

}