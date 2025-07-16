package com.example.nba_today.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nba_today.adapters.GamesRecyclerAdapter
import com.example.nba_today.common.getDateForPosition
import com.example.nba_today.databinding.FragmentGamesBinding
import com.example.nba_today.models.GameItem
import com.example.nba_today.presenters.GamesPresenter
import com.example.nba_today.views.GamesFragmentView
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

import java.text.SimpleDateFormat
import java.util.*

class GamesFragment : MvpAppCompatFragment(), GamesFragmentView {

    @InjectPresenter
    lateinit var gamesPresenter: GamesPresenter

    private var _binding: FragmentGamesBinding? = null
    private val binding get() = _binding!!
    private var position = 0

    companion object {
        fun newInstance(position: Int): GamesFragment {
            return GamesFragment().apply {
                arguments = Bundle().apply {
                    putInt("Data", position)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        position = arguments?.getInt("Data") ?: 0
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGamesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.gamesRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
        }

        binding.gamesRefreshLayout.setOnRefreshListener {
            gamesPresenter.gamesRequest(showSelectedDate())
        }
    }

    override fun onResume() {
        super.onResume()
        gamesPresenter.gamesRequest(showSelectedDate())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun displayGames(games: List<GameItem>) {
        val adapter = GamesRecyclerAdapter(requireContext(), games)
        binding.gamesRecyclerView.adapter = adapter
    }

    override fun displayRefreshLayout() {
        binding.gamesRefreshLayout.isRefreshing = true
    }

    override fun doNotDisplayRefreshLayout() {
        binding.gamesRefreshLayout.isRefreshing = false
    }

    override fun displayProgressBar() {
        binding.gamesProgressBar.visibility = View.VISIBLE
    }

    override fun doNotDisplayProgressBar() {
        binding.gamesProgressBar.visibility = View.INVISIBLE
    }

    override fun displayNotGameChooseDay() {
        binding.noGamesToday.visibility = View.VISIBLE
    }

    @SuppressLint("SimpleDateFormat")
    private fun showSelectedDate(): String {
        return SimpleDateFormat("MM.dd.yy").format(Date(getDateForPosition(position)))
    }
}