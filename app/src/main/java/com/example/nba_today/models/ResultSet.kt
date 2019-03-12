package com.example.nba_today.models

data class ResultSet(
    val name: String?,
    val headers: List<String>?,
    val rowSet: List<List<String>>?
)