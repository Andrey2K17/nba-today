package com.example.nba_today.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nba_today.databinding.FragmentPlayerInfoBinding
import com.example.nba_today.models.PlayerItem
import com.example.nba_today.presenters.PlayerInfoPresenter
import com.example.nba_today.views.PlayerInfoFragmentView
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class PlayerInfoFragment : MvpAppCompatFragment(), PlayerInfoFragmentView {

    @InjectPresenter
    lateinit var playerPresenter: PlayerInfoPresenter
    private var _binding: FragmentPlayerInfoBinding? = null
    private val binding get() = _binding!!

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
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentPlayerInfoBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val playerId = requireArguments().getString(ARG_NAMEE)!!
        playerPresenter.playerRequest(playerId)
    }

    override fun getPlayer(model: PlayerItem) {
        binding.apply {
            playerItemName.text = model.player_item_name
            playerItemBirthdate.text = model.player_item_birthday
            playerItemPosition.text = model.player_item_pos
            playerItemCountry.text = model.player_item_country
            playerItemDraft.text = model.player_item_draft
            playerItemHeight.text = model.player_item_height
            playerItemWeight.text = model.player_item_weight
        }
    }

}
