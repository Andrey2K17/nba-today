package com.example.nba_today.activities

import android.os.Bundle
import com.example.nba_today.R
import com.example.nba_today.adapters.MyViewPagerAdapter
import com.example.nba_today.databinding.ActivityBoxScoreBinding
import com.example.nba_today.fragments.GuestBoxScoreFragment
import com.example.nba_today.fragments.HomeBoxScoreFragment
import com.example.nba_today.views.BoxScoreActivityView
import moxy.MvpAppCompatActivity

class BoxScoreActivity : MvpAppCompatActivity(), BoxScoreActivityView {
    private lateinit var binding: ActivityBoxScoreBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBoxScoreBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_box_score)

        val game = intent.getStringExtra("games_id")!!

        val adapter = MyViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(
            GuestBoxScoreFragment.newInstance(game),
            intent.getStringExtra("left_team")!!
        )
        adapter.addFragment(
            HomeBoxScoreFragment.newInstance(game),
            intent.getStringExtra("right_team")!!
        )
        binding.viewPager.adapter = adapter
        binding.tabs.setupWithViewPager(binding.viewPager)
    }
}
