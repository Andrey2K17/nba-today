package com.example.nba_today.models.BoxScore

data class Score(
    val parameters: Parameters,
    val resource: String,
    val resultSets: List<ResultSet>
)