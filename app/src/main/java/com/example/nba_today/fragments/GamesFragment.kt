package com.example.nba_today.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.nba_today.R
import com.example.nba_today.presenters.GamesPresenter
import com.example.nba_today.views.GamesFragmentView
import kotlinx.android.synthetic.main.fragment_games.*

class GamesFragment : MvpAppCompatFragment(), GamesFragmentView {

    @InjectPresenter
    lateinit var gamesPresenter: GamesPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gamesPresenter.gamesRequest()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_games, container, false)
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
        gamesId.setText(game)
    }
}
