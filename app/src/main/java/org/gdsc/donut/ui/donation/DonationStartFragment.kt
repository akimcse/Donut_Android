package org.gdsc.donut.ui.donation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.gdsc.donut.databinding.FragmentDonationStartBinding
import org.gdsc.donut.ui.GiverMainActivity

class DonationStartFragment : Fragment() {
    private lateinit var binding: FragmentDonationStartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDonationStartBinding.inflate(inflater, container, false)

        (activity as GiverMainActivity).enableFloatingButton()

        return binding.root
    }
}