package com.example.nba_today.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.nba_today.models.GameItem

@StateStrategyType(AddToEndStrategy::class)
interface GamesFragmentView : MvpView {
    fun displayGames(games: List<GameItem>)
    fun displayRefreshLayout()
    fun doNotDisplayRefreshLayout()
    fun displayProgressBar()
    fun doNotDisplayProgressBar()
    fun displayNotGameChooseDay()
}