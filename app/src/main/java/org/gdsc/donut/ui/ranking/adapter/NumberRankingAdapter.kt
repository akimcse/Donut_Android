package org.gdsc.donut.ui.ranking.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.gdsc.donut.data.local.NumberRankingData
import org.gdsc.donut.data.local.SumRankingData
import org.gdsc.donut.databinding.ItemRankingNumberBinding
import org.gdsc.donut.databinding.ItemRankingSumBinding

class NumberRankingAdapter : RecyclerView.Adapter<NumberRankingAdapter.NumberRankingViewHolder>() {
    val itemList = mutableListOf<NumberRankingData>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NumberRankingViewHolder {val binding = ItemRankingNumberBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return NumberRankingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NumberRankingViewHolder, position: Int) {
        holder.onBind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    class NumberRankingViewHolder(private val binding: ItemRankingNumberBinding): RecyclerView.ViewHolder(binding.root){
        fun onBind(data: NumberRankingData){
            binding.tvRankingNum.text = data.ranking.toString()
            binding.tvName.text = data.name
            binding.tvNumberNum.text = data.Number
        }
    }
}