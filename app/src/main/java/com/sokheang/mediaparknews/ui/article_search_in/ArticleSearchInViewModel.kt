package com.sokheang.mediaparknews.ui.article_search_in

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Create by Sokheang RET on 20-Aug-22.
 **/
@HiltViewModel
class ArticleSearchInViewModel @Inject constructor() : ViewModel() {
    val titleCheck = MutableLiveData(false)
    val descriptionCheck = MutableLiveData(false)
    val contentCheck = MutableLiveData(false)
}