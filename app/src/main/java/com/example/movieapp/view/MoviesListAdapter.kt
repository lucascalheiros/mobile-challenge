package com.example.movieapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.databinding.ListItemMovieBinding
import com.example.movieapp.model.Movie
import com.example.movieapp.view.nowPlaying.NowPlayingFragmentDirections
import io.reactivex.subjects.PublishSubject

class MoviesListAdapter(val onClickCallback: (movie: Movie, view: View) -> Unit) : RecyclerView.Adapter<MoviesListAdapter.ViewHolder>() {

    val requestPageSubject: PublishSubject<Boolean> = PublishSubject.create<Boolean>()

    var movies: MutableList<Movie> = mutableListOf<Movie>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun clear() {
        movies.clear()
        notifyDataSetChanged()
    }

    fun add(newMovies: List<Movie>) {
        movies.addAll(newMovies)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = movies[position]
        holder.bind(item)
        if (position + 10 > itemCount) {
            requestPageSubject.onNext(true)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item_movie, parent, false)
        return ViewHolder(view, onClickCallback)
    }


    class ViewHolder(itemView: View, val onClickCallback: (movie: Movie, view: View) -> Unit) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val biding: ListItemMovieBinding = ListItemMovieBinding.bind(itemView)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(movie: Movie) {
            biding.movie = movie
            biding.executePendingBindings()
        }

        override fun onClick(view: View?) {
            val movie = biding.movie
            movie?.let {
                view?.let {
                    onClickCallback(movie, view)

                }
            }
        }
    }
}