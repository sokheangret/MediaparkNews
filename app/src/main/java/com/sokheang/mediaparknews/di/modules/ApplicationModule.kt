package com.sokheang.mediaparknews.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Create by Sokheang RET on 19-Aug-22.
 **/
@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    fun provideContext(application: Application): Context = application.applicationContext

}