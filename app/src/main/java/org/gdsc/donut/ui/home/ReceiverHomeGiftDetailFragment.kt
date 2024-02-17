package org.gdsc.donut.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import org.gdsc.donut.data.DonutSharedPreferences
import org.gdsc.donut.databinding.FragmentReceiverHomeBinding
import org.gdsc.donut.databinding.FragmentReceiverHomeGiftDetailBinding
import org.gdsc.donut.ui.home.adpater.PackageItemAdapter
import org.gdsc.donut.ui.viewModel.HomeViewModel
import org.gdsc.donut.util.DonutUtil

class ReceiverHomeGiftDetailFragment : Fragment() {
    private lateinit var binding: FragmentReceiverHomeGiftDetailBinding
    private val viewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReceiverHomeGiftDetailBinding.inflate(inflater, container, false)

        initNetwork()
        getReceiverHomeGiftInfo()

        return binding.root
    }

    private fun initNetwork(){
        viewModel.sharedGiftId.observe(viewLifecycleOwner, Observer { data ->
            DonutSharedPreferences.getAccessToken()?.let { viewModel.requestReceiverHomeGiftInfo(it, data) }
        })
    }

    private fun getReceiverHomeGiftInfo(){
        viewModel.receiverHomeGiftInfo.observe(viewLifecycleOwner, Observer { data ->
            val date = data.data!!.dueDate.substring(0, 10)
            binding.tvTitle.text = data.data.product
            binding.tvAmountNum.text = data.data.price.toString()
            binding.tvDueNum.text = date
            binding.tvStoreText.text = data.data.store
            if(data.data.status == "USED") binding.tvStatusText.text = "can use"
            Glide.with(this)
                .load(data.data.imgUrl)
                .fitCenter()
                .into(binding.ivImage)
        })
    }
}