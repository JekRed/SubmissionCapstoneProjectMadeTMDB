package com.irfan.core.data.source.remote


import android.util.Log
import com.irfan.core.data.source.remote.network.ApiResponse
import com.irfan.core.data.source.remote.network.ApiService
import com.irfan.core.data.source.remote.response.MoviesResponse
import com.irfan.core.data.source.remote.response.TvShowResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception

class RemoteDataSource(private val apiService: ApiService) {


    suspend fun getAllMovies(): Flow<ApiResponse<List<MoviesResponse>>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getMovies()
                val dataArray = response.result
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.result))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }


    suspend fun getAllTvShow() : Flow<ApiResponse<List<TvShowResponse>>> {

        //get data from remote api
        return flow {
            try {
                val response = apiService.getTvShows()
                val dataArray = response.result
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.result))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }


}