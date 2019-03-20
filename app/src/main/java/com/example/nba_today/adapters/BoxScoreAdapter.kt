package com.example.nba_today.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nba_today.R
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
        holder.player_pts.text = boxScoreList[position].player_pts
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val player_name = itemView.playerName
        val player_pts = itemView.playerPoints
    }
}