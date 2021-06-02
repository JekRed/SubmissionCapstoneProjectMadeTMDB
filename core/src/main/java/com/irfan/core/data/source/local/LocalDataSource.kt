package com.irfan.core.data.source.local

import com.irfan.core.data.source.local.entity.MoviesEntity
import com.irfan.core.data.source.local.entity.TvShowEntity
import com.irfan.core.data.source.local.room.MoviesDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val mMoviesDao: MoviesDao){


    fun getListAllMovies():Flow<List<MoviesEntity>> = mMoviesDao.getListMovies()

    fun getListFavoriteMovies(): Flow<List<MoviesEntity>> = mMoviesDao.getListFavoriteMovies()


    suspend fun insertMovies(movies: List<MoviesEntity>) = mMoviesDao.insertMovies(movies)


    fun setFavoriteMovie(movies: MoviesEntity, newState: Boolean) {
        movies.isFavorite = newState
        mMoviesDao.updateMovie(movies)
    }


    fun getListAllTvShow(): Flow<List<TvShowEntity>> = mMoviesDao.getListTvShows()

    fun getListFavoriteTvShows():Flow<List<TvShowEntity>> = mMoviesDao.getListFavoriteTvShows()


    suspend fun insertTvShows(tvShowList: List<TvShowEntity>) = mMoviesDao.insertTvShows(tvShowList)


    fun setFavoriteTvShow(tvShows: TvShowEntity, newState: Boolean) {
        tvShows.isFavorite = newState
        mMoviesDao.updateTvShow(tvShows)
    }

}