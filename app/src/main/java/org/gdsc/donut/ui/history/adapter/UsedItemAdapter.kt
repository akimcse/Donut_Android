package org.gdsc.donut.ui.history.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.gdsc.donut.data.local.UnusedItemData
import org.gdsc.donut.data.local.UsedItemData
import org.gdsc.donut.databinding.ItemHistoryUnusedBinding
import org.gdsc.donut.databinding.ItemHistoryUsedBinding

class UsedItemAdapter : RecyclerView.Adapter<UsedItemAdapter.UsedItemViewHolder>() {
    val itemList = mutableListOf<UsedItemData>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UsedItemViewHolder {val binding = ItemHistoryUsedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return UsedItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UsedItemViewHolder, position: Int) {
        holder.onBind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    class UsedItemViewHolder(private val binding: ItemHistoryUsedBinding): RecyclerView.ViewHolder(binding.root){
        fun onBind(data: UsedItemData){
            binding.tvCalendar.text = data.day
            binding.tvName.text = data.name
            binding.tvDollar.text = data.price
        }
    }
}