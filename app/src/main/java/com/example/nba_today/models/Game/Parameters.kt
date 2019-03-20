package com.example.nba_today.models.Game

import com.google.gson.annotations.SerializedName

data class Parameters(
    @SerializedName("GameDate") val GameDate: String?,
    @SerializedName("LeagueID") val LeagueID: String?,
    @SerializedName("DayOffset") val DayOffset: String?
)