package com.irfan.favourite.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.irfan.core.domain.usecase.MoviesUseCase

class FavoriteViewModel (moviesUseCase: MoviesUseCase)  : ViewModel() {

    val getListFavoriteMovies = moviesUseCase.getListFavoriteMovies().asLiveData()

    val getListFavoriteTvShows = moviesUseCase.getListFavoriteTvShows().asLiveData()
}