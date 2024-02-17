package org.gdsc.donut.ui.home.adpater

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.gdsc.donut.data.local.PackageItemData
import org.gdsc.donut.data.local.UnusedItemData
import org.gdsc.donut.data.remote.response.home.ResponseHomeReceiverBox
import org.gdsc.donut.data.remote.response.home.ResponseHomeReceiverBoxItemData
import org.gdsc.donut.data.remote.response.home.ResponseHomeReceiverGift
import org.gdsc.donut.databinding.ItemHistoryUnusedBinding
import org.gdsc.donut.databinding.ItemPackageBinding
import org.gdsc.donut.util.DonutUtil

class DetailItemAdapter : RecyclerView.Adapter<DetailItemAdapter.DetailItemViewHolder>() {
    var itemList = emptyList<ResponseHomeReceiverGift>()
    private var listener: ((ResponseHomeReceiverGift, Int) -> Unit)? = null
    var mPosition = 0

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailItemViewHolder {val binding = ItemHistoryUnusedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return DetailItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailItemViewHolder, position: Int) {
        holder.onBind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    inner class DetailItemViewHolder(private val binding: ItemHistoryUnusedBinding): RecyclerView.ViewHolder(binding.root){
        fun onBind(data: ResponseHomeReceiverGift){
            val date = data.dueDate.substring(0, 10)
            binding.tvDayNum.text = DonutUtil().getDDayInfo(date).toString()
            binding.tvCalendar.text = DonutUtil().setCalendarFormat(date)
            binding.tvName.text = data.product
            binding.tvDollar.text = data.price.toString()

            setClinkListenerOnPosition(data)
        }

        private fun setClinkListenerOnPosition(data: ResponseHomeReceiverGift){
            binding.clUnusedItem.setOnClickListener {
                val pos = adapterPosition
                mPosition = pos
                listener?.invoke(data, mPosition)
            }
        }
    }

    fun setOnItemClickListener(listener: ((ResponseHomeReceiverGift, Int) -> Unit)?) {
        this.listener = listener
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setGiftItemList(data: List<ResponseHomeReceiverGift>) {
        this.itemList = data
        notifyDataSetChanged()
    }

    /*
    override fun getItemViewType(position: Int): Int {
        return when (itemList[position].isUsed) {
            true -> PackageItemAdapter.OptionViewType.USED
            false -> PackageItemAdapter.OptionViewType.UNUSED
        }
    }

    object OptionViewType {
        const val USED = 1
        const val UNUSED = 2
    }
*/
}