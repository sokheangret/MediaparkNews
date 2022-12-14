package com.sokheang.mediaparknews.di.components

import com.sokheang.mediaparknews.MainActivity
import com.sokheang.mediaparknews.di.modules.ApplicationModule
import com.sokheang.mediaparknews.di.modules.NetworkModule
import com.sokheang.mediaparknews.di.modules.RestfulModule
import com.sokheang.mediaparknews.di.modules.RoomModule
import com.sokheang.mediaparknews.ui.article_filter.ArticleFilterActivity
import com.sokheang.mediaparknews.ui.article_search_in.ArticleSearchInActivity
import com.sokheang.mediaparknews.ui.news.NewsFragment
import com.sokheang.mediaparknews.ui.search.SearchFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Create by Sokheang RET on 19-Aug-22.
 **/

@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class, RestfulModule::class, RoomModule::class])
interface ApplicationComponents {
    fun inject(activity: MainActivity)
    fun inject(activity: NewsFragment)
    fun inject(activity: SearchFragment)
    fun inject(activity: ArticleFilterActivity)
    fun inject(activity: ArticleSearchInActivity)
}