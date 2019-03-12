package com.example.nba_today.models

data class Data(
        val geocoded_waypoints: List<GeocodedWaypoint>,
        val routes: List<Route>,
        val status: String
)