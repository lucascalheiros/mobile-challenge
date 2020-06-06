package com.example.movieapp.model

import com.squareup.moshi.Json

data class Pagination<T>(
    val results: T,
    val page: Int,
    @Json(name = "total_pages") val totalPages: Int,
    @Json(name = "total_results") val totalResults: Int
)

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    @Json(name = "release_date") val releaseDate: String,
    @Json(name = "original_language") val originalLanguage: String,
    var genres: List<Genre> = listOf<Genre>(),
    @Json(name = "genre_ids") val genreIds: List<Int> = listOf<Int>(),
    @Json(name = "poster_path") val posterPath: String? = null
)

data class Genres<T>(
    val genres: T
)

data class Genre(
    val id: Int,
    val name: String
)