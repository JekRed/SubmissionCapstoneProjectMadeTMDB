package com.irfan.capcoba.ui.detail


import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.irfan.core.domain.model.Movies
import com.irfan.core.domain.model.TvShow
import com.irfan.core.domain.usecase.MoviesUseCase


class DetailMovieViewModel (private val moviesUseCase: MoviesUseCase) : ViewModel() {

    fun setFavoriteMovie(movieId: Movies, newStatus:Boolean ) =
        moviesUseCase.setFavoriteMovie(movieId, newStatus)

    fun setFavoriteTvShow(tvShowId: TvShow, newStatus:Boolean ) =
        moviesUseCase.setFavoriteTvShow(tvShowId, newStatus)

    val getListMovie = moviesUseCase.getAllMovies().asLiveData()

}