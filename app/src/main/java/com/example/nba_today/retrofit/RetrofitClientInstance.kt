package com.example.nba_today.retrofit

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitClientInstance {
    private var instanceGames: Retrofit? = null
    private var instanceGames1: Retrofit? = null

//    fun create(): IMyAPI.Games {
//        val interceptor = HttpLoggingInterceptor()
//        interceptor.level = HttpLoggingInterceptor.Level.BODY
//
//        val client = OkHttpClient.Builder()
//           // .connectTimeout(5,TimeUnit.MINUTES)
//            //.readTimeout(5,TimeUnit.MINUTES)
//            .addInterceptor(interceptor)
//            .build()
//
//        val retrofit = Retrofit.Builder()
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(client)
//            .baseUrl("https://stats.nba.com/")
//            .build()
//
//        return retrofit.create(IMyAPI.Games::class.java)
//    }

    val gson = GsonBuilder()
        .setLenient()
        .create()

    val instanseGame: Retrofit
        get() {
            val interceptor = HttpLoggingInterceptor()
            //interceptor.level = HttpLoggingInterceptor.Level.HEADERS
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder()
                //.connectTimeout(10, TimeUnit.SECONDS)
                //.readTimeout(12, TimeUnit.SECONDS)
                .addInterceptor(interceptor)

            if (instanceGames == null) {
                instanceGames = Retrofit.Builder()
                    .baseUrl("https://stats.nba.com/")
                    .client(client.build())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
            }
            return instanceGames!!
        }

//    val instanseGame1: Retrofit
//        get() {
//            val interceptor = HttpLoggingInterceptor()
//            interceptor.level = HttpLoggingInterceptor.Level.BODY
//
//            val client = OkHttpClient.Builder()
//                .addInterceptor(interceptor)
//                .build()
//
//            if (instanceGames1 == null) {
//                instanceGames1 = Retrofit.Builder()
//                    .baseUrl("https://maps.googleapis.com/maps/api/directions/")
//                    .client(client)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                    .build()
//            }
//            return instanceGames1!!
//        }
}