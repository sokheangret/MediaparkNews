package com.sokheang.mediaparknews.models

import com.google.gson.annotations.SerializedName

/**
 * Create by Sokheang RET on 19-Aug-22.
 **/
data class ArticleListResponse(
    @SerializedName("articles")
    val articles: List<Article>,
    @SerializedName("totalArticles")
    val totalArticles: Int) {
    data class Article(
        @SerializedName("content")
        val content: String,
        @SerializedName("description")
        val description: String,
        @SerializedName("image")
        val image: String,
        @SerializedName("publishedAt")
        val publishedAt: String,
        @SerializedName("source")
        val source: Source,
        @SerializedName("title")
        val title: String,
        @SerializedName("url")
        val url: String
    ) {
        data class Source(
            @SerializedName("name")
            val name: String,
            @SerializedName("url")
            val url: String
        )
    }
}