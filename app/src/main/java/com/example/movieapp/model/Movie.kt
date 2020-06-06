package com.example.movieapp.model

data class Pagination<T>(
    val results: T
)

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val release_date: String,
    val original_language: String,
    var genres: List<Genre> = listOf<Genre>(),
    val genre_ids: List<Int> = listOf<Int>(),
    val poster_path: String? = null
)

data class Genres<T>(
    val genres: T
)

data class Genre(
    val id: Int,
    val name: String
)