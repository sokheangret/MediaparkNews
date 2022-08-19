package com.sokheang.mediaparknews.api.services

import com.sokheang.mediaparknews.models.ArticleListResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Create by Sokheang RET on 19-Aug-22.
 **/
interface ApiService {

    @GET("api/v4/{articleType}")
    fun getArticleList(
        @Path("articleType") articleType: String,
        @Query("token") token: String,
        @Query("lang") lang: String = "en",
    ): Single<Response<ArticleListResponse>>
}