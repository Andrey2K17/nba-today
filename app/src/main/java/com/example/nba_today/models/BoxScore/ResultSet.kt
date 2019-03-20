package com.example.nba_today.models.BoxScore

data class ResultSet(
    val headers: List<String>,
    val name: String,
    val rowSet: List<Any>
)