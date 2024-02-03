package org.gdsc.donut.ui.ranking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.gdsc.donut.R
import org.gdsc.donut.databinding.FragmentNumberBinding
import org.gdsc.donut.databinding.FragmentSumBinding

class NumberFragment : Fragment() {
    private lateinit var binding: FragmentNumberBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNumberBinding.inflate(inflater, container, false)

        //함수 호출

        return binding.root
    }

    //함수 작성

    companion object {
    }
}