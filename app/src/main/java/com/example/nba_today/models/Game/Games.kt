package com.example.nba_today.models.Game

import com.google.gson.annotations.SerializedName

data class Games(
    @SerializedName("resource") val resource: String?,
    @SerializedName("parameters") val parameters: Parameters?,
    @SerializedName("resultSets") val resultSets: List<ResultSet>
)