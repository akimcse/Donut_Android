package org.gdsc.donut.ui.donation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.gdsc.donut.databinding.FragmentDonationCheckBinding
import org.gdsc.donut.ui.GiverMainActivity

class DonationCheckFragment : Fragment() {

    private lateinit var binding: FragmentDonationCheckBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDonationCheckBinding.inflate(inflater, container, false)

        (activity as GiverMainActivity).enableFloatingButton()

        return binding.root
    }
}