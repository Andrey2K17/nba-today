package com.example.nba_today.retrofit

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientInstance {
    private var instanceGames: Retrofit? = null

    val instanceGame: Retrofit
        get() {
            if (instanceGames == null) {
                instanceGames = Retrofit.Builder()
                    .baseUrl("https://stats.nba.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
            }
            return instanceGames!!
        }
}