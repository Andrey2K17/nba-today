package com.example.nba_today.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.request.RequestOptions
import com.example.nba_today.R
import com.example.nba_today.common.GlideApp
import com.example.nba_today.models.Score
import kotlinx.android.synthetic.main.box_score_item.view.*

class BoxScoreAdapter(private val boxScoreList: List<Score>) :
    RecyclerView.Adapter<BoxScoreAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.box_score_item, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return boxScoreList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.player_name.text = boxScoreList[position].player_name
        holder.player_sername.text = boxScoreList[position].player_surname
        holder.player_pts.text = boxScoreList[position].player_pts
        GlideApp.with(holder.itemView.context)
            .load("https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/${boxScoreList[position].player_id}.png")
            .apply(RequestOptions.centerCropTransform())
            .placeholder(R.drawable.man_icon_layer)
            .into(holder.player_img)
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val player_name = itemView.playerName
        val player_sername = itemView.playerSurname
        val player_pts = itemView.playerPoints
        val player_img = itemView.playerImage
    }
}