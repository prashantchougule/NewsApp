package com.example.newsapp.data.service

import com.example.newsapp.data.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("/v1/news")
    suspend fun getNews(@Query("access_key") accessKey:String): NewsResponse
}