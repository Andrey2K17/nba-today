package com.example.nba_today.views

import com.example.nba_today.models.Score
import moxy.MvpView
import moxy.viewstate.strategy.SingleStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(SingleStateStrategy::class)
interface HomeBoxScoreFragmentView : MvpView {
    fun displayScore(score: List<Score>)
}