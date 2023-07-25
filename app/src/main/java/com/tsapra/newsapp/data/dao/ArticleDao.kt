package com.tsapra.newsapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface ArticleDao {
    @Query("SELECT * FROM articles")
    fun getAllArticles(): LiveData<List<Article>>
}