package com.example.nba_today.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nba_today.adapters.BoxScoreAdapter
import com.example.nba_today.databinding.FragmentBoxScoreBinding
import com.example.nba_today.models.Score
import com.example.nba_today.presenters.BoxScorePresenter
import com.example.nba_today.views.BoxScoreFragmentView
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class HomeBoxScoreFragment : MvpAppCompatFragment(), BoxScoreFragmentView {


    @InjectPresenter
    lateinit var boxScorePresenter: BoxScorePresenter
    private var _binding: FragmentBoxScoreBinding? = null
    private val binding get() = _binding!!
    private var check = 1

    companion object {
        const val ARG_NAME = "games_id"

        fun newInstance(name: String): HomeBoxScoreFragment {
            val fragment = HomeBoxScoreFragment()
            val bundle = Bundle().apply {
                putString(ARG_NAME, name)
            }
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val gameId = requireArguments().getString(GuestBoxScoreFragment.ARG_NAME)!!
        boxScorePresenter.scoreRequest1(gameId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBoxScoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.boxScoreRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
        }
        val gameId = requireArguments().getString(HomeBoxScoreFragment.ARG_NAME)
        binding.boxScoreRefreshLayout.setOnRefreshListener { boxScorePresenter.scoreRequest1(gameId!!) }
    }

    override fun displayScore(score: List<Score>) {
        val adapter = BoxScoreAdapter(score)
        binding.boxScoreRecyclerView.adapter = adapter
    }

    override fun displayProgressBar() {
        if (check == 1) {
            binding.boxScoreProgressBar.visibility = View.VISIBLE
            check = 0
        }
    }

    override fun doNotDisplayProgressBar() {
        binding.boxScoreProgressBar.visibility = View.INVISIBLE
    }

    override fun displayRefreshLayout() {
        binding.boxScoreRefreshLayout.isRefreshing = true
    }

    override fun doNotDisplayRefreshLayout() {
        binding.boxScoreRefreshLayout.isRefreshing = false
    }

    override fun displayGamesNotPlayed() {
        binding.gamesNotPlayed.visibility = View.VISIBLE
    }
}
