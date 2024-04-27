package org.gdsc.donut.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.gdsc.donut.databinding.FragmentGiverWalletBinding


class GiverWalletFragment : Fragment() {
    private lateinit var binding: FragmentGiverWalletBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGiverWalletBinding.inflate(inflater, container, false)



        return binding.root
    }
}