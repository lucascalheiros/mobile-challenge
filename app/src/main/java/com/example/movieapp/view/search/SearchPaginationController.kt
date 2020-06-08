package com.example.movieapp.view.search

import com.example.movieapp.view.MoviesListAdapter
import io.reactivex.disposables.CompositeDisposable

class SearchPaginationController(private val adapter: MoviesListAdapter, private val viewModel: SearchViewModel) {

    private val compositeDisposable = CompositeDisposable()

    fun start(searchText: String) {
        stop()
        var currentPage = 0
        var totalPages = 1
        var waitingResponse = true

        viewModel.loadPage(1, searchText)

        val disposable = viewModel.pageSubject.subscribe( {
            currentPage = it.page
            totalPages = it.totalPages
            waitingResponse = false
        },{
            waitingResponse = false
        })

        val disposable2 = adapter.requestPageSubject.subscribe {
            if (!waitingResponse && currentPage < totalPages) {
                waitingResponse = true
                viewModel.loadPage(currentPage + 1, searchText)
            }
        }

        compositeDisposable.add(disposable)
        compositeDisposable.add(disposable2)
        viewModel.compositeDisposable.add(disposable)
        viewModel.compositeDisposable.add(disposable2)
    }


    private fun stop() {
        compositeDisposable.clear()
    }




}