package org.gdsc.donut.ui.donation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import org.gdsc.donut.databinding.FragmentDonationStartBinding
import org.gdsc.donut.ui.GiverMainActivity
import org.gdsc.donut.ui.viewModel.DonationViewModel

class DonationStartFragment : Fragment() {
    private lateinit var binding: FragmentDonationStartBinding
    private val viewModel: DonationViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDonationStartBinding.inflate(inflater, container, false)

        (activity as GiverMainActivity).disableFloatingButton()
        setButton()

        return binding.root
    }

    private fun setButton(){
        binding.btnWallet.setOnClickListener {
            viewModel.setDirectDonationOption(false)
            (activity as GiverMainActivity).changeFragment("donation")
        }

        binding.btnDonate.setOnClickListener {
            viewModel.setDirectDonationOption(true)
            (activity as GiverMainActivity).changeFragment("donation")
        }
    }
}