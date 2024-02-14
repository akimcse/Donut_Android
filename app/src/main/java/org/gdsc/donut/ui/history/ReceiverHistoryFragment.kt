package org.gdsc.donut.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import org.gdsc.donut.databinding.FragmentReceiverHistoryBinding
import org.gdsc.donut.ui.history.adapter.HistoryViewPagerAdapter

class ReceiverHistoryFragment : Fragment() {
    private lateinit var binding: FragmentReceiverHistoryBinding
    private lateinit var itemViewPagerAdapter: HistoryViewPagerAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReceiverHistoryBinding.inflate(inflater, container, false)

        setViewPager()
        initTabLayout()

        return binding.root
    }

    private fun setViewPager(){
        val fragmentList = listOf(UnusedFragment(), UsedFragment())

        itemViewPagerAdapter = HistoryViewPagerAdapter(this.requireActivity())
        itemViewPagerAdapter.fragments.addAll(fragmentList)

        binding.vpItems.adapter = itemViewPagerAdapter
    }

    private fun initTabLayout(){
        val tabLabel = listOf("unused", "used")
        TabLayoutMediator(binding.tlMenu, binding.vpItems){tab, position -> tab.text = tabLabel[position]}.attach()
    }

    companion object {
    }
}