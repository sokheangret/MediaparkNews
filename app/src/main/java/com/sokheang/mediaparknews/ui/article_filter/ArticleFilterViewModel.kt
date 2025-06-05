package com.sokheang.mediaparknews.ui.article_filter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


/**
 * Create by Sokheang RET on 20-Aug-22.
 **/
@HiltViewModel
class ArticleFilterViewModel @Inject constructor() : ViewModel() {

    val fromDate = MutableLiveData<String>()
    val toDate = MutableLiveData<String>()
    val searchIn = MutableLiveData<String>()
}