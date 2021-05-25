package com.vine.tmdb.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vine.api.tv.model.entitys.TvShows
import com.vine.tmdb.R
import com.vine.tmdb.data.listener.onClickDashBoardMovies
import kotlinx.android.synthetic.main.rcv_home.view.*
import javax.inject.Inject


class HomeTvAdapter @Inject constructor() :
    RecyclerView.Adapter<HomeTvAdapter.MoviesHolder>() {

    private var tvList: List<TvShows> = listOf()
    private lateinit var listener: onClickDashBoardMovies

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
        holder.itemView.tvMovieName.text = tvList[position].name
        Glide.with(holder.itemView.context)
            .load("https://image.tmdb.org/t/p/w500/${tvList[position].posterPath}")
            .into(holder.itemView.ivMovie)

        holder.itemView.setOnClickListener {
//            listener.onClickMovie(tvList[position])
        }
    }

    override fun getItemCount(): Int {
        return tvList.size
    }

    fun renderData(list: List<TvShows>) {
        tvList = list
        notifyDataSetChanged()
    }

    fun setOnClickListener(listener: onClickDashBoardMovies) {
        this.listener = listener
    }
}