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

        (activity as ReceiverMainActivity).disableFloatingButton()
        setStoreSelector()

        return binding.root
    }

    private fun setStoreSelector() {
        val storeViewList = listOf(binding.cl7, binding.clGs, binding.clCu)
        for(i in storeViewList.indices){
            storeViewList[i].setOnClickListener {
                storeViewList[i].visibility = View.INVISIBLE
                when (i) {
                    0 -> {
                        storeViewList[1].visibility = View.VISIBLE
                        store = "GS25"
                    }
                    1 -> {
                        storeViewList[2].visibility = View.VISIBLE
                        store = "CU"
                    }
                    2 -> {
                        storeViewList[0].visibility = View.VISIBLE
                        store = "SEVENELEVEN"
                    }
                }
                setContinueButton()
            }
        }
    }

    private fun setContinueButton(){
        viewModel.setStoreName(store)
        binding.btnContinue.setOnClickListener {
            (activity as ReceiverMainActivity).changeFragment("receive_amount")
        }
    }
}