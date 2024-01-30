package org.gdsc.donut.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.gdsc.donut.databinding.FragmentReceiverHomeBinding

class ReceiverHomeFragment : Fragment() {
    private lateinit var binding: FragmentReceiverHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReceiverHomeBinding.inflate(inflater, container, false)

        //함수 호출

        return binding.root
    }

    //함수 작성

    companion object {
    }
}