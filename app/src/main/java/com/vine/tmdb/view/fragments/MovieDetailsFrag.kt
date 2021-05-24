package com.vine.tmdb.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.vine.api.movies.models.entitys.Movie
import com.vine.tmdb.R
import com.vine.tmdb.data.hide
import com.vine.tmdb.data.show
import com.vine.tmdb.view.adapters.CastsRcvAdapter
import com.vine.tmdb.view.adapters.CrewsRcvAdapter
import com.vine.tmdb.viewmodel.MovieDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_details.*
import javax.inject.Inject

@AndroidEntryPoint
class MovieDetailsFrag @Inject constructor() : Fragment() {

    private lateinit var selectedMovie: Movie

    @Inject
    lateinit var castsRcvAdapter: CastsRcvAdapter

    @Inject
    lateinit var crewsRcvAdapter: CrewsRcvAdapter

    val viewModel: MovieDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            selectedMovie = it.getSerializable("selectedMovie") as Movie
            tvSelectedItemName.text = selectedMovie.title
            loadMovie()
        }


        clickListener()
    }

    private fun clickListener() {
        icBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun loadMovie() {
        Glide.with(requireActivity())
            .load("https://image.tmdb.org/t/p/w500/${selectedMovie.backdropPath}")
            .into(moviePosterImage)

        movieNameTv.text = selectedMovie.title

        viewModel.getMovieDetails(selectedMovie.id).observe(requireActivity(), {
            tvReview.text = it.overview

            if (it.voteAverage > 5)
                ratingTv.setTextColor(ContextCompat.getColor(requireActivity(), R.color.yellow))
            else
                ratingTv.setTextColor(ContextCompat.getColor(requireActivity(), R.color.red))

            ratingTv.text = it.voteAverage.toString()
        })

        viewModel.getMovieCredits(selectedMovie.id).observe(requireActivity(), {

            if (it.cast.isNotEmpty()) {
                castLayout.show()
                castRcv.apply {
                    layoutManager = LinearLayoutManager(
                        requireActivity(),
                        LinearLayoutManager.HORIZONTAL,
                        false
                    )

                    adapter = castsRcvAdapter
                    castsRcvAdapter.renderData(it.cast)
                }

            } else {
                castLayout.hide()
            }

            if (it.crew.isNotEmpty()) {
                crewLayout.show()
                crewRcv.apply {
                    layoutManager = LinearLayoutManager(
                        requireActivity(),
                        LinearLayoutManager.HORIZONTAL,
                        false
                    )

                    adapter = crewsRcvAdapter
                    crewsRcvAdapter.renderData(it.crew)
                }

            } else {
                castLayout.hide()
            }
        })


    }
}