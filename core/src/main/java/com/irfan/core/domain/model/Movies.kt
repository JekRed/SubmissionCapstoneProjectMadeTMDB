package com.irfan.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Movies (
    val id: Int,
    val title: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Float,
    val posterPath: String,
    val voteCount: Int,
    val voteAverage: Float,
    val releaseDate: String,
    val isFavorite: Boolean = false,
):Parcelable