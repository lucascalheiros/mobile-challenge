package com.example.movieapp.view.visualized

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentVisualizedBinding
import com.example.movieapp.model.Movie
import com.example.movieapp.storage.MoviesLocalService
import com.example.movieapp.view.MoviesListAdapter

class VisualizedFragment : Fragment() {
    private val listClickAction: (Movie, View) -> Unit = { movie, view ->
        val action =
            VisualizedFragmentDirections.actionVisualizedFragmentToMovieDetailFragment(movie)
        view.findNavController().navigate(action)
    }
    private val adapter = MoviesListAdapter(listClickAction)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentVisualizedBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_visualized, container, false
        )
        val recyclerView = binding.visualizedRecycler
        recyclerView.layoutManager = LinearLayoutManager(this.activity)
        recyclerView.adapter = adapter
        this.activity?.let { activity ->
            val movies = MoviesLocalService.list(activity)
            adapter.clear()
            adapter.add(movies.toList())
        }
        binding.clearBtn.setOnClickListener {
            this.activity?.let { activity ->
                MoviesLocalService.clear(activity)
            }
            adapter.clear()
        }

        return binding.root
    }

}