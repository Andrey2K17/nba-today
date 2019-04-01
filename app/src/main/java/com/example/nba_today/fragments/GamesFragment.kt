package com.example.nba_today.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.nba_today.R
import com.example.nba_today.adapters.GamesRecyclerAdapter
import com.example.nba_today.common.getDateForPosition
import com.example.nba_today.models.GameItem
import com.example.nba_today.presenters.GamesPresenter
import com.example.nba_today.views.GamesFragmentView
import kotlinx.android.synthetic.main.fragment_games.*
import java.text.SimpleDateFormat
import java.util.*

class GamesFragment : MvpAppCompatFragment(), GamesFragmentView {

    @InjectPresenter
    lateinit var gamesPresenter: GamesPresenter
    private var position = 0

    companion object {
        @JvmStatic
        fun newInstance(position: Int): GamesFragment {
            val fragmentGames = GamesFragment()
            val args = Bundle()
            args.putInt("Data", position)
            fragmentGames.arguments = args
            return fragmentGames
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        position = arguments!!.getInt("Data")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gamesRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
        }
        gamesRefreshLayout.setOnRefreshListener { gamesPresenter.gamesRequest(showSelectedDate()) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_games, container, false)
    }

    override fun onResume() {
        super.onResume()
        gamesPresenter.gamesRequest(showSelectedDate())
    }

    override fun displayGames(games: List<GameItem>) {
        val adapter = GamesRecyclerAdapter(games)
        gamesRecyclerView.adapter = adapter
    }

    override fun displayRefreshLayout() {
        gamesRefreshLayout.isRefreshing = true
    }

    override fun doNotDisplayRefreshLayout() {
        gamesRefreshLayout.isRefreshing = false
    }

    override fun displayProgressBar() {
        gamesProgressBar.visibility = View.VISIBLE
    }

    override fun doNotDisplayProgressBar() {
        gamesProgressBar.visibility = View.INVISIBLE
    }

    override fun displayNotGameChooseDay() {
        noGamesToday.visibility = View.VISIBLE
    }

    @SuppressLint("SimpleDateFormat")
    private fun showSelectedDate(): String {
        val date = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("MM.dd.yy")
        date.timeInMillis = getDateForPosition(position)
        return dateFormat.format(date.time)
    }
}
