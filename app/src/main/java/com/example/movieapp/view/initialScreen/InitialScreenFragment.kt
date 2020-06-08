package com.example.movieapp.view.initialScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.movieapp.databinding.FragmentInitialScreenBinding


class InitialScreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentInitialScreenBinding.inflate(inflater)
        binding.toPlayingBtn.setOnClickListener {
            it.findNavController().navigate(InitialScreenFragmentDirections.actionInitialScreenToNowPlayingFragment())
        }
        binding.toSearchBtn.setOnClickListener {
            it.findNavController().navigate(InitialScreenFragmentDirections.actionInitialScreenToSearchFragment())
        }
        return binding.root
    }
}
