package com.irfan.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.irfan.core.data.source.local.entity.MoviesEntity
import com.irfan.core.data.source.local.entity.TvShowEntity

@Database(entities = [MoviesEntity::class, TvShowEntity::class],
    version = 1,
    exportSchema = false)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
}