package com.example.nba_today.models

data class Leg(
        val distance: Distance,
        val end_address: String,
        val start_address: String,
        val traffic_speed_entry: List<Any>,
        val via_waypoint: List<Any>
)