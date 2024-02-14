package org.gdsc.donut.ui.home.adpater

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.gdsc.donut.data.local.PackageItemData
import org.gdsc.donut.databinding.ItemPackageBinding

class PackageItemAdapter : RecyclerView.Adapter<PackageItemAdapter.PackageItemViewHolder>() {
    val itemList = mutableListOf<PackageItemData>()
    private var listener: ((PackageItemData, Int) -> Unit)? = null
    var mPosition = 0

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PackageItemViewHolder {val binding = ItemPackageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return PackageItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PackageItemViewHolder, position: Int) {
        holder.onBind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    inner class PackageItemViewHolder(private val binding: ItemPackageBinding): RecyclerView.ViewHolder(binding.root){
        fun onBind(data: PackageItemData){
            binding.tvDayNum.text = data.day
            binding.tvCalendar.text = data.day
            binding.tvName.text = data.name
            binding.tvDollar.text = data.price

            setClinkListenerOnPosition(data)
        }

        private fun setClinkListenerOnPosition(data: PackageItemData){
            binding.clPackageItem.setOnClickListener {
                val pos = adapterPosition
                mPosition = pos
                listener?.invoke(data, mPosition)
            }
        }
    }

    fun setOnItemClickListener(listener: ((PackageItemData, Int) -> Unit)?) {
        this.listener = listener
    }
}