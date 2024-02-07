package org.gdsc.donut.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.gdsc.donut.databinding.FragmentGiverHomeBinding
import org.gdsc.donut.ui.GiverMainActivity
import kotlin.properties.Delegates

class GiverHomeFragment : Fragment() {
    private lateinit var binding: FragmentGiverHomeBinding
    private var donatedAmount by Delegates.notNull<Double>()
    private var requiredAmount by Delegates.notNull<Double>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGiverHomeBinding.inflate(inflater, container, false)

        setAmount()
        setProgressBar()
        setSpeechBubble()
        setFloatingButton()

        return binding.root
    }

    private fun setAmount() {
        // 나중에 서버에서 값 받아오기
        donatedAmount = 200.0
        requiredAmount = 1000.0
    }

    private fun setProgressBar() {
        val percentage = (donatedAmount / requiredAmount * 100)
        binding.progressBar.progress = percentage.toInt()
        if (percentage >= 0 && percentage < 20) {
            setProgressBarPointer(300)
        } else if (percentage >= 20 && percentage < 40) {
            setProgressBarPointer(340)
        } else if (percentage >= 40 && percentage < 80) {
            setProgressBarPointer(350)
        } else if (percentage >= 80) {
            setProgressBarPointer(355)
        }
    }

    private fun setProgressBarPointer(ratio: Int) {
        val percentage = (donatedAmount / requiredAmount * 100)
        val percentage360 = percentage / 100 * ratio
        binding.tvPercentNum.translationX = percentage360.toFloat()
        binding.tvPercent.translationX = percentage360.toFloat()
        binding.ivPointer.translationX = percentage360.toFloat()
    }

    private fun setSpeechBubble() {
        binding.tvDonatedNum.text = donatedAmount.toInt().toString()
        binding.tvNeedNum.text = requiredAmount.toInt().toString()

        binding.ivDonut.setOnClickListener {
            if(binding.clSpeechBubble.visibility == View.INVISIBLE) binding.clSpeechBubble.visibility = View.VISIBLE
            else binding.clSpeechBubble.visibility = View.INVISIBLE
        }
    }

    private fun setFloatingButton(){
        binding.fabDonationBtn.setOnClickListener {
            (activity as GiverMainActivity).changeFragment("donation")
        }
    }

    companion object {
    }
}