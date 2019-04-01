package com.example.nba_today.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.request.RequestOptions
import com.example.nba_today.R
import com.example.nba_today.common.GlideApp
import com.example.nba_today.models.PlayerItem
import kotlinx.android.synthetic.main.fragment_player_info.view.*

class PlayerInfoAdapter(private val playerInfoList: List<PlayerItem>) :
    RecyclerView.Adapter<PlayerInfoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.player_item, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return playerInfoList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        GlideApp.with(holder.itemView.context)
            .load("https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/${playerInfoList[position].player_item_id}.png")
            .apply(RequestOptions.centerCropTransform())
            .placeholder(R.drawable.ic_man_layer)
            .into(holder.player_img)
        holder.player_name.text = playerInfoList[position].player_item_name
        holder.player_birthdate.text = playerInfoList[position].player_item_birthday
        holder.player_pos.text = playerInfoList[position].player_item_pos
        holder.player_country.text = playerInfoList[position].player_item_country
        holder.player_draft.text = playerInfoList[position].player_item_draft
        holder.player_height.text = playerInfoList[position].player_item_height
        holder.player_weight.text = playerInfoList[position].player_item_weight
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val player_img = itemView.playerItemImage
        val player_name = itemView.playerItemName
        val player_birthdate = itemView.playerItemBirthdate
        val player_pos = itemView.playerItemPosition
        val player_country = itemView.playerItemCountry
        val player_draft = itemView.playerItemDraft
        val player_height = itemView.playerItemHeight
        val player_weight = itemView.playerItemWeight
    }
}