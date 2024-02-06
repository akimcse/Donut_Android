package org.gdsc.donut.ui.donation

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import org.gdsc.donut.databinding.FragmentDonationBinding
import org.gdsc.donut.ui.GiverMainActivity

class DonationFragment : Fragment() {
    private lateinit var binding: FragmentDonationBinding
    private val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        if (uri != null) { setImage(uri) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDonationBinding.inflate(inflater, container, false)

        setUploadButton()

        return binding.root
    }

    private fun setUploadButton() {
        binding.btnUpload.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
    }

    private fun setImage(uri: Uri) {
        binding.ivGifticon.setImageURI(uri)
        binding.ivGifticon.visibility = View.VISIBLE
        binding.btnDonate.visibility = View.VISIBLE
        setDonateButton()
    }

    private fun setDonateButton() {
        binding.btnDonate.setOnClickListener {
            getImageInfo()
            requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
            (activity as GiverMainActivity).changeFragment("donation_confirm") // 화면 전환 시 이름, 금액, 기한, 사용처, 사진 uri 담아서 전환
        }
    }

    private fun getImageInfo() {
        // OCR 사용해서 정보 추출
    }

    companion object {
    }
}