package com.sokheang.mediaparknews.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Create by Sokheang RET on 19-Aug-22.
 **/
@HiltAndroidApp
class MediaparkNewsApp : Application() {
//    lateinit var applicationComponents: ApplicationComponents
//    override fun onCreate() {
//        super.onCreate()
//
//        //Build Dagger application components
//        applicationComponents = DaggerApplicationComponents.builder()
//            .networkModule(NetworkModule())
//            .restfulModule(RestfulModule())
//            .roomModule(RoomModule(this))
//            .applicationModule(ApplicationModule(this))
//            .build()
//    }
//
//    fun getApplicationComponent(): ApplicationComponents {
//        return applicationComponents
//    }
}