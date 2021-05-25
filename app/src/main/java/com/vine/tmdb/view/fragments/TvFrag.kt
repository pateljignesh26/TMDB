package com.vine.tmdb.view.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.vine.tmdb.R
import com.vine.tmdb.data.hide
import com.vine.tmdb.data.show
import com.vine.tmdb.view.adapters.HomeTvAdapter
import com.vine.tmdb.viewmodel.TvHomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_tv.*
import kotlinx.android.synthetic.main.fragment_tv.popularLayout
import javax.inject.Inject

@AndroidEntryPoint
class TvFrag @Inject constructor() : Fragment() {

    val viewModel: TvHomeViewModel by viewModels()

    @Inject
    lateinit var topRatedTvAdapter: HomeTvAdapter;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tv, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
getTopRated()
    }

    private fun getTopRated() {
        viewModel.topRatedTv.observe(requireActivity(), {
            topRatedTvRcv.apply {
                layoutManager = LinearLayoutManager(
                    requireActivity(),
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
                adapter = topRatedTvAdapter
                if (it.results.isNotEmpty()) {
                    popularLayout.show()
                    topRatedTvAdapter.renderData(it.results)
                } else {
                    popularLayout.hide()
                }
                mainTvLayout.show()
                loadingBarTv.hide()
            }
        })
    }
}