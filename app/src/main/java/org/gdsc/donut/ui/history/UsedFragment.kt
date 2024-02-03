package org.gdsc.donut.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import org.gdsc.donut.data.local.UnusedItemData
import org.gdsc.donut.data.local.UsedItemData
import org.gdsc.donut.databinding.FragmentUsedBinding
import org.gdsc.donut.ui.history.adapter.UnusedItemAdapter
import org.gdsc.donut.ui.history.adapter.UsedItemAdapter


class UsedFragment : Fragment() {
    private lateinit var binding: FragmentUsedBinding
    private lateinit var itemAdapter: UsedItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUsedBinding.inflate(inflater, container, false)

        setAdapter()

        return binding.root
    }

    private fun setAdapter(){
        itemAdapter = UsedItemAdapter()
        binding.rvUnusedItem.adapter = itemAdapter
        binding.rvUnusedItem.layoutManager = GridLayoutManager(context, 2)
        itemAdapter.itemList.addAll(
            listOf(
                UsedItemData("34", "chupa chups", "19.94"),
                UsedItemData("34", "chupa chups", "19.94"),
                UsedItemData("34", "chupa chups", "19.94"),
                UsedItemData("34", "chupa chups", "19.94"),
                UsedItemData("34", "chupa chups", "19.94"),
                UsedItemData("34", "chupa chups", "19.94"),
            )
        )
    }

    companion object {
    }
}