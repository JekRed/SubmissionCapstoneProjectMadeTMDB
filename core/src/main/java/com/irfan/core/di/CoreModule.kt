package com.irfan.core.di

import androidx.room.Room
import com.irfan.core.BuildConfig
import com.irfan.core.data.MoviesRepository
import com.irfan.core.data.source.local.LocalDataSource
import com.irfan.core.data.source.local.room.MoviesDatabase
import com.irfan.core.data.source.remote.RemoteDataSource
import com.irfan.core.data.source.remote.network.ApiService
import com.irfan.core.domain.repository.MoviesDataRepository
import com.irfan.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<MoviesDatabase>().moviesDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            MoviesDatabase::class.java, "Tourism.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<MoviesDataRepository> { MoviesRepository(get(), get(), get()) }
}