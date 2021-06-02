package com.irfan.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListTvShowResponse(
    @SerializedName("results")
    val result: List<TvShowResponse>
)