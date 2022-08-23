package com.example.newsapp.data.mapper

import com.example.newsapp.data.model.NewsResponse
import com.example.newsapp.presentation.uistate.NewsItemUIState

object NewsMapper {
    fun map(newResponse: NewsResponse):List<NewsItemUIState> =
        newResponse.newsList.map { NewsItemUIState(
            it.author,
            it.title,
            it.description,
            it.url,
            it.source,
            it.image,
            it.category,
            it.language,
            it.country,
            it.published_at
        ) }

}