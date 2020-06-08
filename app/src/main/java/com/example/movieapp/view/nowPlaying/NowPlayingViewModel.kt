package com.example.movieapp.view.nowPlaying

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.model.Genre
import com.example.movieapp.model.Genres
import com.example.movieapp.model.Movie
import com.example.movieapp.model.Pagination
import com.example.movieapp.network.MoviesApi
import com.example.movieapp.view.PaginationController
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class NowPlayingViewModel : ViewModel(), PaginationController.PageLoader<List<Movie>> {
    private val moviesService = MoviesApi.retrofitService
    override val compositeDisposable = CompositeDisposable()
    val moviesLiveData: MutableLiveData<List<Movie>> = MutableLiveData()

    override fun loadPage(page: Int, query: String): Single<Pagination<List<Movie>>> {

        val genresSingle = moviesService.getGenres()

        val moviesSingle = moviesService.getNowPlayingMovies(page)

        return Single.zip(
            genresSingle.subscribeOn(Schedulers.io()),
            moviesSingle.subscribeOn(Schedulers.io()),
            BiFunction { g: Genres<List<Genre>>, p: Pagination<List<Movie>> ->
                fillMoviePageGenres(g, p)
            })
            .observeOn(AndroidSchedulers.mainThread())
            .map { moviesPage ->
                moviesLiveData.value = moviesPage.results
                moviesPage.copy(results = listOf())
            }
    }

    private fun fillMoviePageGenres(
        genres: Genres<List<Genre>>,
        page: Pagination<List<Movie>>
    ): Pagination<List<Movie>> {
        return page.copy(results = page.results.map { result ->
            result.copy(genres = genres.genres.filter {
                it.id in result.genreIds
            })
        })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}