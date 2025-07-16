package com.example.nba_today.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nba_today.databinding.FragmentTableBinding
import com.example.nba_today.views.TableFragmentView
import moxy.MvpAppCompatFragment

class TableFragment : MvpAppCompatFragment(), TableFragmentView {

    private var _binding: FragmentTableBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTableBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun setImage() {}
}