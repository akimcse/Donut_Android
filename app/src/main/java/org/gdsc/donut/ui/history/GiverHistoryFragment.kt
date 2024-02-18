package org.gdsc.donut.ui.history

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import org.gdsc.donut.R
import org.gdsc.donut.databinding.FragmentGiverHistoryBinding

class GiverHistoryFragment : Fragment() {
    private lateinit var binding: FragmentGiverHistoryBinding
    private var filteredYear = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGiverHistoryBinding.inflate(inflater, container, false)

        setDropDownMenu()

        return binding.root
    }

    @SuppressLint("ResourceType")
    private fun setDropDownMenu(){
        val years = arrayOf("2024", "2023", "2022", "2021")
        val yearSpinner = binding.spnYearMenu
        val spinnerAdapter: ArrayAdapter<String>? = context?.let { ArrayAdapter(it, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, years) }
        spinnerAdapter?.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)
        yearSpinner.adapter = spinnerAdapter
        yearSpinner.onItemClickListener = AdapterView.OnItemClickListener { p0, p1, p2, p3 -> filteredYear = p0?.getItemAtPosition(p2) as String }
    }

    companion object {
    }
}