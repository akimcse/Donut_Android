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
        setGoogleLoginBtn()
        setContinueBtn()
        checkNameStatus()
        checkPasswordStatus()
    }

    private fun autoLogin() {
        DonutSharedPreferences.init(applicationContext)
        if (DonutSharedPreferences.getAccessToken()?.isNotEmpty() == true) {
            startActivity(Intent(this, ReceiverMainActivity::class.java))
            finish()
        }
    }

    private fun setGoogleLoginBtn(){
        binding.btnGoogle.setOnClickListener {

        }
    }

    private fun requestGiverSignIn(){
        val accessToken = DonutSharedPreferences.getAccessToken()
        if (accessToken != null) {
            viewModel.requestGiverSignIn(accessToken)
        }
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

    private fun setLoginBtn() {
        if (!binding.etUsername.text.isNullOrBlank() && !binding.etPassword.text.isNullOrBlank()) {
            binding.btnLogin.setBackgroundDrawable(getDrawable(R.drawable.bg_coral_round))
            binding.tvLogin.setTextColor(getColor(R.color.white))
            binding.btnLogin.setOnClickListener {
                requestReceiverSignIn()
            }
        } else {
            binding.btnLogin.setBackgroundDrawable(getDrawable(R.drawable.bg_gray200_round))
            binding.tvLogin.setTextColor(getColor(R.color.gray_300))
        }
    }

    private fun requestReceiverSignIn() {
        val id = binding.etUsername.text.toString()
        val password = binding.etPassword.text.toString()
        viewModel.requestReceiverSignIn(id, password)
        handleNetworkException()
    }

    private fun handleNetworkException() {
        viewModel.receiverSignInInfo.observe(this, Observer { data ->
            when (data.code) {
                201 -> {
                    viewModel.saveUserId(data.data?.name)
                    viewModel.saveAccessToken(data.data?.accesstoken)
                    startActivity(Intent(this, ReceiverMainActivity::class.java))
                    finish()
                }

                409 -> {
                    Toast.makeText(this, "아이디 혹은 패스워드를 확인해주세요.", Toast.LENGTH_SHORT).show()
                }

                else -> {
                    Toast.makeText(this, "서버 오류입니다. 잠시 후 다시 시도해주세요.", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}