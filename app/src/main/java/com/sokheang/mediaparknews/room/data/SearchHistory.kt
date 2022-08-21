package com.sokheang.mediaparknews.room.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Create by Sokheang RET on 21-Aug-22.
 **/

//Set up table not allow to add same history in db
@Entity(tableName = "search_history", indices = [Index(value = ["history"], unique = true)])
data class SearchHistory (
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "history") val history: String
)