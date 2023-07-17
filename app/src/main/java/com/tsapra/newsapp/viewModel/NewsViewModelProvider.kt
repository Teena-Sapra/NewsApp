package com.tsapra.newsapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.tsapra.newsapp.repository.NewsRepository
import com.tsapra.newsapp.viewModel.NewsViewModel

class NewsViewModelProvider(val newsRepository:NewsRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(newsRepository) as T
    }
}