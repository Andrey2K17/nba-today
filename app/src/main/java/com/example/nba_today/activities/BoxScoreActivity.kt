package com.example.nba_today.activities

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.nba_today.R
import com.example.nba_today.fragments.GuestBoxScoreFragment
import com.example.nba_today.fragments.HomeBoxScoreFragment
import kotlinx.android.synthetic.main.activity_box_score.*

class BoxScoreActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_box_score)

        val game = intent.getStringExtra("games_id")

        val adapter = MyViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(
            GuestBoxScoreFragment.newInstance(game),
            intent.getStringExtra("left_team")
        )
        adapter.addFragment(
            HomeBoxScoreFragment(),
            intent.getStringExtra("right_team")
        )
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)
    }

    class MyViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {

        private val fragmentList: MutableList<Fragment> = ArrayList()
        private val titleList: MutableList<String> = ArrayList()

        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }

        override fun getCount(): Int {
            return fragmentList.size
        }

        fun addFragment(fragment: Fragment, title: String) {
            fragmentList.add(fragment)
            titleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return titleList[position]
        }
    }
}
