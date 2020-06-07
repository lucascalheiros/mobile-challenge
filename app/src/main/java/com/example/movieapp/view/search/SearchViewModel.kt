package com.example.movieapp.view.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.model.Genre
import com.example.movieapp.model.Genres
import com.example.movieapp.model.Movie
import com.example.movieapp.model.Pagination
import com.example.movieapp.network.MoviesApi
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class SearchViewModel : ViewModel() {
    private val moviesService = MoviesApi.retrofitService
    val compositeDisposable = CompositeDisposable()
    val pageSubject: PublishSubject<Pagination<List<Movie>>> =
        PublishSubject.create<Pagination<List<Movie>>>()
    val moviesLiveData: MutableLiveData<List<Movie>> = MutableLiveData()

    fun loadPage(page: Int, searchText: String) {

        val genresSingle = moviesService.getGenres()

        val moviesSingle = moviesService.getSearchMovies(searchText, page)

        val disposable = Single.zip(
            genresSingle.subscribeOn(Schedulers.io()),
            moviesSingle.subscribeOn(Schedulers.io()),
            BiFunction { g: Genres<List<Genre>>, p: Pagination<List<Movie>> ->
                fillMoviePageGenres(g, p)
            })
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { page ->
                pageSubject.onNext(page.copy(results = listOf()))
                moviesLiveData.value = page.results
            }
        compositeDisposable.add(disposable)
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