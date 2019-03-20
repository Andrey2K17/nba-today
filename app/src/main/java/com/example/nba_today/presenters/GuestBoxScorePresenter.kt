package com.example.nba_today.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.nba_today.models.Score
import com.example.nba_today.retrofit.IMyAPI
import com.example.nba_today.retrofit.RetrofitClientInstance
import com.example.nba_today.views.GuestBoxScoreFragmentView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

@InjectViewState
class GuestBoxScorePresenter() : MvpPresenter<GuestBoxScoreFragmentView>() {
    private val disposable = CompositeDisposable()
    private lateinit var jsonApi: IMyAPI
    private var model = mutableListOf<Score>()

    fun scoreRequest(gameId: String) {
        val retrofit = RetrofitClientInstance.instanceGame
        jsonApi = retrofit.create(IMyAPI::class.java)
        disposable.add(
            jsonApi.getBoxScore(gameId)
                .map {
                    for (i in 0 until it.resultSets[0].rowSet!!.size) {
                        model.add(
                            Score(
                                it.resultSets[0].rowSet!![i][5],
                                it.resultSets[0].rowSet!![i][26]
                            )
                        )
                    }
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewState.displayScore(model)
                }, { error ->
                    error.printStackTrace()
                })
        )
    }

}