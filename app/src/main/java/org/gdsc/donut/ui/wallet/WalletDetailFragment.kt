package org.gdsc.donut.ui.wallet

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



        return binding.root
    }
}