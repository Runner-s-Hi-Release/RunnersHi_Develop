package com.example.runnershi_develop

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.example.runnershi_develop.adapters.HomeViewPagerAdapter
import com.example.runnershi_develop.databinding.FragmentViewPagerBinding


class HomeViewPagerFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        val viewPager = binding.homeViewPager
        val bottomNavigationView = binding.bottomNavigationView

        viewPager.adapter = HomeViewPagerAdapter(this)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.tabHome ->viewPager.currentItem=0
                R.id.tabRecord ->viewPager.currentItem=1
                R.id.tabRank ->viewPager.currentItem=2
                R.id.tabMypage ->viewPager.currentItem=3
            }
            true
        }

        return binding.root
    }
}