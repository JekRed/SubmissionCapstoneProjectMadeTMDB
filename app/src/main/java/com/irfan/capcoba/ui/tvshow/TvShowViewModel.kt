package com.irfan.capcoba.ui.tvshow


import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.irfan.core.domain.usecase.MoviesUseCase

class TvShowViewModel (moviesUseCase: MoviesUseCase) : ViewModel() {

    val getListTvShow = moviesUseCase.getAllTvShow().asLiveData()

}