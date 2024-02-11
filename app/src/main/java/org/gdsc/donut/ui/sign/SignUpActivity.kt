package org.gdsc.donut.ui.sign

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.gdsc.donut.R
import org.gdsc.donut.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkNameStatus()
        checkPasswordStatus()
        checkConfirmStatus()
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
                setCreateBtn()
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
                setCreateBtn()
            }
        })
    }

    private fun checkConfirmStatus() {
        binding.etConfirm.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.clPasswordCheck.setBackgroundDrawable(getDrawable(R.drawable.bg_white_maincoral_round8))
                binding.etConfirm.setTextColor(getColor(R.color.black_100))
            }

            override fun afterTextChanged(p0: Editable?) {
                if (binding.etConfirm.text.isNullOrBlank()) {
                    binding.clPasswordCheck.setBackgroundDrawable(getDrawable(R.drawable.bg_white_black_round8))
                    binding.etConfirm.setTextColor(getColor(R.color.gray_300))
                }
                setCreateBtn()
            }
        })
    }

    private fun setCreateBtn() {
        if (!binding.etUsername.text.isNullOrBlank() && !binding.etPassword.text.isNullOrBlank() && !binding.etConfirm.text.isNullOrBlank()) {
            if(binding.etPassword.text.toString() == binding.etConfirm.text.toString()){
                binding.btnCreate.setBackgroundDrawable(getDrawable(R.drawable.bg_coral_round))
                binding.tvContinue.setTextColor(getColor(R.color.white))
            }else{
                Toast.makeText(this, "패스워드를 확인해주세요.", Toast.LENGTH_SHORT).show()
            }

        } else {
            binding.btnCreate.setBackgroundDrawable(getDrawable(R.drawable.bg_gray200_round))
            binding.tvContinue.setTextColor(getColor(R.color.gray_300))
        }

        binding.btnCreate.setOnClickListener {
            startActivity(Intent(this, SignUpDoneActivity::class.java))
        }
    }
}