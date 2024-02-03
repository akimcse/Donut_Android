package org.gdsc.donut.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import org.gdsc.donut.data.local.UnusedItemData
import org.gdsc.donut.databinding.FragmentUnusedBinding
import org.gdsc.donut.ui.history.adapter.UnusedItemAdapter

class UnusedFragment : Fragment() {
    private lateinit var binding: FragmentUnusedBinding
    private lateinit var itemAdapter: UnusedItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUnusedBinding.inflate(inflater, container, false)

        setAdapter()

        return binding.root
    }

    private fun setAdapter(){
        itemAdapter = UnusedItemAdapter()
        binding.rvUnusedItem.adapter = itemAdapter
        binding.rvUnusedItem.layoutManager = GridLayoutManager(context, 2)
        itemAdapter.itemList.addAll(
            listOf(
                UnusedItemData("34", "chupa chups", "19.94"),
                UnusedItemData("34", "chupa chups", "19.94"),
                UnusedItemData("34", "chupa chups", "19.94"),
                UnusedItemData("34", "chupa chups", "19.94"),
                UnusedItemData("34", "chupa chups", "19.94"),
                UnusedItemData("34", "chupa chups", "19.94"),
            )
        )
    }

    companion object {
    }
}