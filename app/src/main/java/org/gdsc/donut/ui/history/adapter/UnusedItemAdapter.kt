package org.gdsc.donut.ui.history.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.gdsc.donut.data.local.UnusedItemData
import org.gdsc.donut.data.remote.response.home.ResponseHistoryReceiver
import org.gdsc.donut.data.remote.response.home.ResponseHistoryReceiverGift
import org.gdsc.donut.data.remote.response.home.ResponseHomeReceiverGift
import org.gdsc.donut.databinding.ItemHistoryUnusedBinding
import org.gdsc.donut.util.DonutUtil

class UnusedItemAdapter : RecyclerView.Adapter<UnusedItemAdapter.UnusedItemViewHolder>() {
    var itemList = mutableListOf<ResponseHistoryReceiverGift>()

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

    inner class UnusedItemViewHolder(private val binding: ItemHistoryUnusedBinding): RecyclerView.ViewHolder(binding.root){
        fun onBind(data: ResponseHistoryReceiverGift){
            val date = data.dueDate.substring(0, 10)
            if(data.isUsed == "UNUSED"){
                binding.tvDayNum.text = DonutUtil().getDDayInfo(date).toString()
                binding.tvCalendar.text = DonutUtil().setCalendarFormat(date)
                binding.tvName.text = data.product
                binding.tvDollar.text = data.price.toString()
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setGiftItemList(data: List<ResponseHistoryReceiverGift>) {
        itemList.clear()
        itemList.addAll(data.filter { it.isUsed == "UNUSED" })
        notifyDataSetChanged()
    }
}