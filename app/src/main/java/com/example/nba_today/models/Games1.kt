package com.example.nba_today.models

import com.google.gson.annotations.SerializedName

data class Games1(
    val resource: String?,
    val parameters: Parameters?,
    val resultSets: List<ResultSet>
)