package com.example.runnershi_develop.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.runnershi_develop.HomeFragment
import com.example.runnershi_develop.MyProfileFragment
import com.example.runnershi_develop.RankFragment
import com.example.runnershi_develop.RecordFragment

const val HOME_PAGE_INDEX = 0
const val RECORD_PAGE_INDEX = 1
const val RANK_PAGE_INDEX = 2
const val MY_PAGE_PAGE_INDEX = 3


class HomeViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    /**
     * Mapping of the ViewPager page indexes to their respective Fragments
     */
    private val bottomNavigationFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        HOME_PAGE_INDEX to { HomeFragment() },
        RECORD_PAGE_INDEX to { RecordFragment() },
        RANK_PAGE_INDEX to { RankFragment() },
        MY_PAGE_PAGE_INDEX to { MyProfileFragment() }
    )

    override fun getItemCount() = bottomNavigationFragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        return bottomNavigationFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}