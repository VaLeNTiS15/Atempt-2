package com.example.definition.presentation.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.definition.presentation.favorite.FavoriteFragment
import com.example.definition.presentation.search.SearchFragment


class PagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> SearchFragment()
            else -> FavoriteFragment()
        }
    }

    override fun getItemCount(): Int = 2

}