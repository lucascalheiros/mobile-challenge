package com.example.movieapp.storage

import android.app.Activity
import android.content.Context
import android.util.Log
import com.example.movieapp.model.Movie
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.lang.reflect.Type

private const val MOVIES_KEY = "MOVIES"

object MoviesLocalService {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val type: Type = Types.newParameterizedType(MutableSet::class.java, Movie::class.java)

    private val adapter: JsonAdapter<MutableSet<Movie>> = moshi.adapter(type)


    fun add(movie: Movie, activity: Activity) {
        val sharedPreferences = activity.getSharedPreferences(MOVIES_KEY, Context.MODE_PRIVATE)
        val moviesJson = sharedPreferences.getString(MOVIES_KEY, "[]")
        var movies: MutableSet<Movie>
        try {
            movies = adapter.fromJson(moviesJson) ?: mutableSetOf<Movie>()
            movies.add(movie)
        } catch (e: Exception) {
            movies = mutableSetOf<Movie>(movie)
        }
        val newMoviesJson = adapter.toJson(movies)
        with (sharedPreferences.edit()) {
            putString(MOVIES_KEY, newMoviesJson)
            commit()
        }
    }

    fun list(activity: Activity): MutableSet<Movie> {
        val sharedPreferences = activity.getSharedPreferences(MOVIES_KEY, Context.MODE_PRIVATE)
        val moviesJson = sharedPreferences.getString(MOVIES_KEY, "[]")?: "[]"
        val movies: MutableSet<Movie>
        movies = try {
            adapter.fromJson(moviesJson) ?: mutableSetOf<Movie>()
        } catch (e: Exception) {
            mutableSetOf<Movie>()
        }
        return movies
    }

    fun clear(activity: Activity) {
        val sharedPreferences = activity.getSharedPreferences(MOVIES_KEY, Context.MODE_PRIVATE)
        with (sharedPreferences.edit()) {
            putString(MOVIES_KEY, "[]")
            commit()
        }
    }

}