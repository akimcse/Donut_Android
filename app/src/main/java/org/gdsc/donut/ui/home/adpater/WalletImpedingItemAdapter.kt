package org.gdsc.donut.ui.home.adpater

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import org.gdsc.donut.data.remote.response.home.ResponseWalletGiftList
import org.gdsc.donut.data.remote.response.home.ResponseWalletImpendingList
import org.gdsc.donut.databinding.ItemHistoryUnusedBinding
import org.gdsc.donut.databinding.ItemWalletImminentBinding
import org.gdsc.donut.ui.viewModel.HomeViewModel
import org.gdsc.donut.util.DonutUtil

class WalletImpedingItemAdapter(private val viewModel: HomeViewModel) : RecyclerView.Adapter<WalletImpedingItemAdapter.UnusedItemViewHolder>() {
    var itemList = emptyList<ResponseWalletImpendingList>()
    private var listener: ((ResponseWalletImpendingList, Int) -> Unit)? = null
    var mPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):UnusedItemViewHolder {val binding = ItemWalletImminentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UnusedItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UnusedItemViewHolder, position: Int) {
        holder.onBind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    inner class UnusedItemViewHolder(private val binding: ItemWalletImminentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponseWalletImpendingList) {
            val date = data.dueDate.substring(0, 10)
            binding.tvDayNum.text = DonutUtil().getDDayInfo(date).toString()
            binding.tvCalendar.text = DonutUtil().setCalendarFormat(date)
            binding.tvName.text = data.product
            binding.tvDollar.text = data.price.toString()
            binding.clDonate.setOnClickListener {
                viewModel.
            }
            setClinkListenerOnPosition(data)
        }

        private fun setClinkListenerOnPosition(data: ResponseWalletImpendingList) {
            binding.clUnusedItem.setOnClickListener {
                val pos = adapterPosition
                mPosition = pos
                listener?.invoke(data, mPosition)
            }
        }
    }

    fun setOnItemClickListener(listener: ((ResponseWalletImpendingList, Int) -> Unit)?) {
        this.listener = listener
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setImpendingItemList(data: List<ResponseWalletImpendingList>) {
        this.itemList = data
        notifyDataSetChanged()
    }
}