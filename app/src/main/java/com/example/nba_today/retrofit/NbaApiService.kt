package com.example.nba_today.retrofit

import com.example.nba_today.models.Game.Games
import com.example.nba_today.models.PlayerItem
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NbaApiService {
    @Headers(
        value = [
            "Accept-Language: ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7",
            "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36 OPR/58.0.3135.79"
        ]
    )

    @GET("stats/scoreboardv2?leagueid=00&dayoffset=0")
    fun getGames(
        @Query("gamedate") gamedate: String
    ): Single<Games>

    @Headers(
        value = [
            "Accept-Language: ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7",
            "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36 OPR/58.0.3135.79"
        ]
    )
    @GET("stats/boxscoretraditionalv2/?StartPeriod=1&EndPeriod=10&StartRange=1&EndRange=10&RangeType=1")
    fun getBoxScore(
        @Query("gameid") gameid: String
    ): Single<Games>

    @Headers(
        value = [
            "Accept-Language: ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7",
            "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36 OPR/58.0.3135.79"
        ]
    )
    @GET("stats/commonplayerinfo/")
    fun getPlayerInfo(
        @Query("playerid") playerid: String
    ): Single<Games>
}
