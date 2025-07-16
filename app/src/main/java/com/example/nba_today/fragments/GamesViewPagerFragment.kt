package com.example.nba_today.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.nba_today.R
import com.example.nba_today.adapters.GamesHomePagerAdapter
import com.example.nba_today.common.formatNavigatorDate
import com.example.nba_today.common.getDateForPosition
import com.example.nba_today.common.getPositionForDate
import com.example.nba_today.databinding.FragmentGamesViewPagerBinding
import java.util.*

class GamesViewPagerFragment : Fragment(), DatePickerDialog.OnDateSetListener {

    private var _binding: FragmentGamesViewPagerBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: GamesHomePagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGamesViewPagerBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance() = GamesViewPagerFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = GamesHomePagerAdapter(requireContext(), childFragmentManager)
        binding.gamesViewPager.adapter = adapter
        binding.gamesViewPager.currentItem = adapter.count / 2

        setSelectedDate()

        binding.gamesViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageSelected(position: Int) {
                setSelectedDate()
            }
        })

        binding.navigatorBackBtn.setOnClickListener {
            binding.gamesViewPager.currentItem = binding.gamesViewPager.currentItem - 1
        }

        binding.navigatorForwardBtn.setOnClickListener {
            binding.gamesViewPager.currentItem = binding.gamesViewPager.currentItem + 1
        }

        binding.selectDateTextView.setOnClickListener {
            showDatePickerDialog()
        }
    }

    private fun showDatePickerDialog() {
        val today = Calendar.getInstance()
        context?.let { context ->
            DatePickerDialog(
                context,
                this,
                today.get(Calendar.YEAR),
                today.get(Calendar.MONTH),
                today.get(Calendar.DAY_OF_MONTH)
            ).apply {
                datePicker.minDate = 1507204822000L // First available game in backend
                datePicker.maxDate = Date().time
                show()
            }
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        Calendar.getInstance().apply {
            set(Calendar.YEAR, year)
            set(Calendar.MONTH, month)
            set(Calendar.DAY_OF_MONTH, dayOfMonth)
            binding.gamesViewPager.currentItem = getPositionForDate(timeInMillis)
        }
    }

    private fun setSelectedDate() {
        binding.selectDateTextView.text = formatNavigatorDate(
            Date(getDateForPosition(binding.gamesViewPager.currentItem))
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}