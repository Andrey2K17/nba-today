package com.example.nba_today.views

import com.example.nba_today.models.GameItem
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndStrategy::class)
interface GamesFragmentView : MvpView {
    fun displayGames(games: List<GameItem>)
    fun displayRefreshLayout()
    fun doNotDisplayRefreshLayout()
    fun displayProgressBar()
    fun doNotDisplayProgressBar()
    fun displayNotGameChooseDay()
}