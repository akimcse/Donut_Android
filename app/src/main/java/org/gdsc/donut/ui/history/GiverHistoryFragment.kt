package org.gdsc.donut.ui.history

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import org.gdsc.donut.R
import org.gdsc.donut.databinding.ActivityCameraBinding.bind
import org.gdsc.donut.databinding.ActivityCameraBinding.inflate
import org.gdsc.donut.databinding.FragmentGiverHistoryBinding
import org.gdsc.donut.ui.history.adapter.MonthAdapter

class GiverHistoryFragment : Fragment() {
    private lateinit var binding: FragmentGiverHistoryBinding
    private lateinit var itemAdapter: MonthAdapter
    private var filteredYear = ""
    private var filteredMonth = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGiverHistoryBinding.inflate(inflater, container, false)

        setDropDownMenu()
        setChipAdapter()

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
        itemAdapter = MonthAdapter()
        binding.rvMonths.adapter = itemAdapter

        itemAdapter.setOnItemClickListener { _, pos ->
            filteredMonth = itemAdapter.itemList[pos]
        }
    }
}