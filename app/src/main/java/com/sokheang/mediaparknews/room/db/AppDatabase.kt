package com.sokheang.mediaparknews.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sokheang.mediaparknews.room.dao.SearchHistoryDao
import com.sokheang.mediaparknews.room.data.SearchHistory

/**
 * Create by Sokheang RET on 21-Aug-22.
 **/

@Database(entities = [SearchHistory::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun searchHistoryDao(): SearchHistoryDao
}