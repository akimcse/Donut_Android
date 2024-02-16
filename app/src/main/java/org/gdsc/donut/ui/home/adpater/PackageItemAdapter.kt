package org.gdsc.donut.ui.home.adpater

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.gdsc.donut.data.remote.response.home.ResponseHomeReceiver
import org.gdsc.donut.databinding.ItemPackageBinding
import org.gdsc.donut.util.DonutUtil
import java.text.SimpleDateFormat
import java.util.Calendar

class PackageItemAdapter : RecyclerView.Adapter<PackageItemAdapter.PackageItemViewHolder>() {
    var itemList = emptyList<ResponseHomeReceiver>()
    private var listener: ((ResponseHomeReceiver, Int) -> Unit)? = null
    var mPosition = 0

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PackageItemViewHolder {
        val binding = ItemPackageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PackageItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PackageItemViewHolder, position: Int) {
        holder.onBind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    inner class PackageItemViewHolder(private val binding: ItemPackageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SimpleDateFormat")
        fun onBind(data: ResponseHomeReceiver) {
            data.data!!.boxList!!.forEach {
                val date = it.dueDate.substring(0, 10)
                binding.tvDayNum.text = DonutUtil().getDDayInfo(date).toString()
                binding.tvCalendar.text = DonutUtil().setCalendarFormat(date)
                binding.tvName.text = it.store
                binding.tvDollar.text = it.amount.toString()
            }
            setClinkListenerOnPosition(data)
        }

        private fun setClinkListenerOnPosition(data: ResponseHomeReceiver) {
            binding.clPackageItem.setOnClickListener {
                val pos = adapterPosition
                mPosition = pos
                listener?.invoke(data, mPosition)
            }
        }

    }

    fun setOnItemClickListener(listener: ((ResponseHomeReceiver, Int) -> Unit)?) {
        this.listener = listener
    }

    fun setItemList(data: ResponseHomeReceiver){
        this.itemList = listOf(data)
        notifyDataSetChanged()
    }
}