package com.example.nba_today.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.nba_today.R
import com.example.nba_today.models.PlayerItem
import com.example.nba_today.presenters.PlayerInfoPresenter
import com.example.nba_today.views.PlayerInfoFragmentView
import kotlinx.android.synthetic.main.fragment_player_info.*

class PlayerInfoFragment : MvpAppCompatFragment(), PlayerInfoFragmentView {

    @InjectPresenter
    lateinit var playerPresenter: PlayerInfoPresenter

    companion object {
        const val ARG_NAMEE = "player_id"

        fun newInstance(name: String): PlayerInfoFragment {
            val fragment = PlayerInfoFragment()
            val bundle = Bundle().apply {
                putString(ARG_NAMEE, name)
            }
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_player_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val playerId = arguments!!.getString(ARG_NAMEE)
        playerPresenter.playerRequest(playerId)
    }

    override fun getPlayer(model: PlayerItem) {
        playerItemName.text = model.player_item_name
        playerItemBirthdate.text = model.player_item_birthday
        playerItemPosition.text = model.player_item_pos
        playerItemCountry.text = model.player_item_country
        playerItemDraft.text = model.player_item_draft
        playerItemHeight.text = model.player_item_height
        playerItemWeight.text = model.player_item_weight
    }

}
