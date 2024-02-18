package org.gdsc.donut.ui.donation

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
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
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.gdsc.donut.R
import org.gdsc.donut.data.DonutSharedPreferences
import org.gdsc.donut.databinding.FragmentDonationBinding
import org.gdsc.donut.ui.GiverMainActivity
import org.gdsc.donut.ui.viewModel.DonationViewModel
import org.gdsc.donut.ui.viewModel.RankingViewModel
import java.io.File

class DonationFragment : Fragment() {
    private lateinit var binding: FragmentDonationBinding
    private val viewModel: DonationViewModel by activityViewModels()

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
            store = "SEVENELEVEN"
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

    private fun uriToFile(uri: Uri): File? {
        val cursor: Cursor? = context?.contentResolver?.query(uri, null, null, null, null)
        cursor?.use {
            if (it.moveToFirst()) {
                val filePathColumnIndex = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                val filePath = it.getString(filePathColumnIndex)
                return File(filePath)
            }
        }
        return null
    }

    private fun setImage(uri: Uri) {
        Glide.with(this)
            .load(uri)
            .centerCrop()
            .into(binding.ivGifticon)
        binding.ivGifticon.visibility = View.VISIBLE
        img = uriToFile(uri)?.path ?: ""

        setDonateButton()
    }

    private fun setDonateButton() {
        if (!binding.etName.text.isNullOrBlank() && !binding.etAmount.text.isNullOrBlank() && !binding.etDue.text.isNullOrBlank() && store.isNotBlank() && img.isNotBlank()) {
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
        val requestFile = RequestBody.create("image/jpeg".toMediaTypeOrNull(), File(img))
        val giftImage = MultipartBody.Part.createFormData("giftImage", File(img).name, requestFile)
        val product = binding.etName.text.toString().toRequestBody("text/plain".toMediaTypeOrNull())
        val price = binding.etAmount.text.toString().toInt()
        val dueDate = (binding.etDue.text.toString()+"T00:00:00.000000").toRequestBody("text/plain".toMediaTypeOrNull())
        val store = store.toRequestBody("text/plain".toMediaTypeOrNull())

        DonutSharedPreferences.getAccessToken()?.let { viewModel.requestDonateGiver(it, giftImage, product, price, dueDate, store) }
    }
}