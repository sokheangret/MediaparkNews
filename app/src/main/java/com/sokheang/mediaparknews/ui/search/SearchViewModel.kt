package com.sokheang.mediaparknews.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sokheang.mediaparknews.api.repository.ApiRepository
import com.sokheang.mediaparknews.models.ArticleListResponse
import com.sokheang.mediaparknews.room.dao.SearchHistoryDao
import com.sokheang.mediaparknews.room.data.SearchHistory
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var apiRepository: ApiRepository

    @Inject
    lateinit var searchHistoryDao: SearchHistoryDao

    val isLoading = MutableLiveData(false)
    val isZeroItemsLoaded = MutableLiveData(false)
    val isLoadFail = MutableLiveData(false)
    val showResult = MutableLiveData(false)
    val textQuery = MutableLiveData("")

    val searchResultCount = MutableLiveData("Search History")

    fun getArticleList(articleType: String, querySearch: String,
                       fromPublishDate: String?, toPublishDate: String?,
                       searchIn: String?, sortBy: String?): LiveData<ArticleListResponse> {
        return apiRepository.getArticleList(articleType, querySearch = querySearch,
            fromPublishDate = fromPublishDate, toPublishDate = toPublishDate,
            searchIn = searchIn, sortBy = sortBy,)
    }

    fun getAllHistory(): LiveData<List<SearchHistory>> {
        val data = MutableLiveData<List<SearchHistory>>()
        searchHistoryDao.getAllHistory()
            .subscribeOn(Schedulers.io())
            .subscribe { searchHistories: List<SearchHistory> ->
                data.postValue(searchHistories)
            }

        return data
    }

    fun saveHistory(histories: List<SearchHistory>): LiveData<List<Long>> {
        val data = MutableLiveData<List<Long>>()
        searchHistoryDao.saveSearchHistory(histories)
            .subscribeOn(Schedulers.io())
            .subscribe { listLong: List<Long> ->
                data.postValue(listLong)
            }
        return data
    }
}