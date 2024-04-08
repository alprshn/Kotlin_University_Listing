package com.example.kotlin_university_listing.data.model

data class RecyclerViewData(
    var nestedList: List<String>,
    var itemText: String,
    var isExpandable: Boolean
)