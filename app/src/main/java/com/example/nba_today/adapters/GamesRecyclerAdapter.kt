package com.example.nba_today.adapters

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.request.RequestOptions
import com.example.nba_today.R
import com.example.nba_today.activities.BoxScoreActivity
import com.example.nba_today.common.GlideApp
import com.example.nba_today.models.GameItem
import kotlinx.android.synthetic.main.game_item.view.*

class GamesRecyclerAdapter(private val gameList: List<GameItem>) :
    RecyclerView.Adapter<GamesRecyclerAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        GlideApp.with(holder.itemView.context)
            .load("https://stats.nba.com/media/img/teams/logos/${gameList[position].left_img}_logo.svg")
            .apply(RequestOptions.centerCropTransform())
            .into(holder.left_img)

        GlideApp.with(holder.itemView.context)
            .load("https://stats.nba.com/media/img/teams/logos/${gameList[position].right_img}_logo.svg")
            .apply(RequestOptions.centerCropTransform())
            .into(holder.right_img)
        holder.left_team_name.text = gameList[position].left_team_name
        holder.right_team_name.text = gameList[position].right_team_name
        holder.left_team_pts.text = gameList[position].left_team_pts
        holder.right_team_pts.text = gameList[position].right_team_pts
        holder.game_status.text = gameList[position].game_status
        holder.cardGames.setOnClickListener {
            val intent = Intent(holder.itemView.context, BoxScoreActivity::class.java)
            intent.putExtra("games_id", gameList[position].gameId)
            intent.putExtra("left_team", gameList[position].left_team_name)
            intent.putExtra("right_team", gameList[position].right_team_name)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.game_item, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return gameList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val left_img = itemView.leftImg
        val right_img = itemView.rightImg
        val left_team_name = itemView.leftTeamName
        val right_team_name = itemView.rightTeamName
        val left_team_pts = itemView.leftTeamPts
        val right_team_pts = itemView.rightTeamPts
        val game_status = itemView.gameStatus
        val cardGames = itemView.cardViewGame
    }
}