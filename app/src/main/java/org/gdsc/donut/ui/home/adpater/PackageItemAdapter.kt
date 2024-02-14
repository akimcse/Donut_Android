package org.gdsc.donut.ui.home.adpater

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.gdsc.donut.data.local.PackageItemData
import org.gdsc.donut.databinding.ItemPackageBinding

class PackageItemAdapter : RecyclerView.Adapter<PackageItemAdapter.PackageItemViewHolder>() {
    val itemList = mutableListOf<PackageItemData>()

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

    class PackageItemViewHolder(private val binding: ItemPackageBinding): RecyclerView.ViewHolder(binding.root){
        fun onBind(data: PackageItemData){
            binding.tvDayNum.text = data.day
            binding.tvCalendar.text = data.day
            binding.tvName.text = data.name
            binding.tvDollar.text = data.price
        }
    }
}