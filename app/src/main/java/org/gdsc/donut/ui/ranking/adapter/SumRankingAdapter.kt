package org.gdsc.donut.ui.ranking.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.gdsc.donut.data.local.SumRankingData
import org.gdsc.donut.data.local.UsedItemData
import org.gdsc.donut.databinding.ItemHistoryUsedBinding
import org.gdsc.donut.databinding.ItemRankingSumBinding

class SumRankingAdapter : RecyclerView.Adapter<SumRankingAdapter.SumRankingViewHolder>() {
    val itemList = mutableListOf<SumRankingData>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SumRankingViewHolder {val binding = ItemRankingSumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return SumRankingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SumRankingViewHolder, position: Int) {
        holder.onBind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    class SumRankingViewHolder(private val binding: ItemRankingSumBinding): RecyclerView.ViewHolder(binding.root){
        fun onBind(data: SumRankingData){
            binding.tvRankingNum.text = data.ranking.toString()
            binding.tvName.text = data.name
            binding.tvDollarNum.text = data.price
        }
    }
}