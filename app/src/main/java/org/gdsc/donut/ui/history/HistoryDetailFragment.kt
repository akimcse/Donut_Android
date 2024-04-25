package org.gdsc.donut.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.gdsc.donut.databinding.FragmentGiverMyPageBinding
import org.gdsc.donut.databinding.FragmentHistoryDetailBinding

class HistoryDetailFragment : Fragment() {
    private lateinit var binding: FragmentHistoryDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryDetailBinding.inflate(inflater, container, false)

        return binding.root
    }
}