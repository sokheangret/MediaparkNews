package com.sokheang.mediaparknews.di.modules

import android.app.Application
import androidx.room.Room
import com.sokheang.mediaparknews.room.dao.SearchHistoryDao
import com.sokheang.mediaparknews.room.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Create by Sokheang RET on 21-Aug-22.
 **/
@Module
@InstallIn(SingletonComponent::class)
object  RoomModule {

    @Provides
    fun providesRoomDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "mediapark-db").build()
    }

    @Provides
    fun providesSearchHistoryDao(appDatabase: AppDatabase): SearchHistoryDao {
        return appDatabase.searchHistoryDao()
    }
}