package com.sokheang.mediaparknews.api.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sokheang.mediaparknews.api.services.ApiService
import com.sokheang.mediaparknews.models.ArticleListResponse
import com.sokheang.mediaparknews.utils.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

/**
 * Create by Sokheang RET on 19-Aug-22.
 **/
class ApiRepository @Inject constructor() {

    @Inject
    lateinit var apiService: ApiService
    private val disposable = CompositeDisposable()

    fun getArticleList(articleType: String, querySearch: String? = null,
                       fromPublishDate: String? = null, toPublishDate: String? = null,
                       searchIn: String? = null, sortBy: String? = null,
    ): LiveData<ArticleListResponse> {
        val data = MutableLiveData<ArticleListResponse>()

        disposable.add(
            apiService.getArticleList(
                articleType, querySearch = querySearch,
                fromPublishDate = fromPublishDate, toPublishDate = toPublishDate,
                searchIn = searchIn, sortBy = sortBy,
                token = Constants.ApiConstants.API_KEY)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object : DisposableSingleObserver<Response<ArticleListResponse>>() {
                        override fun onSuccess(response: Response<ArticleListResponse>) {
                            data.value = response.body()
                        }

                        override fun onError(e: Throwable) {
                            e.printStackTrace()
                        }
                    })
        )
        return data
    }


}