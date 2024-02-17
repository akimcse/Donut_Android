package org.gdsc.donut.ui.receive

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import org.gdsc.donut.R
import org.gdsc.donut.databinding.FragmentReceiveStoreBinding
import org.gdsc.donut.ui.ReceiverMainActivity
import org.gdsc.donut.ui.viewModel.DonationViewModel
import org.gdsc.donut.ui.viewModel.RankingViewModel

class ReceiveStoreFragment : Fragment() {
    private lateinit var binding: FragmentReceiveStoreBinding
    private val viewModel: DonationViewModel by activityViewModels()
    private var store = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReceiveStoreBinding.inflate(inflater, container, false)

        (activity as ReceiverMainActivity).enableFloatingButton()
        setStoreSelector()

        return binding.root
    }

    private fun setStoreSelector() {
        binding.cl7.setOnClickListener {
            binding.cl7.isSelected = true
            binding.clCu.isSelected = false
            binding.clGs.isSelected = false
            setContinueButton("SEVENELEVEN")
        }

        binding.clCu.setOnClickListener {
            binding.cl7.isSelected = false
            binding.clCu.isSelected = true
            binding.clGs.isSelected = false
            setContinueButton("CU")
        }

        binding.clGs.setOnClickListener {
            binding.cl7.isSelected = false
            binding.clCu.isSelected = false
            binding.clGs.isSelected = true
            setContinueButton("GS25")
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun setContinueButton(store: String) {
        binding.btnContinue.setBackgroundDrawable(context?.let {getDrawable(it,R.drawable.bg_coral_round)})
        binding.tvContinue.setTextColor(resources.getColor(R.color.white))
        binding.btnContinue.setOnClickListener {
            viewModel.setStoreName(store)
            (activity as ReceiverMainActivity).changeFragment("receive_amount")
        }
    }
}