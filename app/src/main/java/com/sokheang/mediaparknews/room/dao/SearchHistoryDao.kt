package com.sokheang.mediaparknews.room.dao

import androidx.room.*
import com.sokheang.mediaparknews.room.data.SearchHistory
import io.reactivex.Single

/**
 * Create by Sokheang RET on 21-Aug-22.
 **/
@Dao
interface SearchHistoryDao {
    //Order by id desc to get last history just saved
    @Query("SELECT * FROM search_history order by id desc")
    fun getAllHistory(): Single<List<SearchHistory>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveSearchHistory(histories: List<SearchHistory>): Single<List<Long>>

    /**
     * To delete one or more products.
     * Returns number of rows deleted. 0 if no row deleted.
     */
    @Delete
    fun deleteSearchHistory(history: SearchHistory): Single<Int>
}