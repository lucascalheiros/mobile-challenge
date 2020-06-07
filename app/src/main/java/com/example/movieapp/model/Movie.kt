package com.example.movieapp.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

private const val BASE_IMG_URL = "https://image.tmdb.org/t/p"

data class Pagination<T>(
    val results: T,
    val page: Int,
    @Json(name = "total_pages") val totalPages: Int,
    @Json(name = "total_results") val totalResults: Int
)

@Parcelize
data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    @Json(name = "release_date") val releaseDate: String,
    @Json(name = "original_language") val originalLanguage: String,
    var genres: List<Genre> = listOf<Genre>(),
    @Json(name = "genre_ids") val genreIds: List<Int> = listOf<Int>(),
    @Json(name = "poster_path") val posterPath: String? = null
): Parcelable {
    val imgUrl: String?
        get() {return  if (posterPath != null) "$BASE_IMG_URL/w500$posterPath" else null}

    val genresStr: String
        get() {return genres.joinToString { it.name }
        }
}

data class Genres<T>(
    val genres: T
)

@Parcelize
data class Genre(
    val id: Int,
    val name: String
): Parcelable