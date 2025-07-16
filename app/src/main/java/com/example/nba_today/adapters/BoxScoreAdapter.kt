package com.example.nba_today.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.nba_today.R
import com.example.nba_today.databinding.BoxScoreItemBinding
import com.example.nba_today.models.Score

class BoxScoreAdapter(
    private val boxScoreList: List<Score>,
    private val onItemClick: (playerId: String) -> Unit = {}
) : RecyclerView.Adapter<BoxScoreAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = BoxScoreItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = boxScoreList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val score = boxScoreList[position]
        with(holder.binding) {
            playerName.text = score.player_name
            playerSurname.text = score.player_surname
            playerPoints.text = score.player_pts
            playerRebounds.text = score.player_reb
            playerAssist.text = score.player_ast

            Glide.with(root.context)
                .load("https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/${score.player_id}.png")
                .apply(RequestOptions.centerCropTransform())
                .placeholder(R.drawable.ic_man_layer)
                .into(playerImage)

            root.setOnClickListener {
                onItemClick(score.player_id!!)
            }
        }
    }

    class ViewHolder(val binding: BoxScoreItemBinding) : RecyclerView.ViewHolder(binding.root)
}