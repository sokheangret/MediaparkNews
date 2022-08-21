package com.sokheang.mediaparknews.utils

/**
 * Create by Sokheang RET on 20-Aug-22.
 **/
object Constants {
    object ApiConstants {
        const val BASE_URL = "https://gnews.io/"
        const val API_KEY = "05c9aaed2df1e4e915813bbc95f6a6f0"
    }

    object ArticleTypeConstants {
        const val SEARCH = "search"
        const val TOP_HEAD_LINE = "top-headlines"
    }

    object IntentConstants {
        const val FROM_PUBLISH_DATE = "FROM_PUBLISH_DATE"
        const val TO_PUBLISH_DATE   = "TO_PUBLISH_DATE"
        const val SEARCH_IN   = "SEARCH_IN"
        const val ARTICLE_URL   = "ARTICLE_URL"
    }

    object SearchInConstants {
        const val TITLE = "title"
        const val DESCRIPTION = "description"
        const val CONTENT = "content"
    }

    object SortByConstants {
        const val PUBLISH_AT = "publishedAt"
        const val RELEVANCE = "relevance"
    }
}