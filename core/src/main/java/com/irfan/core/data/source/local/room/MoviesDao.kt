package com.irfan.core.data.source.local.room

import androidx.room.*
import com.irfan.core.data.source.local.entity.MoviesEntity
import com.irfan.core.data.source.local.entity.TvShowEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {

    @Query("SELECT * FROM moviesentity")
    fun getListMovies(): Flow<List<MoviesEntity>>

    @Query("SELECT * FROM moviesentity where isFavorite = 1")
    fun getListFavoriteMovies(): Flow<List<MoviesEntity>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MoviesEntity>)

    @Update
    fun updateMovie(movies: MoviesEntity)

    @Delete
    fun deleteMovie(movies: MoviesEntity)


    @Query("SELECT * FROM tvshowentity")
    fun getListTvShows():  Flow<List<TvShowEntity>>

    @Query("SELECT * FROM tvshowentity where isFavorite = 1")
    fun getListFavoriteTvShows():  Flow<List<TvShowEntity>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvShows(tvShow: List<TvShowEntity>)

    @Update
    fun updateTvShow(tvShow: TvShowEntity)


    @Delete
    fun deleteTvShow(tvShow: TvShowEntity)


}