package com.example.nba_today.presenters

import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.nba_today.retrofit.IMyAPI
import com.example.nba_today.retrofit.RetrofitClientInstance
import com.example.nba_today.views.GamesFragmentView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_games.*
import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

@InjectViewState
class GamesPresenter : MvpPresenter<GamesFragmentView>() {

    private val disposable = CompositeDisposable()
    private lateinit var jsonApi: IMyAPI.Games
    //private lateinit var jsonApi1: IMyAPI.Games1
    private val URL_DATA = "https://stats.nba.com/stats/scoreboardv2/?gamedate=03.04.19&leagueid=00&dayoffset=0"

    fun gamesRequest() {
        val retrofit = RetrofitClientInstance.instanseGame
        jsonApi = retrofit.create(IMyAPI.Games::class.java)
        //val apiService = RetrofitClientInstance.create()
        //disposable.add(apiService.getGames("03.04.2019","00","0")
        disposable.add(jsonApi.getGames()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({data ->
                viewState.setGame(data.resultSets[1].name!!)
            },
                {error ->
                   error.printStackTrace()
                })
            )
    }

    //    fun gamesRequest() {
//        val retrofit = RetrofitClientInstance.instanseGame1
//        jsonApi1 = retrofit.create(IMyAPI.Games1::class.java)
//        disposable.add(jsonApi1.getGames1()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({data ->
//                viewState.setGame(data.routes[0].legs[0].distance.text)
//            },
//                {error ->
//                    error.printStackTrace()
//                })
//        )
//    }

//    fun gamesRequest(queue: RequestQueue){
//        var stringRequest = StringRequest(
//            Request.Method.GET,URL_DATA,
//            Response.Listener<String>{
//                    response ->
//                var strResp = response.toString()
//                Log.d("qwerty", strResp)
//                val jsonObj: JSONObject = JSONObject(strResp)
//                val jsonArray: JSONArray = jsonObj.getJSONArray("resultSets")
//                var str_user = ""
//                var jsonInner = jsonArray.getJSONObject(1).getJSONArray("rowSet").getJSONArray(1)
//                str_user = jsonInner.getString(22)
//                    //setGame(str_user)
//                viewState.setGame(str_user)
//            },
//            Response.ErrorListener { Log.d("qwerty", "залупа") })
//        queue.add(stringRequest)
//    }





    private fun getDate(): String {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("MM.dd.yy")
        calendar.add(Calendar.DATE, -1)
        return dateFormat.format(calendar.time)
    }

}