package org.gdsc.donut.ui.sign

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import org.gdsc.donut.databinding.ActivitySignUpConfirmBinding

class SignUpConfirmActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpConfirmBinding
    private val pickMedia =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                setImage(uri)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpConfirmBinding.inflate(layoutInflater)

        setUploadButton()

        setContentView(binding.root)
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
            .into(binding.ivCard)
        binding.ivCard.visibility = View.VISIBLE
        setContinueBtn()
    }


    private fun setContinueBtn() {
        binding.btnContinue.visibility = View.VISIBLE
        binding.btnContinue.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}