package org.gdsc.donut.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.gdsc.donut.databinding.FragmentGiverHistoryUnusedBinding

class GiverHistoryUnusedFragment : Fragment() {
    private lateinit var binding: FragmentGiverHistoryUnusedBinding
    private lateinit var itemAdapter: UnusedItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGiverHistoryUnusedBinding.inflate(inflater, container, false)

        setAdapter()

        return binding.root
    }

    private fun setAdapter(){
        itemAdapter = UnusedItemAdapter()
        binding.rvUnusedItem.adapter = itemAdapter
    }

    companion object {
    }
}