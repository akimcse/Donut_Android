package org.gdsc.donut.ui.donation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import org.gdsc.donut.R
import org.gdsc.donut.databinding.FragmentDonationBinding

class DonationFragment : Fragment() {
    private lateinit var binding: FragmentDonationBinding
    private val pickMedia =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                setImage(uri)
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDonationBinding.inflate(inflater, container, false)

        setEditTextFocus()
        setStoreList()
        setUploadButton()
        setDonateButton()

        return binding.root
    }

    // 입력 여부에 따라 색 변하도록 다시 처리
    private fun setEditTextFocus() {
        binding.etName.onFocusChangeListener =
            View.OnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    binding.etName.background.setTint(resources.getColor(R.color.main_coral))
                } else {
                    binding.etName.background.setTint(resources.getColor(R.color.black_100))
                }
            }

        binding.etAmount.onFocusChangeListener =
            View.OnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    binding.etAmount.background.setTint(resources.getColor(R.color.main_coral))
                } else {
                    binding.etAmount.background.setTint(resources.getColor(R.color.black_100))
                }
            }

        binding.etDue.onFocusChangeListener =
            View.OnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    binding.etDue.background.setTint(resources.getColor(R.color.main_coral))
                } else {
                    binding.etDue.background.setTint(resources.getColor(R.color.black_100))
                }
            }
    }


    private fun setStoreList() {
        binding.clStore.setOnClickListener {
            binding.clStore.visibility = View.GONE
            binding.clStoreList.visibility = View.VISIBLE
            binding.tvCu.visibility = View.VISIBLE
            binding.tvGs25.visibility = View.VISIBLE
        }
        setStoreClickListener()
    }

    private fun setStoreClickListener() {
        binding.tv7eleven.setOnClickListener {
            binding.tv7eleven.setTextColor(resources.getColor(R.color.main_coral))
            binding.tvCu.setTextColor(resources.getColor(R.color.black_100))
            binding.tvGs25.setTextColor(resources.getColor(R.color.black_100))
        }

        binding.tvCu.setOnClickListener {
            binding.tv7eleven.setTextColor(resources.getColor(R.color.black_100))
            binding.tvCu.setTextColor(resources.getColor(R.color.main_coral))
            binding.tvGs25.setTextColor(resources.getColor(R.color.black_100))
        }

        binding.tvGs25.setOnClickListener {
            binding.tv7eleven.setTextColor(resources.getColor(R.color.black_100))
            binding.tvCu.setTextColor(resources.getColor(R.color.black_100))
            binding.tvGs25.setTextColor(resources.getColor(R.color.main_coral))
        }
    }

    private fun setUploadButton() {
        binding.btnUpload.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
    }

    private fun setImage(uri: Uri) {
        Glide.with(this)
            .load(uri)
            .centerCrop()
            .into(binding.ivGifticon)
        binding.ivGifticon.visibility = View.VISIBLE
    }

    private fun setDonateButton() {
        // 버튼 예외처리 서버 통신할 때 다시 보기
        if (!binding.etName.text.isNullOrEmpty() && !binding.etAmount.text.isNullOrEmpty() && !binding.etDue.text.isNullOrEmpty() && binding.clStore.visibility == View.GONE && binding.ivGifticon.visibility == View.VISIBLE) {
            binding.btnDonate.visibility = View.VISIBLE
            binding.btnDonate.setOnClickListener {
                //sendDonationInfo()
                requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
                startActivity(Intent(context, DonationDoneActivity::class.java))
            }
        }
    }

    private fun sendDonationInfo() {
        // api 리스폰스에 이름, 금액, 기한, 사용처, 사진 담아서 전송
    }

    companion object {
    }
}