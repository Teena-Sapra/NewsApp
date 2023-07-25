package com.tsapra.newsapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tsapra.newsapp.data.entities.NewsResponse
import com.tsapra.newsapp.repository.NewsRepository
import com.tsapra.newsapp.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(val newsRepository: NewsRepository): ViewModel(){
    val breakingNews : MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    val breakingNewsPage=1
    val searchNews:MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    val searchNewsPage=1
    init{
        getBreakingNews("in")
    }
    fun getBreakingNews(countryCode:String)=viewModelScope.launch{
        breakingNews.postValue(Resource.Loading())
        val response=newsRepository.getBreakingNews(countryCode,breakingNewsPage)
        breakingNews.postValue(handlerBreakingNewsResponse(response))
    }
    private fun handlerBreakingNewsResponse(response: Response<NewsResponse>):Resource<NewsResponse>{
        if (response.isSuccessful){
            response.body()?.let{resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())

    }
}


