package com.example.nba_today.models.Game

import com.google.gson.annotations.SerializedName

data class ResultSet(
    @SerializedName("name") val name: String?,
    @SerializedName("headers") val headers: List<String>?,
    @SerializedName("rowSet") val rowSet: List<List<String>>?
)