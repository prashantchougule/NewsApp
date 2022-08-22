package com.example.newsapp.presentation.uistate


sealed class NewsListUIState {
    object Loading : NewsListUIState()
    data class Content(val newsList: List<NewsItemUIState>) : NewsListUIState()
    data class Error(val message: String) : NewsListUIState()
}