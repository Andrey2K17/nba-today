package com.example.nba_today.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.nba_today.models.Score

@StateStrategyType(SingleStateStrategy::class)
interface BoxScoreFragmentView : MvpView {
    fun displayScore(score: List<Score>)
    fun displayProgressBar()
    fun doNotDisplayProgressBar()
    fun displayRefreshLayout()
    fun doNotDisplayRefreshLayout()
    fun displayGamesNotPlayed()
}