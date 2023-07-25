package com.tsapra.newsapp.repository

import com.tsapra.newsapp.data.entities.Article
import com.tsapra.newsapp.data.entities.NewsResponse
import com.tsapra.newsapp.data.instance.RetrofitInstance
import com.tsapra.newsapp.database.ArticleDatabase
import retrofit2.Response

class NewsRepository( val db : ArticleDatabase){
    suspend fun getBreakingNews(countryCode:String, pageNumber:Int): Response<NewsResponse> {
        return RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)
    }
    suspend fun upsert(article: Article) = db.getArticleDao().upsert(article)
    fun getSavedNews() = db.getArticleDao().getAllArticles()

    suspend fun deleteArticle(article: Article) = db.getArticleDao().deleteArticle(article)
}