package com.irfan.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class TvShow (
        val id: Int,
        val name: String,
        val originalName: String,
        val overview: String,
        val popularity: Float,
        val posterPath: String,
        val voteCount: Int,
        val voteAverage: Float,
        val isFavorite: Boolean = false,
        ): Parcelable