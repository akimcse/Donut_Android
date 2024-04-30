package org.gdsc.donut.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import org.gdsc.donut.data.DonutSharedPreferences
import org.gdsc.donut.databinding.FragmentGiverWalletBinding
import org.gdsc.donut.ui.GiverMainActivity
import org.gdsc.donut.ui.home.adpater.DetailItemAdapter
import org.gdsc.donut.ui.home.adpater.PackageItemAdapter
import org.gdsc.donut.ui.home.adpater.WalletDetailItemAdapter
import org.gdsc.donut.ui.viewModel.HomeViewModel


class GiverWalletFragment : Fragment() {
    private lateinit var binding: FragmentGiverWalletBinding
    private lateinit var itemAdapter: WalletDetailItemAdapter
    private val viewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGiverWalletBinding.inflate(inflater, container, false)

        initNetwork()
        getGiverWalletInfo()
        setAdapter()

        return binding.root
    }

    private fun initNetwork(){
        DonutSharedPreferences.getAccessToken()?.let { viewModel.requestGiverWalletInfo(it) }
    }

    private fun getGiverWalletInfo(){
        viewModel.giverWalletInfo.observe(viewLifecycleOwner, Observer { data ->
            binding.tvChildrenNum.text = data.data!!.receivers.toString()
            binding.tvDollarNum.text = data.data.amount.toString()
            binding.tv7Num.text = data.data.sevenEleven.toString()
            binding.tvCuNum.text = data.data.cu.toString()
            binding.tvGsNum.text = data.data.gs25.toString()
        })
    }

    private fun setAdapter(){
        itemAdapter = WalletDetailItemAdapter()
        binding.rvGiftItem.adapter = itemAdapter
        binding.rvGiftItem.layoutManager = GridLayoutManager(context, 2)

        itemAdapter.setOnItemClickListener { _, pos ->
            viewModel.setGiftId(itemAdapter.itemList[itemAdapter.mPosition].giftId)
            (activity as GiverMainActivity).changeFragment("wallet_detail")
        }
        setDataList()
    }

    private fun setDataList() {
        viewModel.giverWalletInfo.observe(viewLifecycleOwner, Observer { data ->
            with(binding.rvGiftItem.adapter as WalletDetailItemAdapter) {
                data.data!!.giftList.let {
                    if (it != null) {
                        itemAdapter.setGiftItemList(it)
                    }
                }
            }
        })
    }
}