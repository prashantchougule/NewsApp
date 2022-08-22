package com.example.newsapp.domain

import com.example.newsapp.data.model.NewsResponse
import com.example.newsapp.data.repository.NewsRepository
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(private val repository: NewsRepository) {
    suspend fun invoke(apikey: String): NewsResponse {
        return repository.getNews(apikey)
    }
}