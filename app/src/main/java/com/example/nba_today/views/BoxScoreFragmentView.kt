package com.example.nba_today.views

import com.example.nba_today.models.Score
import moxy.MvpView
import moxy.viewstate.strategy.SingleStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(SingleStateStrategy::class)
interface BoxScoreFragmentView : MvpView {
    fun displayScore(score: List<Score>)
    fun displayProgressBar()
    fun doNotDisplayProgressBar()
    fun displayRefreshLayout()
    fun doNotDisplayRefreshLayout()
    fun displayGamesNotPlayed()
}