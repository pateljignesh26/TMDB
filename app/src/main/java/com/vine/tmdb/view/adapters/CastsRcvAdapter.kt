package com.vine.tmdb.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.vine.api.movies.models.credits.Cast
import com.vine.api.movies.models.entitys.Movie
import com.vine.tmdb.R
import kotlinx.android.synthetic.main.rcv_cast.view.*
import kotlinx.android.synthetic.main.rcv_home.view.*
import javax.inject.Inject


class CastsRcvAdapter @Inject constructor() :
    RecyclerView.Adapter<CastsRcvAdapter.MoviesHolder>() {

    private var castList: List<Cast> = listOf()

    class MoviesHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesHolder {
        return MoviesHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.rcv_cast,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MoviesHolder, position: Int) {
        holder.itemView.tvActorRealName.text = castList[position].originalName
        holder.itemView.tvActorName.text = "As ${castList[position].character}"

        Glide.with(holder.itemView.context)
            .load("https://image.tmdb.org/t/p/w500/${castList[position].profilePath}")
            .apply(RequestOptions.circleCropTransform())
            .into(holder.itemView.ivActorImage)
    }

    override fun getItemCount(): Int {
        return castList.size
    }

    fun renderData(list: List<Cast>) {
        castList = list
        notifyDataSetChanged()
    }
}