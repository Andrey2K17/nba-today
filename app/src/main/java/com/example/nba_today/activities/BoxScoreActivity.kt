package com.example.nba_today.activities

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.example.nba_today.R
import com.example.nba_today.adapters.MyViewPagerAdapter
import com.example.nba_today.fragments.GuestBoxScoreFragment
import com.example.nba_today.fragments.HomeBoxScoreFragment
import com.example.nba_today.views.BoxScoreActivityView
import kotlinx.android.synthetic.main.activity_box_score.*

class BoxScoreActivity : MvpAppCompatActivity(), BoxScoreActivityView {

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
            HomeBoxScoreFragment.newInstance(game),
            intent.getStringExtra("right_team")
        )
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)
    }
}
