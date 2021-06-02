package com.irfan.core.domain.usecase


import com.irfan.core.domain.model.Movies
import com.irfan.core.domain.model.TvShow
import com.irfan.core.domain.repository.MoviesDataRepository

class MoviesInteractor(private val tourismRepository: MoviesDataRepository): MoviesUseCase {

    override fun getAllMovies() = tourismRepository.getAllMovies()

    override fun getListFavoriteMovies() = tourismRepository.getListFavoriteMovies()

    override fun setFavoriteMovie(moviesId: Movies, state: Boolean) = tourismRepository.setFavoriteMovie(moviesId, state)

    override fun getAllTvShow() = tourismRepository.getAllTvShow()

    override fun getListFavoriteTvShows() = tourismRepository.getListFavoriteTvShows()

    override fun setFavoriteTvShow(tvShowId: TvShow, state: Boolean) = tourismRepository.setFavoriteTvShow(tvShowId, state)


}