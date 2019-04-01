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
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.box_score_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return boxScoreList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.playerName.text = boxScoreList[position].player_name
        holder.playerSurname.text = boxScoreList[position].player_surname
        holder.playerPts.text = boxScoreList[position].player_pts
        GlideApp.with(holder.itemView.context)
            .load("https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/${boxScoreList[position].player_id}.png")
            .apply(RequestOptions.centerCropTransform())
            .placeholder(R.drawable.ic_man_layer)
            .into(holder.playerImg)
        holder.playerReb.text = boxScoreList[position].player_reb
        holder.playerAst.text = boxScoreList[position].player_ast
        holder.boxScoreView.setOnClickListener {
            //            val fr = PlayerInfoFragment()
//            val args = Bundle().apply {
//                putString("player_id", boxScoreList[position].player_id)
//            }
//


            //Toast.makeText(holder.a.context, "Кнопка нажата", Toast.LENGTH_SHORT).show()
//            val intent = Intent(holder.itemView.context, PlayerInfoFragment::class.java)
//            intent.putExtra("player_id", boxScoreList[position].player_id)
//            holder.itemView.context.startActivity(intent)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val playerName = itemView.playerName!!
        val playerSurname = itemView.playerSurname!!
        val playerPts = itemView.playerPoints!!
        val playerImg = itemView.playerImage!!
        val playerReb = itemView.playerRebounds!!
        val playerAst = itemView.playerAssist!!
        val boxScoreView = itemView
    }

}