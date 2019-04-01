package com.example.nba_today.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import com.example.nba_today.adapters.GamesHomePagerAdapter
import com.example.nba_today.common.formatNavigatorDate
import com.example.nba_today.common.getDateForPosition
import com.example.nba_today.common.getPositionForDate
import kotlinx.android.synthetic.main.fragment_games_view_pager.*
import java.util.*


class GamesViewPagerFragment : Fragment(), DatePickerDialog.OnDateSetListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(com.example.nba_today.R.layout.fragment_games_view_pager, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(): GamesViewPagerFragment {
            val fragmentGames = GamesViewPagerFragment()
            val args = Bundle()
            fragmentGames.arguments = args
            return fragmentGames
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = GamesHomePagerAdapter(requireContext(), childFragmentManager)
        gamesViewPager.adapter = adapter
        gamesViewPager.currentItem = adapter.count / 2

        setSelectedDate()

        gamesViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {}

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {}

            override fun onPageSelected(p0: Int) {
                setSelectedDate()
            }
        })

        navigatorBackBtn.setOnClickListener { gamesViewPager.currentItem = gamesViewPager.currentItem - 1 }
        navigatorForwardBtn.setOnClickListener { gamesViewPager.currentItem = gamesViewPager.currentItem + 1 }
        selectDateTextView.setOnClickListener {

            val today = Calendar.getInstance()
            val datePicker = DatePickerDialog(
                context!!,
                this,
                today.get(Calendar.YEAR),
                today.get(Calendar.MONTH),
                today.get(Calendar.DAY_OF_MONTH)
            )
            // First available game in backend.
            datePicker.datePicker.minDate = 1507204822000L
            datePicker.datePicker.maxDate = Date().time
            datePicker.show()
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val date = Calendar.getInstance()
        date.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        date.set(Calendar.MONTH, month)
        date.set(Calendar.YEAR, year)

        // Move to selected date.
        gamesViewPager.currentItem = getPositionForDate(date.timeInMillis)
    }

    private fun setSelectedDate() {
        selectDateTextView.text = formatNavigatorDate(
            Date(getDateForPosition(gamesViewPager.currentItem))
        )
    }
}

