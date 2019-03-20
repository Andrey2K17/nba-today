package com.example.nba_today.fragments


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.nba_today.R
import com.example.nba_today.adapters.BoxScoreAdapter
import com.example.nba_today.models.Score
import com.example.nba_today.presenters.GuestBoxScorePresenter
import com.example.nba_today.views.GuestBoxScoreFragmentView
import kotlinx.android.synthetic.main.box_score_item.*
import kotlinx.android.synthetic.main.fragment_guest_box_score.*


class GuestBoxScoreFragment : MvpAppCompatFragment(), GuestBoxScoreFragmentView {

    @InjectPresenter
    lateinit var guestPresenter: GuestBoxScorePresenter

    companion object {
        const val ARG_NAME = "games_id"

        fun newInstance(name: String): GuestBoxScoreFragment {
            val fragment = GuestBoxScoreFragment()
            val bundle = Bundle().apply {
                putString(ARG_NAME, name)
            }
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        playerName.addTextChangedListener(object : TextWatcher {
//            override fun afterTextChanged(s: Editable?) {
//                if (s!!.isNotBlank()) {
//                    for (i in 0 until s.length) {
//                        if (s[i] == ' ') {
//                            s.append("\n")
//                        }
//                    }
//                }
//            }
//
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
//        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val gameId = arguments!!.getString(ARG_NAME)
        guestPresenter.scoreRequest(gameId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_guest_box_score, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        boxScroreRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
        }

    }

    override fun displayScore(score: List<Score>) {
        val adapter = BoxScoreAdapter(score)
        boxScroreRecyclerView.adapter = adapter
    }

}
