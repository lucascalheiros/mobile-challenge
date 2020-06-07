package com.example.movieapp.view.search

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.view.MoviesListAdapter
import io.reactivex.disposables.CompositeDisposable

class SearchPaginationController(private val recyclerView: RecyclerView, private val viewModel: SearchViewModel) {

    private val compositeDisposable = CompositeDisposable()

    fun start(searchText: String) {
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

        val disposable2 = (recyclerView.adapter as MoviesListAdapter).requestPageSubject.subscribe {
            if (!waitingResponse && currentPage < totalPages) {
                waitingResponse = true
                Log.i("RRRRRRRRRRRRRRRRRRRRRRRRRRRRr","R de  requisição")
                viewModel.loadPage(currentPage + 1, searchText)
            }
        }

        compositeDisposable.add(disposable)
        compositeDisposable.add(disposable2)
        viewModel.compositeDisposable.add(disposable)
        viewModel.compositeDisposable.add(disposable2)
    }


    fun stop() {
        compositeDisposable.dispose()
    }




}