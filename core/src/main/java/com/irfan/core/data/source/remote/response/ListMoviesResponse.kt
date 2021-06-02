package com.irfan.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListMoviesResponse(

    @SerializedName("results")
    val result: List<MoviesResponse>
)