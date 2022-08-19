package com.sokheang.mediaparknews.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sokheang.mediaparknews.api.repository.ApiRepository
import com.sokheang.mediaparknews.models.ArticleListResponse
import javax.inject.Inject

class NewsViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var apiRepository: ApiRepository

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    fun getArticleList(articleType: String): LiveData<ArticleListResponse> {
        return apiRepository.getArticleList(articleType)
    }
}