package com.example.nba_today.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.nba_today.models.GameItem

@StateStrategyType(SingleStateStrategy::class)
interface GamesFragmentView : MvpView {
    fun setGame(game: String)
    fun displpayGames(games: List<GameItem>)
}