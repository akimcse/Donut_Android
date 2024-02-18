package org.gdsc.donut.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.gdsc.donut.R
import org.gdsc.donut.databinding.FragmentGiverHistoryBinding
import org.gdsc.donut.databinding.FragmentGiverHistoryGiftDetailBinding

class GiverHistoryGiftDetailFragment : Fragment() {
    private lateinit var binding: FragmentGiverHistoryGiftDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGiverHistoryGiftDetailBinding.inflate(inflater, container, false)


        return binding.root
    }


}