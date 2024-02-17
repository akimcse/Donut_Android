package org.gdsc.donut.ui.ranking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import org.gdsc.donut.data.DonutSharedPreferences
import org.gdsc.donut.data.local.SumRankingData
import org.gdsc.donut.databinding.FragmentSumBinding
import org.gdsc.donut.ui.history.adapter.UnusedItemAdapter
import org.gdsc.donut.ui.ranking.adapter.SumRankingAdapter
import org.gdsc.donut.ui.viewModel.HistoryViewModel
import org.gdsc.donut.ui.viewModel.RankingViewModel

class SumFragment : Fragment() {
    private lateinit var binding: FragmentSumBinding
    private lateinit var itemAdapter: SumRankingAdapter
    private val viewModel: RankingViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSumBinding.inflate(inflater, container, false)

        initNetwork()
        setAdapter()

        return binding.root
    }

    private fun initNetwork(){
        DonutSharedPreferences.getAccessToken()?.let { viewModel.requestPriceRankingInfo(it) }
    }

    private fun setAdapter() {
        itemAdapter = SumRankingAdapter()
        binding.rvRanking.adapter = itemAdapter
        binding.rvRanking.layoutManager = LinearLayoutManager(context)
        setDataList()
    }

    private fun setDataList() {
        viewModel.priceRankingInfo.observe(viewLifecycleOwner, Observer { data ->
            with(binding.rvRanking.adapter as SumRankingAdapter) {
                data.data!!.let { itemAdapter.setPriceRankingItemList(it) }
            }
            binding.tvName.text = data.data?.get(0)!!.name
            binding.tvDollar.text = data.data?.get(0)!!.price.toString()
        })
    }


    companion object {
    }
}