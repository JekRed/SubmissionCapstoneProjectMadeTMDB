package com.irfan.core.domain.repository

import com.irfan.core.data.Resource
import com.irfan.core.domain.model.Movies
import com.irfan.core.domain.model.TvShow
import kotlinx.coroutines.flow.Flow

interface MoviesDataRepository {

    fun getAllMovies(): Flow<Resource<List<Movies>>>
    fun getAllTvShow() : Flow<Resource<List<TvShow>>>

    fun getListFavoriteMovies(): Flow<List<Movies>>
    fun getListFavoriteTvShows(): Flow<List<TvShow>>

    fun setFavoriteMovie(moviesId: Movies, state: Boolean)
    fun setFavoriteTvShow(tvShowId: TvShow, state: Boolean)

}