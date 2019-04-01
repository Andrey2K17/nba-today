package com.example.nba_today.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.nba_today.models.PlayerItem

@StateStrategyType(SingleStateStrategy::class)
interface PlayerInfoFragmentView : MvpView {
    fun getPlayer(model: PlayerItem)
}