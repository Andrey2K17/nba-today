package com.example.nba_today.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.nba_today.models.GameItem
import com.example.nba_today.retrofit.NbaApiService
import com.example.nba_today.retrofit.RetrofitClientInstance
import com.example.nba_today.views.GamesFragmentView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

@InjectViewState
class GamesPresenter : MvpPresenter<GamesFragmentView>() {

    private val disposable = CompositeDisposable()
    private lateinit var jsonApi: NbaApiService
    private var model = mutableListOf<GameItem>()

    fun gamesRequest(day: String) {
        val retrofit = RetrofitClientInstance.instanceGame
        jsonApi = retrofit.create(NbaApiService::class.java)
        disposable.add(
            jsonApi.getGames(day)
                .map {
                    if (model.isNotEmpty()) model.clear()
                    for (i in 0 until it.resultSets[1].rowSet!!.size - 1 step 2) {
                        model.add(
                            GameItem(
                                it.resultSets[1].rowSet!![i][4],
                                it.resultSets[1].rowSet!![i + 1][4],
                                it.resultSets[1].rowSet!![i][6],
                                it.resultSets[1].rowSet!![i + 1][6],
                                it.resultSets[1].rowSet!![i][22],
                                it.resultSets[1].rowSet!![i + 1][22],
                                it.resultSets[0].rowSet!![i / 2][4],
                                it.resultSets[1].rowSet!![i][2]
                            )
                        )
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
                    if (model.isEmpty()) viewState.displayNotGameChooseDay()
                    viewState.displayRefreshLayout()
                    viewState.displayGames(model)
                },
                    { error ->
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

//    @SuppressLint("SimpleDateFormat")
//    fun getDate(day: Int) {
//        val calendar = Calendar.getInstance()
//        val dateFormat = SimpleDateFormat("MM.dd.yy")
//        calendar.add(Calendar.DATE, day)
//        model.clear()
//        gamesRequest(dateFormat.format(calendar.time))
//    }
}