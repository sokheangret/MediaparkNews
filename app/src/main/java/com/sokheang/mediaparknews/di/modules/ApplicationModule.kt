package com.sokheang.mediaparknews.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

/**
 * Create by Sokheang RET on 19-Aug-22.
 **/
@Module
class ApplicationModule constructor(application: Application){
    private val  context: Context
    init {
        context = application.applicationContext
    }

    @Provides
    fun provideContext(): Context = context

}