package org.gdsc.donut.ui.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import org.gdsc.donut.databinding.FragmentMyPageBinding

class MyPageFragment : Fragment() {
    private lateinit var binding: FragmentMyPageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyPageBinding.inflate(inflater, container, false)

        setClickListener()

        return binding.root
    }

    private fun setClickListener(){
        binding.clProfiles.setOnClickListener {
            Toast.makeText(activity, "준비중입니다.", Toast.LENGTH_SHORT).show()
        }
        binding.clService.setOnClickListener {
            Toast.makeText(activity, "준비중입니다.", Toast.LENGTH_SHORT).show()
        }
        binding.clOpenSource.setOnClickListener {
            Toast.makeText(activity, "준비중입니다.", Toast.LENGTH_SHORT).show()
        }
        binding.clAbout.setOnClickListener {
            Toast.makeText(activity, "준비중입니다.", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
    }
}