package org.gdsc.donut.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import org.gdsc.donut.databinding.FragmentGiverHistoryBinding
import org.gdsc.donut.ui.GiverMainActivity
import org.gdsc.donut.ui.history.adapter.HistoryViewPagerAdapter

class GiverHistoryFragment : Fragment() {
    private lateinit var binding: FragmentGiverHistoryBinding
    private lateinit var itemViewPagerAdapter: HistoryViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGiverHistoryBinding.inflate(inflater, container, false)

        setViewPager()
        initTabLayout()
        setFloatingButton()

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

    private fun setFloatingButton(){
        binding.fabDonationBtn.setOnClickListener {
            (activity as GiverMainActivity).changeFragment("donation")
        }
    }

    companion object {
    }
}