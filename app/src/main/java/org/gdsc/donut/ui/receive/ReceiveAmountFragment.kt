package org.gdsc.donut.ui.receive

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.fragment.app.Fragment
import org.gdsc.donut.R
import org.gdsc.donut.databinding.FragmentReceiveAmountBinding
import org.gdsc.donut.ui.ReceiverMainActivity
import org.gdsc.donut.ui.sign.CameraActivity

class ReceiveAmountFragment : Fragment() {
    private lateinit var binding: FragmentReceiveAmountBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReceiveAmountBinding.inflate(inflater, container, false)

        checkAmountStatus()

        return binding.root
    }

    private fun checkAmountStatus() {
        binding.etAmount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.clAmount.setBackgroundDrawable(context?.let {getDrawable(it, R.drawable.bg_white_maincoral_round8)})
            }

            override fun afterTextChanged(p0: Editable?) {
                if (binding.etAmount.text.isNullOrBlank()) {
                    binding.clAmount.setBackgroundDrawable(context?.let {getDrawable(it, R.drawable.bg_white_black_round8)})
                }
                setContinueButton()
            }
        })
    }

    @SuppressLint("ResourceAsColor")
    private fun setContinueButton() {
        binding.btnDone.setBackgroundDrawable(context?.let {getDrawable(it,R.drawable.bg_coral_round)})
        binding.tvDone.text = getString(R.string.receive_done)
        binding.tvDone.setTextColor(resources.getColor(R.color.white))
        binding.btnDone.setOnClickListener {
            // sendReceiveInfo()
            requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
            startActivity(Intent(context, ReceiveDoneActivity::class.java))
        }
    }

    private fun sendReceiveInfo() {
        // api 리스폰스에 사용처, 금액 담아서 전송
    }
}