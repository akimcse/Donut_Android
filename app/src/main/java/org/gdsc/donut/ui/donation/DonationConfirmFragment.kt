package org.gdsc.donut.ui.donation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import org.gdsc.donut.databinding.FragmentDonationConfirmBinding
import org.gdsc.donut.ui.GiverMainActivity

class DonationConfirmFragment : Fragment() {
    private lateinit var binding: FragmentDonationConfirmBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDonationConfirmBinding.inflate(inflater, container, false)

        setButtons()

        return binding.root
    }

    private fun setButtons(){
        binding.btnConfirm.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
            startActivity(Intent(context, DonationDoneActivity::class.java))
        }
        binding.btnRetry.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
            (activity as GiverMainActivity).changeFragment("donation")
        }
    }
    companion object {
    }
}