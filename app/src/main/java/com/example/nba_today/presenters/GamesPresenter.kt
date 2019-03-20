package com.example.nba_today.presenters

import android.annotation.SuppressLint
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.nba_today.models.GameItem
import com.example.nba_today.retrofit.IMyAPI
import com.example.nba_today.retrofit.RetrofitClientInstance
import com.example.nba_today.views.GamesFragmentView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*

@InjectViewState
class GamesPresenter : MvpPresenter<GamesFragmentView>() {

    private val disposable = CompositeDisposable()
    private lateinit var jsonApi: IMyAPI
    private var model = mutableListOf<GameItem>()

    private fun gamesRequest(day: String) {
        val retrofit = RetrofitClientInstance.instanceGame
        jsonApi = retrofit.create(IMyAPI::class.java)
        disposable.add(
            jsonApi.getGames(day)
                .map {
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
                .subscribe({
                    viewState.displpayGames(model)
                },
                    { error ->
                        error.printStackTrace()
                    })
        )
    }


    @SuppressLint("SimpleDateFormat")
    fun getDate(day: Int) {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("MM.dd.yy")
        calendar.add(Calendar.DATE, day)
        model.clear()
        gamesRequest(dateFormat.format(calendar.time))
    }

}