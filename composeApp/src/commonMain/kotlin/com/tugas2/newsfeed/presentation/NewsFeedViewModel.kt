package com.tugas2.newsfeed.presentation

import com.tugas2.newsfeed.data.NewsRepository
import com.tugas2.newsfeed.model.News
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class NewsFeedViewModel(private val repository: NewsRepository = NewsRepository()) {

    private val _readCount = MutableStateFlow(0)
    val readCount: StateFlow<Int> = _readCount.asStateFlow()

    private val _selectedCategory = MutableStateFlow("All")
    val selectedCategory: StateFlow<String> = _selectedCategory.asStateFlow()

    private val _allNews = MutableStateFlow<List<News>>(emptyList())
    val allNews: StateFlow<List<News>> = _allNews.asStateFlow()

    fun startFeed(scope: CoroutineScope) {
        repository.getNewsStream()
            .onEach { news ->
                _allNews.value = listOf(news) + _allNews.value
            }
            .launchIn(scope)
    }

    fun setCategory(category: String) {
        _selectedCategory.value = category
    }

    fun markAsReadAndFetch(news: News, scope: CoroutineScope) {
        scope.launch {
            val detailDeferred = async(Dispatchers.Default) {
                delay(1000)
                "Detail content downloaded"
            }
            detailDeferred.await()

            _readCount.value += 1
        }
    }
}