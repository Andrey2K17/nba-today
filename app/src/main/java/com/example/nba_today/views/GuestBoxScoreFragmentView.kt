package com.example.nba_today.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.nba_today.models.Score

@StateStrategyType(SingleStateStrategy::class)
interface GuestBoxScoreFragmentView : MvpView {
    fun displayScore(score: List<Score>)
}