package org.gdsc.donut.ui.sign

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import org.gdsc.donut.R
import org.gdsc.donut.data.DonutSharedPreferences
import org.gdsc.donut.databinding.ActivitySignBinding
import org.gdsc.donut.ui.ReceiverMainActivity
import org.gdsc.donut.ui.viewModel.SignViewModel

class SignActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignBinding
    private val viewModel: SignViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignBinding.inflate(layoutInflater)

        setContentView(binding.root)

        autoLogin()
        setContinueBtn()
        checkNameStatus()
        checkPasswordStatus()
    }



    private fun setContinueBtn() {
        binding.btnCreate.setOnClickListener {
            startActivity(Intent(this, SignUpConfirmActivity::class.java))
        }
    }

    private fun checkNameStatus() {
        binding.etUsername.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.clUsername.setBackgroundDrawable(getDrawable(R.drawable.bg_white_maincoral_round8))
                binding.etUsername.setTextColor(getColor(R.color.black_100))
            }

            override fun afterTextChanged(p0: Editable?) {
                if (binding.etUsername.text.isNullOrBlank()) {
                    binding.clUsername.setBackgroundDrawable(getDrawable(R.drawable.bg_white_black_round8))
                    binding.etUsername.setTextColor(getColor(R.color.gray_300))
                }
                setLoginBtn()
            }
        })
    }

    private fun checkPasswordStatus() {
        binding.etPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.clPassword.setBackgroundDrawable(getDrawable(R.drawable.bg_white_maincoral_round8))
                binding.etPassword.setTextColor(getColor(R.color.black_100))
            }

            override fun afterTextChanged(p0: Editable?) {
                if (binding.etPassword.text.isNullOrBlank()) {
                    binding.clPassword.setBackgroundDrawable(getDrawable(R.drawable.bg_white_black_round8))
                    binding.etPassword.setTextColor(getColor(R.color.gray_300))
                }
                setLoginBtn()
            }
        })
    }


}