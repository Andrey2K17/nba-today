package com.example.nba_today.fragments

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.nba_today.R
import com.example.nba_today.adapters.BoxScoreAdapter
import com.example.nba_today.models.Score
import com.example.nba_today.presenters.BoxScorePresenter
import com.example.nba_today.views.BoxScoreFragmentView
import kotlinx.android.synthetic.main.fragment_box_score.*

class HomeBoxScoreFragment : MvpAppCompatFragment(), BoxScoreFragmentView {


    @InjectPresenter
    lateinit var boxScorePresenter: BoxScorePresenter

    private var check = 1

    companion object {
        const val ARG_NAME = "games_id"

        fun newInstance(name: String): HomeBoxScoreFragment {
            val fragment = HomeBoxScoreFragment()
            val bundle = Bundle().apply {
                putString(ARG_NAME, name)
            }
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val gameId = arguments!!.getString(GuestBoxScoreFragment.ARG_NAME)
        boxScorePresenter.scoreRequest1(gameId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_box_score, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        boxScoreRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
        }
        val gameId = arguments!!.getString(HomeBoxScoreFragment.ARG_NAME)
        boxScoreRefreshLayout.setOnRefreshListener { boxScorePresenter.scoreRequest1(gameId) }
    }

    override fun displayScore(score: List<Score>) {
        val adapter = BoxScoreAdapter(score)
        boxScoreRecyclerView.adapter = adapter
    }

    override fun displayProgressBar() {
        if (check == 1) {
            boxScoreProgressBar.visibility = View.VISIBLE
            check = 0
        }
    }

    override fun doNotDisplayProgressBar() {
        boxScoreProgressBar.visibility = View.INVISIBLE
    }

    override fun displayRefreshLayout() {
        boxScoreRefreshLayout.isRefreshing = true
    }

    override fun doNotDisplayRefreshLayout() {
        boxScoreRefreshLayout.isRefreshing = false
    }

    override fun displayGamesNotPlayed() {
        gamesNotPlayed.visibility = View.VISIBLE
    }
}
