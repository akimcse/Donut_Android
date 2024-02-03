package org.gdsc.donut.ui.ranking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import org.gdsc.donut.data.local.NumberRankingData
import org.gdsc.donut.databinding.FragmentNumberBinding
import org.gdsc.donut.ui.ranking.adapter.NumberRankingAdapter

class NumberFragment : Fragment() {
    private lateinit var binding: FragmentNumberBinding
    private lateinit var itemAdapter: NumberRankingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNumberBinding.inflate(inflater, container, false)

        setAdapter()

        return binding.root
    }

    private fun setAdapter() {
        itemAdapter = NumberRankingAdapter()
        binding.rvRanking.adapter = itemAdapter
        binding.rvRanking.layoutManager = LinearLayoutManager(context)
        itemAdapter.itemList.addAll(
            listOf(
                NumberRankingData(2, "user1", "750"),
                NumberRankingData(3, "user2", "740"),
                NumberRankingData(4, "user3", "730"),
                NumberRankingData(5, "user4", "70"),
                NumberRankingData(6, "user5", "1"),
                NumberRankingData(7, "user6", "0"),
            )
        )
    }


    companion object {
    }
}