package org.gdsc.donut.ui.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import org.gdsc.donut.data.DonutSharedPreferences
import org.gdsc.donut.data.local.UnusedItemData
import org.gdsc.donut.databinding.FragmentReceiverHomePackageDetailBinding
import org.gdsc.donut.ui.ReceiverMainActivity
import org.gdsc.donut.ui.home.adpater.DetailItemAdapter
import org.gdsc.donut.ui.viewModel.HomeViewModel
import org.gdsc.donut.util.DonutUtil

class ReceiverHomePackageDetailFragment : Fragment() {
    private lateinit var binding: FragmentReceiverHomePackageDetailBinding
    private lateinit var itemAdapter: DetailItemAdapter
    private val viewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReceiverHomePackageDetailBinding.inflate(inflater, container, false)

        initNetwork()
        getReceiverHomeBoxInfo()
        setAdapter()

        return binding.root
    }

    private fun initNetwork(){
        viewModel.sharedBoxId.observe(viewLifecycleOwner, Observer { data ->
            DonutSharedPreferences.getAccessToken()?.let { viewModel.requestReceiverHomeBoxInfo(it, data) }
        })
    }

    private fun getReceiverHomeBoxInfo(){
        viewModel.receiverHomeBoxInfo.observe(viewLifecycleOwner, Observer { data ->
            val date = data.data!!.dueDate.substring(0, 10)
            binding.tvTitle.text = data.data.store
            binding.tvAmountNum.text = data.data.amount.toString()
            binding.tvDueNum.text = DonutUtil().setCalendarFormat(date)
        })
    }

    private fun setAdapter() {
        itemAdapter = DetailItemAdapter()
        binding.rvDetailItem.adapter = itemAdapter
        binding.rvDetailItem.layoutManager = GridLayoutManager(context, 2)

        itemAdapter.setOnItemClickListener { _, pos ->
            for (changePos in itemAdapter.itemList.indices) {
                viewModel.setGiftId(itemAdapter.itemList[itemAdapter.mPosition].giftId)
                (activity as ReceiverMainActivity).changeFragment("gift_detail")
            }
        }
        setDataList()
    }

    private fun setDataList(){
        viewModel.receiverHomeBoxInfo.observe(viewLifecycleOwner, Observer { data ->
            with(binding.rvDetailItem.adapter as DetailItemAdapter) {
                data.data!!.giftList?.let { itemAdapter.setGiftItemList(it) }
            }
        })
    }

    companion object {
    }
}