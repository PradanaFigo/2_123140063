package com.tugas2.newsfeed.model
data class News(
    val id: Int,
    val title: String,
    val category: String,
    val displayTitle: String = ""
)