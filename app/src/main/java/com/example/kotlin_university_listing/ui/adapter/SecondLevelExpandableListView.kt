package com.example.kotlin_university_listing.ui.adapter

import android.content.Context
import android.widget.ExpandableListView


class SecondLevelExpandableListView(context: Context) : ExpandableListView(context) {
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val customHeightMeasureSpec = MeasureSpec.makeMeasureSpec(999999, MeasureSpec.AT_MOST)
        super.onMeasure(widthMeasureSpec, customHeightMeasureSpec)
    }
}