package com.example.movieapp.view.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentSearchBinding
import com.example.movieapp.model.Movie
import com.example.movieapp.view.MoviesListAdapter

class SearchFragment: Fragment() {
    var listClickAction: (Movie, View) -> Unit = {
            movie, view ->
        val action = SearchFragmentDirections.actionSearchFragmentToMovieDetailFragment(movie)
        view.findNavController().navigate(action)
    }
    private val searchViewModel = SearchViewModel()
    private val adapter = MoviesListAdapter(listClickAction)


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: FragmentSearchBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_search, container, false)

        val recyclerView = binding.searchRecycler
        recyclerView.layoutManager = LinearLayoutManager(this.activity)
        recyclerView.adapter = adapter

        val paginationController = SearchPaginationController(recyclerView, searchViewModel)

        binding.searchButton.setOnClickListener {
            Log.i("+++++++++++++++++++++++++++++", binding.searchBar.text.toString())
            adapter.clear()
            paginationController.start(binding.searchBar.text.toString())

        }

        searchViewModel.moviesLiveData.observe(this,
            Observer<List<Movie>> { movies->
                Log.i("+++++++++++++++++++++++++++++",movies.toString())

                adapter.add(movies)}
        )

        return binding.root
    }
}

