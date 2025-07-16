package com.example.nba_today.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.nba_today.R
import com.example.nba_today.activities.BoxScoreActivity
import com.example.nba_today.databinding.GameItemBinding
import com.example.nba_today.models.GameItem

class GamesRecyclerAdapter(
    private val context: Context,
    private val gameList: List<GameItem>
) : RecyclerView.Adapter<GamesRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: GameItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(game: GameItem) {
            with(binding) {
                // Загрузка изображений с помощью Glide
                Glide.with(context)
                    .load("https://stats.nba.com/media/img/teams/logos/${game.left_img}_logo.svg")
                    .apply(RequestOptions.centerCropTransform())
                    .into(leftImg)

                Glide.with(context)
                    .load("https://stats.nba.com/media/img/teams/logos/${game.right_img}_logo.svg")
                    .apply(RequestOptions.centerCropTransform())
                    .into(rightImg)

                leftTeamName.text = game.left_team_name
                rightTeamName.text = game.right_team_name
                leftTeamPts.text = game.left_team_pts
                rightTeamPts.text = game.right_team_pts
                gameStatus.text = game.game_status

                // Обработка клика по карточке
                cardViewGame.setOnClickListener {
                    val intent = Intent(context, BoxScoreActivity::class.java).apply {
                        putExtra("games_id", game.gameId)
                        putExtra("left_team", game.left_team_name)
                        putExtra("right_team", game.right_team_name)
                    }
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = GameItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(gameList[position])
    }

    override fun getItemCount(): Int = gameList.size
}