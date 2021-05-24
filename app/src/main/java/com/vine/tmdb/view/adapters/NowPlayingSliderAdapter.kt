package com.vine.tmdb.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.vine.api.movies.models.entitys.Movie
import com.vine.tmdb.R
import com.vine.tmdb.data.listener.onClickDashBoardMovies
import kotlinx.android.synthetic.main.now_playing_slider_adapter.view.*
import java.util.*
import javax.inject.Inject


class NowPlayingSliderAdapter @Inject constructor() : PagerAdapter() {

    private var movieList: List<Movie> = listOf()
    private lateinit var listener: onClickDashBoardMovies

    override fun getCount(): Int {
        return movieList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object` as LinearLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemView: View = LayoutInflater.from(container.context).inflate(
            R.layout.now_playing_slider_adapter,
            container,
            false
        )

        Glide.with(container.context)
            .load("https://image.tmdb.org/t/p/w500/${movieList[position].backdropPath}")
            .into(itemView.sliderImage)

        Glide.with(container.context)
            .load("https://image.tmdb.org/t/p/w500/${movieList[position].posterPath}")
            .apply(RequestOptions.circleCropTransform())
            .into(itemView.circlePosterImage)

        itemView.tvMovieName.text = movieList[position].title

        itemView.setOnClickListener {
            listener.onClickMovie(movieList[position])
        }

        Objects.requireNonNull(container).addView(itemView)

        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }

    fun renderData(list: List<Movie>) {
        movieList = list
        notifyDataSetChanged()
    }

    fun setOnClickListener(listener: onClickDashBoardMovies) {
        this.listener = listener
    }
}