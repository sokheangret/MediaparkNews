package com.sokheang.mediaparknews.di.modules

import android.app.Application
import androidx.room.Room
import com.sokheang.mediaparknews.room.dao.SearchHistoryDao
import com.sokheang.mediaparknews.room.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Create by Sokheang RET on 21-Aug-22.
 **/
@Module
class RoomModule @Inject constructor(application: Application){

    private val appDatabase = Room.databaseBuilder(application, AppDatabase::class.java, "mediapark-db").build()

    @Singleton
    @Provides
    fun providesRoomDatabase(): AppDatabase {
        return appDatabase
    }

    @Singleton
    @Provides
    fun providesSearchHistoryDao(appDatabase: AppDatabase): SearchHistoryDao {
        return appDatabase.searchHistoryDao()
    }
}