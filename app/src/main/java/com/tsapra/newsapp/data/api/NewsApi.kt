package com.tsapra.newsapp.data.api

import com.tsapra.newsapp.data.entities.NewsResponse
import com.tsapra.newsapp.utils.ConstUtils.Companion.api_key
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country")
        countryCode:String="in",
        @Query("page")
        pageNumber:Int=1,
        @Query("apikey")
        apikey:String=api_key,
    ): Response<NewsResponse>


}