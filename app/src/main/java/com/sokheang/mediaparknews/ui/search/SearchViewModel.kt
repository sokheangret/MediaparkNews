package com.sokheang.mediaparknews.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sokheang.mediaparknews.api.repository.ApiRepository
import com.sokheang.mediaparknews.models.ArticleListResponse
import javax.inject.Inject

class SearchViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var apiRepository: ApiRepository

    val isLoading = MutableLiveData(true)
    val isZeroItemsLoaded = MutableLiveData(false)
    val isLoadFail = MutableLiveData(false)

    val searchResultCount = MutableLiveData("Search History")

    fun getArticleList(articleType: String, querySearch: String,
                       fromPublishDate: String?, toPublishDate: String?,
                       searchIn: String?, sortBy: String?): LiveData<ArticleListResponse> {
        return apiRepository.getArticleList(articleType, querySearch = querySearch,
            fromPublishDate = fromPublishDate, toPublishDate = toPublishDate,
            searchIn = searchIn, sortBy = sortBy,)
    }
}