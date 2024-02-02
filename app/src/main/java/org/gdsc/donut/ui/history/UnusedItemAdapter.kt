package org.gdsc.donut.ui.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.gdsc.donut.data.local.UnusedItemData
import org.gdsc.donut.databinding.ItemHistoryUnusedBinding

class UnusedItemAdapter : RecyclerView.Adapter<UnusedItemAdapter.UnusedItemViewHolder>() {
    private val itemList = mutableListOf<UnusedItemData>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UnusedItemViewHolder {val binding = ItemHistoryUnusedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return UnusedItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UnusedItemViewHolder, position: Int) {
        holder.onBind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    class UnusedItemViewHolder(private val binding: ItemHistoryUnusedBinding): RecyclerView.ViewHolder(binding.root){
        fun onBind(data: UnusedItemData){
            binding.tvDay.text = data.name
            // 바인딩..
        }
    }
}