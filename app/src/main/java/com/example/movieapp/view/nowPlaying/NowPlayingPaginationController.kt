package com.example.movieapp.view.nowPlaying

import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.view.MoviesListAdapter
import io.reactivex.disposables.CompositeDisposable

class NowPlayingPaginationController(private val recyclerView: RecyclerView, private val viewModel: NowPlayingViewModel) {

    private val compositeDisposable = CompositeDisposable()

    fun start() {
        var currentPage = 1
        var totalPages = -1
        var waitingResponse = true

        viewModel.loadPage(1)

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
                viewModel.loadPage(currentPage + 1)
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