package com.sokheang.mediaparknews.app

import android.app.Application
import com.sokheang.mediaparknews.di.components.ApplicationComponents
import com.sokheang.mediaparknews.di.components.DaggerApplicationComponents
import com.sokheang.mediaparknews.di.modules.ApplicationModule
import com.sokheang.mediaparknews.di.modules.NetworkModule
import com.sokheang.mediaparknews.di.modules.RestfulModule
import com.sokheang.mediaparknews.di.modules.RoomModule

/**
 * Create by Sokheang RET on 19-Aug-22.
 **/
class MediaparkNewsApp : Application() {
    lateinit var applicationComponents: ApplicationComponents
    override fun onCreate() {
        super.onCreate()

        //Build Dagger application components
        applicationComponents = DaggerApplicationComponents.builder()
            .networkModule(NetworkModule())
            .restfulModule(RestfulModule())
            .roomModule(RoomModule(this))
            .applicationModule(ApplicationModule(this))
            .build()
    }

    fun getApplicationComponent(): ApplicationComponents {
        return applicationComponents
    }
}