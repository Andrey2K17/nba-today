package com.example.nba_today.models

data class Route(
        val copyrights: String,
        val legs: List<Leg>,
        val summary: String,
        val warnings: List<Any>,
        val waypoint_order: List<Any>
)