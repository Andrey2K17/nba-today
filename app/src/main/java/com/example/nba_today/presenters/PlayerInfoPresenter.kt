package com.example.nba_today.presenters

import com.arellomobile.mvp.MvpPresenter
import com.example.nba_today.models.PlayerItem
import com.example.nba_today.retrofit.NbaApiService
import com.example.nba_today.retrofit.RetrofitClientInstance
import com.example.nba_today.views.PlayerInfoFragmentView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PlayerInfoPresenter : MvpPresenter<PlayerInfoFragmentView>() {
    private val disposable = CompositeDisposable()
    private lateinit var jsonApi: NbaApiService
    private var model = PlayerItem()

    fun playerRequest(playerId: String) {
        val retrofit = RetrofitClientInstance.instanceGame
        jsonApi = retrofit.create(NbaApiService::class.java)
        disposable.add(
            jsonApi.getPlayerInfo(playerId)
                .map {
                    model.player_item_name = it.resultSets[0].rowSet!![0][3]
                    model.player_item_birthday = it.resultSets[0].rowSet!![0][6]
                    model.player_item_pos = it.resultSets[0].rowSet!![0][14]
                    model.player_item_country = it.resultSets[0].rowSet!![0][8]
                    model.player_item_draft = it.resultSets[0].rowSet!![0][27]
                    model.player_item_height = it.resultSets[0].rowSet!![0][10]
                    model.player_item_weight = it.resultSets[0].rowSet!![0][11]
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewState.getPlayer(model)
                },
                    { error ->
                        //viewState.notRefresh()
                        error.printStackTrace()
                    })
        )
    }
}