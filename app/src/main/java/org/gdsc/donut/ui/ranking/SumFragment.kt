package org.gdsc.donut.ui.ranking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import org.gdsc.donut.data.local.SumRankingData
import org.gdsc.donut.databinding.FragmentSumBinding
import org.gdsc.donut.ui.ranking.adapter.SumRankingAdapter

class SumFragment : Fragment() {
    private lateinit var binding: FragmentSumBinding
    private lateinit var itemAdapter: SumRankingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSumBinding.inflate(inflater, container, false)

        setAdapter()

        return binding.root
    }

    private fun setAdapter() {
        itemAdapter = SumRankingAdapter()
        binding.rvRanking.adapter = itemAdapter
        binding.rvRanking.layoutManager = LinearLayoutManager(context)
        itemAdapter.itemList.addAll(
            listOf(
                SumRankingData(2, "user1", "14,800"),
                SumRankingData(3, "user2", "1,480"),
                SumRankingData(4, "user3", "148"),
                SumRankingData(5, "user4", "14"),
                SumRankingData(6, "user5", "1"),
                SumRankingData(7, "user6", "0"),
                )
        )
    }


    companion object {
    }
}