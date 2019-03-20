package com.example.nba_today.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.nba_today.R
import com.example.nba_today.adapters.GamesRecyclerAdapter
import com.example.nba_today.models.GameItem
import com.example.nba_today.presenters.GamesPresenter
import com.example.nba_today.views.GamesFragmentView
import kotlinx.android.synthetic.main.fragment_games.*

class GamesFragment : MvpAppCompatFragment(), GamesFragmentView {

    @InjectPresenter
    lateinit var gamesPresenter: GamesPresenter
    private var day = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gamesPresenter.getDate(day)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_back.setOnClickListener { gamesPresenter.getDate(--day) }
        btn_forward.setOnClickListener { gamesPresenter.getDate(++day) }
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_games, container, false)

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(): GamesFragment {
            val fragmentGames = GamesFragment()
            val args = Bundle()
            fragmentGames.arguments = args
            return fragmentGames
        }
    }

    override fun setGame(game: String) {
        //gamesId.setText(game)
    }

    override fun displpayGames(games: List<GameItem>) {
        val adapter = GamesRecyclerAdapter(games)
        recyclerView.adapter = adapter
    }
}
