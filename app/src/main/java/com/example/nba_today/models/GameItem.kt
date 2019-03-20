package com.example.nba_today.models

data class GameItem(
    var left_img: String?,
    var right_img: String?,
    var left_team_name: String?,
    var right_team_name: String?,
    var left_team_pts: String?,
    var right_team_pts: String?,
    var game_status: String?,
    var gameId: String
)