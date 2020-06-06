package com.example.movieapp.network

import com.example.movieapp.model.Genre
import com.example.movieapp.model.Genres
import com.example.movieapp.model.Movie
import com.example.movieapp.model.Pagination
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.themoviedb.org/3/"
private const val API_KEY = "f321a808e68611f41312aa8408531476"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface MoviesApiService {
    @GET("movie/now_playing")
    fun getNowPlayingMovies(@Query("page") page: Int,
                            @Query("api_key") apiKey: String = API_KEY
    ): Single<Pagination<List<Movie>>>

    @GET("genre/movie/list")
    fun getGenres(@Query("api_key") apiKey: String = API_KEY
    ): Single<Genres<List<Genre>>>


}

object MoviesApi {
    val retrofitService: MoviesApiService by lazy {
        retrofit.create(MoviesApiService::class.java)
    }
}