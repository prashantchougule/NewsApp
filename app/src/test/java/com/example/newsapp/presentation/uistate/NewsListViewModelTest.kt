package com.example.newsapp.presentation.uistate

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.newsapp.data.model.News
import com.example.newsapp.data.model.NewsResponse
import com.example.newsapp.data.model.Pagination
import com.example.newsapp.domain.GetNewsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class NewsListViewModelTest {
    @get:Rule
    var rule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: NewsListViewModel
    private val getNewsUsecase = mockk<GetNewsUseCase>()
    private val newsResponse =
        NewsResponse(pagination = Pagination(25, 0,3,10),newsList = (0..2).map {
            News("Auther","News Title", "http://google.com","google.com","","","general","en","UK","2022-05-01T15:38:00+00:00")
        })


    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        coEvery { getNewsUsecase.invoke("5abd24b8a45a0eb54a61fbf5549fd67e") } returns newsResponse
        viewModel = NewsListViewModel(getNewsUsecase)
    }

    @Test
    fun `Load method correctly creates the ViewState`() = runTest {
        val values = mutableListOf<NewsListUIState>()
        viewModel.viewState.observeForever{
            values.add(it)
        }
        viewModel.fetchNews()
        testDispatcher.scheduler.advanceUntilIdle()
        assert(values[0] is NewsListUIState.Loading)
        assert(values[1] ==
                NewsListUIState.Content(
                    (0..2).map {
                        NewsItemUIState("Auther","News Title", "http://google.com","google.com","","","general","en","UK","2022-05-01T15:38:00+00:00")
                    }
                ))

    }
}