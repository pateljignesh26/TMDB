package com.vine.tmdb.view.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.vine.api.movies.models.entitys.Movie
import com.vine.tmdb.R
import com.vine.tmdb.data.hide
import com.vine.tmdb.data.listener.onClickDashBoardMovies
import com.vine.tmdb.data.show
import com.vine.tmdb.view.adapters.HomeMoviesAdapter
import com.vine.tmdb.view.adapters.NowPlayingSliderAdapter
import com.vine.tmdb.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_dashboard.*
import javax.inject.Inject

@AndroidEntryPoint
class DashBoardFrag @Inject constructor() : Fragment() {

    private val viewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var topRatedAdapter: HomeMoviesAdapter

    @Inject
    lateinit var popularAdapter: HomeMoviesAdapter

    @Inject
    lateinit var upcomingAdapter: HomeMoviesAdapter

    @Inject
    lateinit var nowPlaying: NowPlayingSliderAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateTopRatedRcv()
        updatePopularRcv()
        updateUpcomingRcv()
        updateNowPlayingSlider()
        clickListener()
    }

    private fun updateTopRatedRcv() {
        topRatedRcv.apply {
            layoutManager = LinearLayoutManager(
                requireActivity(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = topRatedAdapter
        }

        viewModel.topRate.observe(requireActivity(), {
            if (it.results.isNotEmpty()) {
                topRatedLayout.show()
                topRatedAdapter.renderData(it.results)
            } else {
                topRatedLayout.hide()
            }
        })
    }

    private fun updatePopularRcv() {
        popularRcv.apply {
            layoutManager = LinearLayoutManager(
                requireActivity(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = popularAdapter
        }

        viewModel.poplar.observe(requireActivity(), {
            if (it.results.isNotEmpty()) {
                popularLayout.show()
                popularAdapter.renderData(it.results)
            } else {
                popularLayout.hide()
            }
        })
    }

    private fun updateUpcomingRcv() {
        upcomingRcv.apply {
            layoutManager = LinearLayoutManager(
                requireActivity(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = upcomingAdapter
        }

        viewModel.upcoming.observe(requireActivity(), {
            if (it.results.isNotEmpty()) {
                upcomingLayout.show()
                upcomingAdapter.renderData(it.results)
            } else {
                upcomingLayout.hide()
            }
            mainMovieLayout.show()
            loadingBar.hide()
        })
    }

    private fun updateNowPlayingSlider() {
        nowPlayingSlider.adapter = nowPlaying
        viewModel.nowPlaying.observe(requireActivity(), {
            if (it.results.isNotEmpty()) {
                nowPlayingSlider.show()
                nowPlaying.renderData(it.results)
            } else {
                nowPlayingSlider.hide()
            }
        })
    }

    private fun clickListener() {
        nowPlaying.setOnClickListener(object : onClickDashBoardMovies {
            override fun onClickMovie(movie: Movie) {
                val bundle = bundleOf("selectedMovie" to movie)
                findNavController().navigate(
                    R.id.action_homeFrag_to_movieDetailsFrag,
                    bundle
                )
            }

        })

        upcomingAdapter.setOnClickListener(object : onClickDashBoardMovies {
            override fun onClickMovie(movie: Movie) {
                val bundle = bundleOf("selectedMovie" to movie)
                findNavController().navigate(
                    R.id.action_homeFrag_to_movieDetailsFrag,
                    bundle
                )
            }

        })

        popularAdapter.setOnClickListener(object : onClickDashBoardMovies {
            override fun onClickMovie(movie: Movie) {
                val bundle = bundleOf("selectedMovie" to movie)
                findNavController().navigate(
                    R.id.action_homeFrag_to_movieDetailsFrag,
                    bundle
                )
            }

        })

        topRatedAdapter.setOnClickListener(object : onClickDashBoardMovies {
            override fun onClickMovie(movie: Movie) {
                val bundle = bundleOf("selectedMovie" to movie)
                findNavController().navigate(
                    R.id.action_homeFrag_to_movieDetailsFrag,
                    bundle
                )
            }

        })
    }
}