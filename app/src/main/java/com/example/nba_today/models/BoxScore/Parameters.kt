package com.example.nba_today.models.BoxScore

data class Parameters(
    val EndPeriod: Int,
    val EndRange: Int,
    val GameID: String,
    val RangeType: Int,
    val StartPeriod: Int,
    val StartRange: Int
)