package com.tugas2.newsfeed.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tugas2.newsfeed.presentation.NewsFeedViewModel

@Composable
fun NewsFeedScreen(viewModel: NewsFeedViewModel) {
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.startFeed(scope)
    }

    val readCount by viewModel.readCount.collectAsState()
    val selectedCategory by viewModel.selectedCategory.collectAsState()
    val allNews by viewModel.allNews.collectAsState()

    val filteredNews = allNews
        .filter { selectedCategory == "All" || it.category.uppercase() == selectedCategory.uppercase() }
        .map { it.copy(displayTitle = it.title) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Live News Feed", fontWeight = FontWeight.Bold)
                },
                backgroundColor = Color(0xFF1565C0),
                contentColor = Color.White,
                elevation = 4.dp,
                actions = {
                    Box(
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xFFFFB300))
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = "Dibaca: $readCount",
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    }
                }
            )
        },
        backgroundColor = Color(0xFFF5F5F5)
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            val tabs = listOf("All", "TECH", "SPORTS", "BUSINESS", "ENTERTAINMENT")

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(vertical = 12.dp, horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(tabs) { tab ->
                    val isSelected = selectedCategory.uppercase() == tab.uppercase()

                    Surface(
                        modifier = Modifier.clickable { viewModel.setCategory(tab) },
                        shape = RoundedCornerShape(20.dp),
                        color = if (isSelected) Color(0xFF1565C0) else Color(0xFFE0E0E0),
                        elevation = if (isSelected) 4.dp else 0.dp
                    ) {
                        Text(
                            text = tab,
                            color = if (isSelected) Color.White else Color.DarkGray,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                            fontSize = 13.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(filteredNews) { news ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { viewModel.markAsReadAndFetch(news, scope) },
                        shape = RoundedCornerShape(12.dp),
                        elevation = 2.dp,
                        backgroundColor = Color.White
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(48.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(getCategoryColor(news.category)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = news.category.take(1).uppercase(),
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp
                                )
                            }

                            Spacer(modifier = Modifier.width(16.dp))

                            Column {
                                Text(
                                    text = news.category.uppercase(),
                                    color = getCategoryColor(news.category),
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.ExtraBold
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = news.displayTitle,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 16.sp,
                                    color = Color.Black
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Klik untuk membaca detail...",
                                    color = Color.Gray,
                                    fontSize = 12.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

fun getCategoryColor(category: String): Color {
    return when (category.uppercase()) {
        "TECH" -> Color(0xFF00897B)
        "ENTERTAINMENT" -> Color(0xFF8E24AA)
        "BUSINESS" -> Color(0xFFE53935)
        "SPORTS" -> Color(0xFFFF8F00)
        else -> Color(0xFF546E7A)
    }
}