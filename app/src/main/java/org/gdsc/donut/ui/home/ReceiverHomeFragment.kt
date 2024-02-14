package org.gdsc.donut.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import org.gdsc.donut.data.local.PackageItemData
import org.gdsc.donut.databinding.FragmentReceiverHomeBinding
import org.gdsc.donut.ui.ReceiverMainActivity
import org.gdsc.donut.ui.home.adpater.PackageItemAdapter

class ReceiverHomeFragment : Fragment() {
    private lateinit var binding: FragmentReceiverHomeBinding
    private lateinit var itemAdapter: PackageItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReceiverHomeBinding.inflate(inflater, container, false)

        setAdapter()

        return binding.root
    }

    private fun setAdapter(){
        itemAdapter = PackageItemAdapter()
        binding.rvPackageItem.adapter = itemAdapter
        binding.rvPackageItem.layoutManager = GridLayoutManager(context, 2)
        itemAdapter.itemList.addAll(
            listOf(
                PackageItemData("34", "7 ELEVEN", "19.94"),
                PackageItemData("34", "7 ELEVEN", "19.94"),
                PackageItemData("34", "7 ELEVEN", "19.94"),
                PackageItemData("34", "7 ELEVEN", "19.94"),
                PackageItemData("34", "7 ELEVEN", "19.94"),
                PackageItemData("34", "7 ELEVEN", "19.94"),
                PackageItemData("34", "7 ELEVEN", "19.94"),
                PackageItemData("34", "7 ELEVEN", "19.94"),
            )
        )

        itemAdapter.setOnItemClickListener { _, pos ->
            for (changePos in itemAdapter.itemList.indices) {
                (activity as ReceiverMainActivity).changeFragment("package_detail")
            }
        }
    }

    companion object {
    }
}