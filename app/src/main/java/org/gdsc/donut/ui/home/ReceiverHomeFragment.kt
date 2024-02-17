package org.gdsc.donut.ui.home

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
import org.gdsc.donut.databinding.FragmentReceiverHomeBinding
import org.gdsc.donut.ui.ReceiverMainActivity
import org.gdsc.donut.ui.home.adpater.PackageItemAdapter
import org.gdsc.donut.ui.viewModel.HomeViewModel

class ReceiverHomeFragment : Fragment() {
    private lateinit var binding: FragmentReceiverHomeBinding
    private lateinit var itemAdapter: PackageItemAdapter
    private val viewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReceiverHomeBinding.inflate(inflater, container, false)

        initNetwork()
        getReceiverHomeInfo()
        setAdapter()

        return binding.root
    }

    private fun initNetwork(){
        DonutSharedPreferences.getAccessToken()?.let { viewModel.requestReceiverHomeInfo(it) }
    }

    private fun getReceiverHomeInfo(){
        viewModel.receiverHomeInfo.observe(viewLifecycleOwner, Observer { data ->
            if(data.data!!.availability) {
                binding.tvTitle.visibility = View.VISIBLE
                binding.imageView4.visibility = View.VISIBLE
                binding.imageView5.visibility = View.INVISIBLE
            } else {
                binding.tvTitle.visibility = View.GONE
                binding.imageView5.visibility = View.VISIBLE
                binding.imageView4.visibility = View.INVISIBLE
            }

            binding.tvDollarNum.text = data.data.amount.toString()
            binding.tv7Num.text = data.data.sevenEleven.toString()
            binding.tvCuNum.text = data.data.cu.toString()
            binding.tvGsNum.text = data.data.gs25.toString()
        })
    }

    private fun setAdapter(){
        itemAdapter = PackageItemAdapter()
        binding.rvPackageItem.adapter = itemAdapter
        binding.rvPackageItem.layoutManager = GridLayoutManager(context, 2)

        itemAdapter.setOnItemClickListener { _, pos ->
            for (changePos in itemAdapter.itemList.indices) {
                viewModel.setBoxId(itemAdapter.itemList[itemAdapter.mPosition].boxId)
                (activity as ReceiverMainActivity).changeFragment("package_detail")
            }
        }
        setDataList()
    }

    private fun setDataList() {
        viewModel.receiverHomeInfo.observe(viewLifecycleOwner, Observer { data ->
            with(binding.rvPackageItem.adapter as PackageItemAdapter) {
                data.data!!.boxList?.let { itemAdapter.setBoxItemList(it) }
            }
        })
    }

    companion object {
    }
}