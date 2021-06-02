package com.irfan.core.data.source.remote.network

import com.irfan.core.BuildConfig
import com.irfan.core.data.source.remote.response.ListMoviesResponse
import com.irfan.core.data.source.remote.response.ListTvShowResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService{

    @GET("movie/popular")
    suspend fun getMovies(
        @Query("api_key") apiKey: String? = BuildConfig.API_KEY
    ):ListMoviesResponse

    @GET("tv/on_the_air")
    suspend fun getTvShows(
        @Query("api_key") apiKey: String? = BuildConfig.API_KEY
    ):ListTvShowResponse

}
