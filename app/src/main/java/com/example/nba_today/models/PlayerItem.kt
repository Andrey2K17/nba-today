package com.example.nba_today.models

data class PlayerItem(
    var player_item_name: String,
    var player_item_birthday: String,
    var player_item_pos: String,
    var player_item_country: String,
    var player_item_draft: String,
    var player_item_height: String,
    var player_item_weight: String,
    var player_item_id: String
)
{
    constructor() : this(
        "", "", "", "", "",
        "", "",""
    )
}
