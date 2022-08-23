package com.example.newsapp.presentation.uistate

import androidx.lifecycle.*
import com.example.newsapp.BuildConfig.API_KEY
import com.example.newsapp.domain.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject
@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase
    ): ViewModel() {

    private val _selectedNewsItem = MutableLiveData<NewsItemUIState>()
    val selectedNewsItem: LiveData<NewsItemUIState> get() = _selectedNewsItem

    fun selectNews(news: NewsItemUIState) {
        _selectedNewsItem.value = news
    }
    private val _viewState = MutableLiveData<NewsListUIState>()
    val viewState: LiveData<NewsListUIState>
        get() = _viewState

    fun fetchNews() {
        viewModelScope.launch {
            _viewState.postValue(NewsListUIState.Loading)
            try {
                // Data call to fetch news
                val newsResponse = getNewsUseCase.invoke(API_KEY)
                    newsResponse.let {
                    val news = it.newsList
                    _viewState.postValue(NewsListUIState.Content(
                        news.map {
                            NewsItemUIState(
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
                            )
                        }
                    ))
                }
            }catch (ex: Exception){
                _viewState.postValue(NewsListUIState.Error(handleExcpetion(ex)))
            }
        }
    }
    private fun handleExcpetion(ex: Exception): String{
        //We can handle all exceptions separately here
        return ex.message?: "Error occurred"
    }
}
