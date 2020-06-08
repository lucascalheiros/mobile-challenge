package com.example.movieapp.view.movieDetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.movieapp.databinding.FragmentMovieDetailBinding
import com.example.movieapp.storage.MoviesLocalService


class MovieDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMovieDetailBinding.inflate(inflater)
        binding.movie = arguments?.let {
            val movie = MovieDetailFragmentArgs.fromBundle(it).movieModel
            this.activity?.let { activity ->
                MoviesLocalService.add(movie, activity)
            }
            movie
        }
        return binding.root
    }
}