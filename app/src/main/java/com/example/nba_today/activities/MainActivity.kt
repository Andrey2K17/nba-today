package com.example.nba_today.activities

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import com.arellomobile.mvp.MvpAppCompatActivity
import com.example.nba_today.R
import com.example.nba_today.fragments.GamesFragment
import com.example.nba_today.fragments.MoreFragment
import com.example.nba_today.fragments.TableFragment

class MainActivity : MvpAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val fragment = GamesFragment.newInstance()
        addFragment(fragment)
    }

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_games -> {
                    addFragment(GamesFragment.newInstance())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.action_table -> {
                    addFragment(TableFragment())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.action_more -> {
                    addFragment(MoreFragment())
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
            .replace(R.id.content, fragment, fragment.javaClass.simpleName)
            .commit()
    }
}
