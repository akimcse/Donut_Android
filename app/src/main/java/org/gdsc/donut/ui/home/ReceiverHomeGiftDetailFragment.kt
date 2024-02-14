package org.gdsc.donut.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.gdsc.donut.databinding.FragmentReceiverHomeBinding
import org.gdsc.donut.databinding.FragmentReceiverHomeGiftDetailBinding
import org.gdsc.donut.ui.home.adpater.PackageItemAdapter

class ReceiverHomeGiftDetailFragment : Fragment() {
    private lateinit var binding: FragmentReceiverHomeGiftDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReceiverHomeGiftDetailBinding.inflate(inflater, container, false)

        return binding.root
    }
}