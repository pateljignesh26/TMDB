package com.vine.tmdb.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.vine.tmdb.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

@AndroidEntryPoint
class HomeFrag : Fragment() {

    @Inject
    lateinit var fragDashBoardFrag: DashBoardFrag

    @Inject
    lateinit var fragTvFrag: TvFrag

    @Inject
    lateinit var fragSportFrag: SportFrag

    @Inject
    lateinit var fragNewsFrag: NewsFrag


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setCurrentFragment(fragDashBoardFrag)
        clickBottomBar()
    }

    private fun clickBottomBar() {
        bottomBar.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> setCurrentFragment(fragDashBoardFrag)
                R.id.tv -> setCurrentFragment(fragTvFrag)
                R.id.sports -> setCurrentFragment(fragSportFrag)
                R.id.news -> setCurrentFragment(fragNewsFrag)

            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) {
        val manager: FragmentManager = childFragmentManager
        manager.beginTransaction().apply {
            replace(R.id.mainFragment, fragment)
            commit()
        }
    }

}