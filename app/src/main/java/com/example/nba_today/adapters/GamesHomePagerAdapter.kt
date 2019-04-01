package com.example.nba_today.adapters

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.example.nba_today.fragments.GamesFragment

class GamesHomePagerAdapter(
    val context: Context,
    fm: FragmentManager
) : FragmentStatePagerAdapter(fm) {

    companion object {
        // Number of pages available for pagination. The center page corresponds to today so there
        // would be 250 pages (days) to the left and 250 pages (days) to the right.
        const val NUM_PAGES = 5001
    }

    override fun getItem(position: Int): Fragment {
        return GamesFragment.newInstance(position)
    }

    override fun getCount() = NUM_PAGES
}