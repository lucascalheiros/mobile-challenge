package com.example.movieapp.view.nowPlaying

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentNowPlayingBinding
import com.example.movieapp.model.Movie
import com.example.movieapp.view.MoviesListAdapter

class NowPlayingFragment : Fragment() {

    private val listClickAction: (Movie, View) -> Unit = { movie, view ->
        val action =
            NowPlayingFragmentDirections.actionNowPlayingFragmentToMovieDetailFragment(movie)
        view.findNavController().navigate(action)
    }
    private val nowPlayingViewModel = NowPlayingViewModel()
    private val adapter = MoviesListAdapter(listClickAction)
    private val paginationController = NowPlayingPaginationController(adapter, nowPlayingViewModel)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        paginationController.start()
        nowPlayingViewModel.moviesLiveData.observe(this,
            Observer<List<Movie>> { movies ->
                adapter.add(movies)
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentNowPlayingBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_now_playing, container, false
        )
        val recyclerView = binding.nowPlayingRecycler
        recyclerView.layoutManager = LinearLayoutManager(this.activity)
        recyclerView.adapter = adapter

        return binding.root
    }

}
