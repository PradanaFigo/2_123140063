package com.tugas2.newsfeed

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.tugas2.newsfeed.presentation.NewsFeedViewModel
import com.tugas2.newsfeed.ui.NewsFeedScreen

@Composable
fun App() {
    MaterialTheme {
        val viewModel = remember { NewsFeedViewModel() }
        NewsFeedScreen(viewModel = viewModel)
    }
}