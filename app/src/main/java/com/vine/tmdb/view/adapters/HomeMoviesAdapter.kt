package com.vine.tmdb.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vine.api.movies.models.entitys.Movie
import com.vine.tmdb.R
import kotlinx.android.synthetic.main.rcv_home.view.*
import javax.inject.Inject


class HomeMoviesAdapter @Inject constructor() :
    RecyclerView.Adapter<HomeMoviesAdapter.MoviesHolder>() {

    private var movieList: List<Movie> = listOf()

    class MoviesHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesHolder {
        return MoviesHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.rcv_home,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MoviesHolder, position: Int) {
        holder.itemView.tvMovieName.text = movieList[position].originalTitle
        Glide.with(holder.itemView.context)
            .load("https://image.tmdb.org/t/p/w500/${movieList[position].posterPath}")
            .into(holder.itemView.ivMovie)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    fun renderData(list: List<Movie>) {
        movieList = list
        notifyDataSetChanged()
    }
}