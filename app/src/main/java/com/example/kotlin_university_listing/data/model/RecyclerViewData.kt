package com.example.kotlin_university_listing.data.model

data class RecyclerViewData(
    var nestedList: List<String> = emptyList(),
    var itemText: String,
    var isExpandable: Boolean = false,
    var nestedViewList: List<RecyclerViewData> = emptyList()
)