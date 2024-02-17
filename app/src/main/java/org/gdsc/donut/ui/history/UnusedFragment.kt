package org.gdsc.donut.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import org.gdsc.donut.data.DonutSharedPreferences
import org.gdsc.donut.data.local.UnusedItemData
import org.gdsc.donut.databinding.FragmentUnusedBinding
import org.gdsc.donut.ui.history.adapter.UnusedItemAdapter
import org.gdsc.donut.ui.home.adpater.PackageItemAdapter
import org.gdsc.donut.ui.viewModel.HistoryViewModel
import org.gdsc.donut.ui.viewModel.HomeViewModel

class UnusedFragment : Fragment() {
    private lateinit var binding: FragmentUnusedBinding
    private lateinit var itemAdapter: UnusedItemAdapter
    private val viewModel: HistoryViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUnusedBinding.inflate(inflater, container, false)

        initNetwork()
        setAdapter()

        return binding.root
    }

    private fun initNetwork(){
        DonutSharedPreferences.getAccessToken()?.let { viewModel.requestReceiverHistoryInfo(it) }
    }

    private fun setAdapter(){
        itemAdapter = UnusedItemAdapter()
        binding.rvUnusedItem.adapter = itemAdapter
        binding.rvUnusedItem.layoutManager = GridLayoutManager(context, 2)
        setDataList()
    }

    private fun setDataList() {
        viewModel.receiverHistoryInfo.observe(viewLifecycleOwner, Observer { data ->
            with(binding.rvUnusedItem.adapter as UnusedItemAdapter) {
                data.data!!.giftList?.let { itemAdapter.setGiftItemList(it) }
            }
        })
    }

    companion object {
    }
}