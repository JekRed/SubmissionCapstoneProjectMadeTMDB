package com.irfan.core.domain.usecase

import com.irfan.core.data.Resource
import com.irfan.core.domain.model.Movies
import com.irfan.core.domain.model.TvShow
import kotlinx.coroutines.flow.Flow

interface MoviesUseCase {
    fun getAllMovies(): Flow<Resource<List<Movies>>>
    fun getListFavoriteMovies(): Flow<List<Movies>>
    fun setFavoriteMovie(moviesId: Movies, state: Boolean)

    fun getAllTvShow(): Flow<Resource<List<TvShow>>>
    fun getListFavoriteTvShows(): Flow<List<TvShow>>
    fun setFavoriteTvShow(tvShowId: TvShow, state: Boolean)
}