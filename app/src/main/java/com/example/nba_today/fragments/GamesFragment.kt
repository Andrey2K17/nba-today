package com.example.nba_today.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.MvpFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.nba_today.R
import com.example.nba_today.presenters.GamesPresenter
import com.example.nba_today.views.GamesFragmentView
import kotlinx.android.synthetic.main.fragment_games.*
import org.json.JSONArray
import org.json.JSONObject

class GamesFragment : MvpAppCompatFragment(), GamesFragmentView {


    @InjectPresenter
    lateinit var gamesPresenter: GamesPresenter
    //val queue = Volley.newRequestQueue(context)
    private val URL_DATA = "https://stats.nba.com/stats/scoreboardv2/?gamedate=03.04.19&leagueid=00&dayoffset=0"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gamesPresenter.gamesRequest()
        //loadData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_games, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(): GamesFragment {
            val fragmentGames = GamesFragment()
            val args = Bundle()
            fragmentGames.arguments = args
            return fragmentGames
        }
    }

    override fun setGame(game: String) {
        gamesId.setText(game)
    }

    private fun loadData(){
        val queue = Volley.newRequestQueue(context)
        var stringRequest = StringRequest(Request.Method.GET,URL_DATA,
            Response.Listener<String>{
                response ->
                var strResp = response.toString()
                Log.d("qwerty", strResp)
                val jsonObj: JSONObject = JSONObject(strResp)
                val jsonArray: JSONArray = jsonObj.getJSONArray("resultSets")
                var str_user = ""
                //var jsonInner = jsonArray.getJSONObject(1).getJSONArray("rowSet").getJSONArray(1)
                var jsonInner = jsonObj.getJSONObject("parameters")
                str_user = jsonInner.get("GameDate").toString()
                setGame(str_user)
            },
            Response.ErrorListener { gamesId.setText("Залупа") })
        queue.add(stringRequest)
    }
}
