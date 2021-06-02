package com.irfan.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tvshowentity")
class TvShowEntity (

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "originalName")
    var originalName: String,

    @ColumnInfo(name = "overview")
    var overview: String,

    @ColumnInfo(name = "popularity")
    var popularity: Float,

    @ColumnInfo(name = "posterPath")
    var posterPath: String,

    @ColumnInfo(name = "voteCount")
    var voteCount: Int,

    @ColumnInfo(name = "voteAverage")
    var voteAverage: Float,


    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false,
        )