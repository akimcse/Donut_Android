package org.gdsc.donut.ui.ranking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.gdsc.donut.databinding.FragmentSumBinding

class SumFragment : Fragment() {
    private lateinit var binding: FragmentSumBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSumBinding.inflate(inflater, container, false)

        //함수 호출

        return binding.root
    }

    //함수 작성

    companion object {
    }
}