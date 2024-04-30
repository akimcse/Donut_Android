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
import org.gdsc.donut.ui.home.adpater.WalletImpedingItemAdapter
import org.gdsc.donut.ui.home.adpater.WalletGiftItemAdapter
import org.gdsc.donut.ui.viewModel.DonationViewModel
import org.gdsc.donut.ui.viewModel.HomeViewModel


class GiverWalletFragment : Fragment() {
    private lateinit var binding: FragmentGiverWalletBinding
    private lateinit var giftItemAdapter: WalletGiftItemAdapter
    private lateinit var impendingItemAdapter: WalletImpedingItemAdapter
    private val viewModel: HomeViewModel by activityViewModels()
    private val donationViewModel: DonationViewModel by activityViewModels()

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
        impendingItemAdapter = WalletImpedingItemAdapter(donationViewModel)
        binding.rvImminentGiftItem.adapter = impendingItemAdapter
        giftItemAdapter = WalletGiftItemAdapter()
        binding.rvGiftItem.adapter = giftItemAdapter
        binding.rvGiftItem.layoutManager = GridLayoutManager(context, 2)

        giftItemAdapter.setOnItemClickListener { _, pos ->
            viewModel.setGiftId(giftItemAdapter.itemList[giftItemAdapter.mPosition].giftId)
            (activity as GiverMainActivity).changeFragment("wallet_detail")
        }
        setDataList()
    }

    private fun setDataList() {
        viewModel.giverWalletInfo.observe(viewLifecycleOwner, Observer { data ->
            with(binding.rvGiftItem.adapter as WalletGiftItemAdapter) {
                data.data!!.giftList.let { if (it != null) giftItemAdapter.setGiftItemList(it) }
            }
        })

        viewModel.giverWalletInfo.observe(viewLifecycleOwner, Observer { data ->
            with(binding.rvImminentGiftItem.adapter as WalletImpedingItemAdapter) {
                data.data!!.impendingList.let { if (it != null) impendingItemAdapter.setImpendingItemList(it) }
            }
        })
    }
}