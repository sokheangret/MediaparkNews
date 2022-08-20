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
        @Query("q") querySearch: String? = null,            //This is the purpose of your search. Articles returned are filtered by the given keywords.
        @Query("from") fromPublishDate: String? = null,     //Keep articles with a publication date greater than or equal to the given date. ISO 8601 format (e.g. 2022-08-20T01:10:52Z)
        @Query("to") toPublishDate: String? = null,         //Keep articles with a publication date less than or equal to the given date. ISO 8601 format (e.g. 2022-08-20T01:10:52Z)
        @Query("in") searchIn: String? = null,              //Set the attributes of the articles in which your query will be searched. The attributes are title, description and content. You can specify several attributes by separating them with commas (e.g. title,description)
        @Query("sortby") sortBy: String? = null,            //Set the order in which the items are sorted. (publishedAt: sort articles first according to the most recent date of publication, relevance: sort articles that most closely match the query)
        @Query("lang") lang: String = "en",
    ): Single<Response<ArticleListResponse>>
}