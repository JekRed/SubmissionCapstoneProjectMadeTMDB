package com.irfan.capcoba.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.irfan.core.domain.usecase.MoviesUseCase

class MoviesViewModel (moviesUseCase: MoviesUseCase) : ViewModel() {

    val getListMovie = moviesUseCase.getAllMovies().asLiveData()
}