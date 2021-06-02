package com.irfan.core.utils

import com.irfan.core.data.source.local.entity.MoviesEntity
import com.irfan.core.data.source.local.entity.TvShowEntity
import com.irfan.core.data.source.remote.response.MoviesResponse
import com.irfan.core.data.source.remote.response.TvShowResponse
import com.irfan.core.domain.model.Movies
import com.irfan.core.domain.model.TvShow

object DataMapper {

    fun mapMoviesEntitiesToDomain(input: List<MoviesEntity>): List<Movies> =
            input.map {
                Movies(
                        id = it.id,
                        title = it.title,
                        originalTitle = it.originalTitle,
                        overview = it.overview,
                        popularity = it.popularity,
                        posterPath = it.posterPath,
                        voteCount = it.voteCount,
                        voteAverage = it.voteAverage,
                        releaseDate = it.releaseDate,
                        isFavorite = it.isFavorite
                )
            }

    fun mapTvShowEntitiesToDomain(input: List<TvShowEntity>): List<TvShow> =
            input.map {
                TvShow(
                        id = it.id,
                        name = it.name,
                        originalName = it.originalName,
                        overview = it.overview,
                        popularity = it.popularity,
                        posterPath = it.posterPath,
                        voteCount = it.voteCount,
                        voteAverage = it.voteAverage,
                        isFavorite = it.isFavorite
                )
            }

    fun mapMoviesDomainToEntity(input: Movies) = MoviesEntity(
            id = input.id,
            title = input.title,
            originalTitle = input.originalTitle,
            overview = input.overview,
            popularity = input.popularity,
            posterPath = input.posterPath,
            voteCount = input.voteCount,
            voteAverage = input.voteAverage,
            releaseDate = input.releaseDate,
            isFavorite = input.isFavorite
    )


    fun mapTvShowDomainToEntity(input: TvShow) = TvShowEntity(
            id = input.id,
            name = input.name,
            originalName = input.originalName,
            overview = input.overview,
            popularity = input.popularity,
            posterPath = input.posterPath,
            voteCount = input.voteCount,
            voteAverage = input.voteAverage,
            isFavorite = input.isFavorite
    )


        fun mapMoviesResponsesToEntities(input: List<MoviesResponse>): List<MoviesEntity> {
                val moviesList = ArrayList<MoviesEntity>()
                input.map {
                        val movies = MoviesEntity(
                                id = it.id,
                                title = it.title,
                                originalTitle = it.originalTitle,
                                overview = it.overview,
                                popularity = it.popularity,
                                posterPath = it.posterPath,
                                voteCount = it.voteCount,
                                voteAverage = it.voteAverage,
                                releaseDate = it.releaseDate,
                                isFavorite = false
                        )
                        moviesList.add(movies)
                }
                return moviesList
        }

        fun mapTvShowResponsesToEntities(input: List<TvShowResponse>): List<TvShowEntity> {
                val tvShowList = ArrayList<TvShowEntity>()
                input.map {
                        val tvShow = TvShowEntity(
                                id = it.id,
                                name = it.name,
                                originalName = it.originalName,
                                overview = it.overview,
                                popularity = it.popularity,
                                posterPath = it.posterPath,
                                voteCount = it.voteCount,
                                voteAverage = it.voteAverage,
                                isFavorite = false
                        )
                        tvShowList.add(tvShow)
                }
                return tvShowList
        }
}