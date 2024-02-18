package org.gdsc.donut.ui.history

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import org.gdsc.donut.data.DonutSharedPreferences
import org.gdsc.donut.databinding.FragmentGiverHistoryBinding
import org.gdsc.donut.ui.ReceiverMainActivity
import org.gdsc.donut.ui.history.adapter.HistoryAdapter
import org.gdsc.donut.ui.history.adapter.MonthAdapter
import org.gdsc.donut.ui.viewModel.HistoryViewModel
import java.time.LocalDateTime

class GiverHistoryFragment : Fragment() {
    private lateinit var binding: FragmentGiverHistoryBinding
    private lateinit var menuAdapter: MonthAdapter
    private lateinit var itemAdapter: HistoryAdapter
    private val viewModel: HistoryViewModel by activityViewModels()
    private var filteredYear = ""
    private var filteredMonth = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGiverHistoryBinding.inflate(inflater, container, false)

        setDropDownMenu()
        setChipAdapter()
        initNetwork()
        getReceiverHomeBoxInfo()
        setAdapter()

        return binding.root
    }

    @SuppressLint("ResourceType")
    private fun setDropDownMenu(){
        val years = arrayOf("2024", "2023", "2022", "2021")
        val yearSpinner = binding.spnYearMenu
        val spinnerAdapter: ArrayAdapter<String>? = context?.let { ArrayAdapter(it, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, years) }
        spinnerAdapter?.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)
        yearSpinner.adapter = spinnerAdapter
        yearSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                filteredYear = parent?.getItemAtPosition(position) as String
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun setChipAdapter() {
        menuAdapter = MonthAdapter()
        binding.rvMonths.adapter = menuAdapter

        menuAdapter.setOnItemClickListener { _, pos ->
            filteredMonth = menuAdapter.itemList[pos]
        }
    }

    private fun initNetwork(){
        val date = LocalDateTime.now().withDayOfMonth(1)
        //DonutSharedPreferences.getAccessToken()?.let {viewModel.requestGiverHistoryInfo(it, date)}
        viewModel.requestGiverHistoryInfo("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoia2FuZzZAZXdhaGluLm5ldCIsInJvbGUiOiJST0xFX0dJVkVSIiwiaWF0IjoxNzA4MDk5MzAxLCJleHAiOjE3MTA2OTEzMDF9.LU4jKp3CnWxuAuN2qH8pUKLZxUUnmdnVl74o1F1fDbg", date)
    }

    private fun getReceiverHomeBoxInfo(){
        viewModel.giverHistoryInfo.observe(viewLifecycleOwner, Observer { data ->
            binding.tvTitleYearNum.text = data.data!!.period.toString()
            binding.tvDollarNum.text = data.data.totalAmount.toString()
            binding.tvUnreceivedNum.text = data.data.unreceived.toString()
            binding.tvReceivedNum.text = data.data.received.toString()
            binding.tvMsgNum.text = data.data.msg.toString()
        })
    }

    private fun setAdapter() {
        itemAdapter = HistoryAdapter()
        binding.rvGiftItem.adapter = itemAdapter
        binding.rvGiftItem.layoutManager = GridLayoutManager(context, 2)

        itemAdapter.setOnItemClickListener { _, pos ->
            for (changePos in itemAdapter.itemList.indices) {
                viewModel.setGiftId(itemAdapter.itemList[itemAdapter.mPosition].giftId)
                (activity as ReceiverMainActivity).changeFragment("history_detail")
            }
        }
        setDataList()
    }

    private fun setDataList(){
        viewModel.giverHistoryInfo.observe(viewLifecycleOwner, Observer { data ->
            with(binding.rvGiftItem.adapter as HistoryAdapter) {
                data.data!!.donationList?.let { itemAdapter.setGiftItemList(it) }
            }
        })
    }
}