package com.example.nba_today.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
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

    fun gamesRequest() {
        val retrofit = RetrofitClientInstance.instanceGame
        jsonApi = retrofit.create(IMyAPI::class.java)
        disposable.add(
            jsonApi.getGames(getDate())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ data ->
                    viewState.setGame(data.resultSets[1].rowSet!![0][5])
                },
                    { error ->
                        error.printStackTrace()
                    })
        )
    }

    private fun getDate(): String {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("MM.dd.yy")
        calendar.add(Calendar.DATE, -1)
        return dateFormat.format(calendar.time)
    }

}