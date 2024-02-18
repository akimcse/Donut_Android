package org.gdsc.donut.ui.donation

import android.annotation.SuppressLint
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
import androidx.core.content.ContextCompat.getColor
import androidx.core.content.ContextCompat.getDrawable
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import org.gdsc.donut.R
import org.gdsc.donut.databinding.FragmentDonationBinding
import org.gdsc.donut.ui.GiverMainActivity

class DonationFragment : Fragment() {
    private lateinit var binding: FragmentDonationBinding
    private var store = ""
    private var img = ""
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

        (activity as GiverMainActivity).enableFloatingButton()
        checkNameStatus()
        checkAmountStatus()
        checkDueDateStatus()
        setStoreList()
        setUploadButton()
        setDonateButton()

        return binding.root
    }

    private fun checkNameStatus() {
        binding.etName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //
            }

            @SuppressLint("UseCompatLoadingForDrawables")
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.etName.background.setTint(resources.getColor(R.color.main_coral))
                binding.etName.setTextColor(resources.getColor(R.color.black_100))
            }

            override fun afterTextChanged(p0: Editable?) {
                if (binding.etName.text.isNullOrBlank()) {
                    binding.etName.background.setTint(resources.getColor(R.color.main_coral))
                    binding.etName.setTextColor(resources.getColor(R.color.gray_300))
                }
                setDonateButton()
            }
        })
    }

    private fun checkAmountStatus() {
        binding.etAmount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //
            }

            @SuppressLint("UseCompatLoadingForDrawables")
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.etAmount.background.setTint(resources.getColor(R.color.main_coral))
                binding.etAmount.setTextColor(resources.getColor(R.color.black_100))
            }

            override fun afterTextChanged(p0: Editable?) {
                if (binding.etAmount.text.isNullOrBlank()) {
                    binding.etAmount.background.setTint(resources.getColor(R.color.main_coral))
                    binding.etAmount.setTextColor(resources.getColor(R.color.gray_300))
                }
                setDonateButton()
            }
        })
    }

    private fun checkDueDateStatus() {
        binding.etDue.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //
            }

            @SuppressLint("UseCompatLoadingForDrawables")
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.etDue.background.setTint(resources.getColor(R.color.main_coral))
                binding.etDue.setTextColor(resources.getColor(R.color.black_100))
            }

            override fun afterTextChanged(p0: Editable?) {
                if (binding.etDue.text.isNullOrBlank()) {
                    binding.etDue.background.setTint(resources.getColor(R.color.main_coral))
                    binding.etDue.setTextColor(resources.getColor(R.color.gray_300))
                }
                setDonateButton()
            }
        })
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
            store = "7 ELEVEN"
            setDonateButton()
        }

        binding.tvCu.setOnClickListener {
            binding.tv7eleven.setTextColor(resources.getColor(R.color.black_100))
            binding.tvCu.setTextColor(resources.getColor(R.color.main_coral))
            binding.tvGs25.setTextColor(resources.getColor(R.color.black_100))
            store = "CU"
            setDonateButton()
        }

        binding.tvGs25.setOnClickListener {
            binding.tv7eleven.setTextColor(resources.getColor(R.color.black_100))
            binding.tvCu.setTextColor(resources.getColor(R.color.black_100))
            binding.tvGs25.setTextColor(resources.getColor(R.color.main_coral))
            store = "GS25"
            setDonateButton()
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
        img = uri.toString()
        setDonateButton()
    }

    private fun setDonateButton() {
        if (!binding.etName.text.isNullOrBlank() && !binding.etAmount.text.isNullOrBlank() && !binding.etDue.text.isNullOrBlank() && store.isNotBlank() && img.toString().isNotBlank()) {
            if(store.isNotBlank() && img.isNotBlank()){
                binding.btnDonate.visibility = View.VISIBLE
                binding.btnDonate.setOnClickListener {
                    sendDonationInfo()
                    requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
                    startActivity(Intent(context, DonationDoneActivity::class.java))
                }
            }
        }
    }

    private fun sendDonationInfo() {
        Log.d("donation name", binding.etName.text.toString())
        Log.d("donation amount", binding.etAmount.text.toString())
        Log.d("donation due date", binding.etDue.text.toString())
        Log.d("donation store", store)
        Log.d("donation img", img)
    }

    companion object {
    }
}