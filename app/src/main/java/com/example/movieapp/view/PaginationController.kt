package com.example.movieapp.view;

import com.example.movieapp.model.Pagination
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject


// This class maintain control of which page should be loaded, receive a load requisition, from the page requester
// and call a loadPage from the page loader
class PaginationController<T>(private val requester: PageRequester, private val loader: PageLoader<T>) {
    private val compositeDisposable = CompositeDisposable()

    fun start(query: String) {
        clear()
        var currentPage = 0
        var totalPages = 1
        var waitingResponse = true

        val initialDisposable = loader.loadPage(1, query).subscribe({
            currentPage = it.page
            totalPages = it.totalPages
            waitingResponse = false
        }, {
            waitingResponse = false
        })

        val requestListenerDisposable = requester.pageRequest.subscribe {
            if (!waitingResponse && currentPage < totalPages) {
                waitingResponse = true
                loader.loadPage(currentPage + 1, query).subscribe({
                    currentPage = it.page
                    totalPages = it.totalPages
                    waitingResponse = false
                }, {
                    waitingResponse = false
                })
            }
        }

        compositeDisposable.add(initialDisposable)
        compositeDisposable.add(requestListenerDisposable)
        // The loader is responsible for disposing the subscriptions
        loader.compositeDisposable.addAll(compositeDisposable)
    }

    fun clear() {
        compositeDisposable.clear()
    }

    interface PageLoader<T> {
        fun loadPage(page: Int, query: String): Single<Pagination<T>>
        val compositeDisposable: CompositeDisposable
    }

    interface PageRequester {
        val pageRequest: PublishSubject<Boolean>
    }

}
