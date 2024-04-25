package org.gdsc.donut.ui.mypage

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import org.gdsc.donut.data.DonutSharedPreferences
import org.gdsc.donut.databinding.FragmentReceiverMyPageBinding
import org.gdsc.donut.ui.GiverMainActivity
import org.gdsc.donut.ui.sign.SignActivity

class ReceiverMyPageFragment : Fragment() {
    private lateinit var binding: FragmentReceiverMyPageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReceiverMyPageBinding.inflate(inflater, container, false)

        setClickListener()

        return binding.root
    }

    private fun setClickListener(){
        binding.ivDonut.setOnClickListener {
            DonutSharedPreferences.setAccessToken("")
            requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
            startActivity(Intent(context, SignActivity::class.java))
        }

        binding.clHistory.setOnClickListener {
            (activity as GiverMainActivity).changeFragment("history_detail")
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