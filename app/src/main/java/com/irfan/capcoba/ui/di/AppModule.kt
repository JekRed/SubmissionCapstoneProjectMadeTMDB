package com.irfan.capcoba.ui.di

import com.irfan.core.domain.usecase.MoviesInteractor
import com.irfan.core.domain.usecase.MoviesUseCase
import com.irfan.capcoba.ui.detail.DetailMovieViewModel
import com.irfan.capcoba.ui.movies.MoviesViewModel
import com.irfan.capcoba.ui.tvshow.TvShowViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MoviesUseCase> { MoviesInteractor(get()) }
}

val viewModelModule = module {
    viewModel { DetailMovieViewModel(get()) }
    viewModel { MoviesViewModel(get()) }
    viewModel { TvShowViewModel(get()) }
}