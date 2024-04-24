package org.gdsc.donut.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.gdsc.donut.databinding.FragmentWalletDetailBinding

class WalletDetailFragment : Fragment() {
    private lateinit var binding: FragmentWalletDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWalletDetailBinding.inflate(inflater, container, false)

        setButton()

        return binding.root
    }

    private fun setButton(){
        binding.ivDots.setOnClickListener {
            binding.clReport.visibility = View.VISIBLE
        }
    }
}