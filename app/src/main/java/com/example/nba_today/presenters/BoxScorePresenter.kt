package com.example.nba_today.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.nba_today.models.Score
import com.example.nba_today.retrofit.NbaApiService
import com.example.nba_today.retrofit.RetrofitClientInstance
import com.example.nba_today.views.BoxScoreFragmentView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

@InjectViewState
class BoxScorePresenter : MvpPresenter<BoxScoreFragmentView>() {
    private val disposable = CompositeDisposable()
    private lateinit var apiService: NbaApiService
    private var model = mutableListOf<Score>()

    fun scoreRequest(gameId: String) {
        apiService = RetrofitClientInstance.instanceGame.create(NbaApiService::class.java)
        disposable.add(
            apiService.getBoxScore(gameId)
                .map {
                    if (model.isNotEmpty()) model.clear()
                    for (i in 0 until it.resultSets[0].rowSet!!.size) {
                        if (it.resultSets[0].rowSet!![0][2] == it.resultSets[0].rowSet!![i][2]) {
                            model.add(
                                Score(
                                    it.resultSets[0].rowSet!![i][5].substringBefore(" "),
                                    it.resultSets[0].rowSet!![i][5].substringAfter(" "),
                                    it.resultSets[0].rowSet!![i][26],
                                    it.resultSets[0].rowSet!![i][4],
                                    it.resultSets[0].rowSet!![i][20],
                                    it.resultSets[0].rowSet!![i][21]
                                )
                            )
                        }
                    }
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { viewState.displayProgressBar() }
                .doAfterSuccess {
                    viewState.doNotDisplayRefreshLayout()
                    viewState.doNotDisplayProgressBar()
                }
                .subscribe({
                    if (model.isEmpty()) viewState.displayGamesNotPlayed()
                    viewState.displayScore(model)
                    viewState.displayRefreshLayout()

                }, { error ->
                    viewState.doNotDisplayRefreshLayout()
                    error.printStackTrace()
                })
        )
    }

    fun scoreRequest1(gameId: String) {
        apiService = RetrofitClientInstance.instanceGame.create(NbaApiService::class.java)
        disposable.add(
            apiService.getBoxScore(gameId)
                .map {
                    if (model.isNotEmpty()) model.clear()
                    for (i in 0 until it.resultSets[0].rowSet!!.size) {
                        if (it.resultSets[0].rowSet!![i][2] != it.resultSets[0].rowSet!![0][2]) {
                            model.add(
                                Score(
                                    it.resultSets[0].rowSet!![i][5].substringBefore(" "),
                                    it.resultSets[0].rowSet!![i][5].substringAfter(" "),
                                    it.resultSets[0].rowSet!![i][26],
                                    it.resultSets[0].rowSet!![i][4],
                                    it.resultSets[0].rowSet!![i][20],
                                    it.resultSets[0].rowSet!![i][21]
                                )
                            )
                        }
                    }
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { viewState.displayProgressBar() }
                .doAfterSuccess {
                    viewState.doNotDisplayProgressBar()
                    viewState.doNotDisplayRefreshLayout()
                }
                .subscribe({
                    if (model.isEmpty()) viewState.displayGamesNotPlayed()
                    viewState.displayRefreshLayout()
                    viewState.displayScore(model)

                }, { error ->
                    viewState.doNotDisplayRefreshLayout()
                    error.printStackTrace()
                })
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
        disposable.clear()
    }
}