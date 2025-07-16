package com.example.nba_today.views

import com.example.nba_today.models.PlayerItem
import moxy.MvpView
import moxy.viewstate.strategy.SingleStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(SingleStateStrategy::class)
interface PlayerInfoFragmentView : MvpView {
    fun getPlayer(model: PlayerItem)
}