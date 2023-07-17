package com.tsapra.newsapp.repository

import com.tsapra.newsapp.data.entities.NewsResponse
import com.tsapra.newsapp.data.instance.RetrofitInstance
import retrofit2.Response

class NewsRepository {
    suspend fun getBreakingNews(countryCode:String, pageNumber:Int): Response<NewsResponse> {
        return RetrofitInstance.api.getBreakingNews(countryCode,pageNumber)

    }
}