package com.irfan.core.data


import com.irfan.core.domain.repository.MoviesDataRepository
import com.irfan.core.data.source.local.LocalDataSource
import com.irfan.core.data.source.remote.RemoteDataSource
import com.irfan.core.data.source.remote.network.ApiResponse
import com.irfan.core.data.source.remote.response.MoviesResponse
import com.irfan.core.data.source.remote.response.TvShowResponse
import com.irfan.core.domain.model.Movies
import com.irfan.core.domain.model.TvShow
import com.irfan.core.utils.AppExecutors
import com.irfan.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MoviesRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : MoviesDataRepository {


    override fun getAllMovies(): Flow<Resource<List<Movies>>> =
        object : NetworkBoundResource<List<Movies>, List<MoviesResponse>>() {
             override fun loadFromDB(): Flow<List<Movies>> {
                     return localDataSource.getListAllMovies().map { DataMapper.mapMoviesEntitiesToDomain(it)
                 }
            }

            override fun shouldFetch(data: List<Movies>?): Boolean =
                    data == null || data.isEmpty()

             override suspend fun createCall(): Flow<ApiResponse<List<MoviesResponse>>> =
                    remoteDataSource.getAllMovies()

            override suspend fun saveCallResult(data: List<MoviesResponse>) {
                val moviesDetail =  DataMapper.mapMoviesResponsesToEntities(data)
                localDataSource.insertMovies(moviesDetail)
            }
        }.asFlow()




    override fun getAllTvShow(): Flow<Resource<List<TvShow>>> =
         object : NetworkBoundResource<List<TvShow>, List<TvShowResponse>>() {
            override fun loadFromDB(): Flow<List<TvShow>> {
                return localDataSource.getListAllTvShow().map { DataMapper.mapTvShowEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<TvShow>?): Boolean =
                    data == null || data.isEmpty()

             override suspend fun createCall(): Flow<ApiResponse<List<TvShowResponse>>> =
                    remoteDataSource.getAllTvShow()

             override suspend fun saveCallResult(data: List<TvShowResponse>) {
                val tvShowList = DataMapper.mapTvShowResponsesToEntities(data)
                localDataSource.insertTvShows(tvShowList)
            }
        }.asFlow()





    override fun getListFavoriteMovies(): Flow<List<Movies>> {
            return localDataSource.getListFavoriteMovies().map {
                DataMapper.mapMoviesEntitiesToDomain(it)
            }
        }

    override fun getListFavoriteTvShows(): Flow<List<TvShow>> {
        return localDataSource.getListFavoriteTvShows().map {
            DataMapper.mapTvShowEntitiesToDomain(it)
        }
    }

    override fun setFavoriteMovie(moviesId: Movies, state: Boolean) {
        val moviesEntity = DataMapper.mapMoviesDomainToEntity(moviesId)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(moviesEntity, state) }
    }

    override fun setFavoriteTvShow(tvShowId: TvShow, state: Boolean) {
        val tvShowEntity = DataMapper.mapTvShowDomainToEntity(tvShowId)
        appExecutors.diskIO().execute { localDataSource.setFavoriteTvShow(tvShowEntity, state) }
    }

}