package com.tugas2.newsfeed.data

import com.tugas2.newsfeed.model.News
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
class NewsRepository {
    private val categories = listOf("Tech", "Entertainment", "Business", "Sports")
    fun getNewsStream(): Flow<News> = flow {
        var id = 1
        while (true) {
            delay(2000)
            val cat = categories.random()
            val title = if (cat == "Entertainment") "Final Match Highlights #$id"
            else "Kotlin Coroutines Tips #$id"

            emit(News(id, title, cat))
            id++
        }
    }
}