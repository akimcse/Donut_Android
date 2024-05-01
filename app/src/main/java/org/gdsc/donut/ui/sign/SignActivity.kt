package org.gdsc.donut.ui.sign

import android.Manifest
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import org.gdsc.donut.R
import org.gdsc.donut.data.DonutSharedPreferences
import org.gdsc.donut.databinding.ActivitySignBinding
import org.gdsc.donut.ui.GiverMainActivity
import org.gdsc.donut.ui.ReceiverMainActivity
import org.gdsc.donut.ui.viewModel.SignViewModel

class SignActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignBinding
    private val viewModel: SignViewModel by viewModels()
    private val googleSignInClient: GoogleSignInClient by lazy { getGoogleClient() }
    private val googleAuthLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            val account = task.getResult(ApiException::class.java)
            account.idToken?.let { viewModel.requestGiverSignIn(it) }
            // getGoogleAccessToken(task)
            setGiverUserInfo()
            askNotificationPermission()
        }
    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission(),) { isGranted: Boolean ->
        if (isGranted) setFCM()
        else Toast.makeText(baseContext, "Turn off push notification", Toast.LENGTH_SHORT).show()
    }

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
            if (DonutSharedPreferences.getUserRole() == "giver") {
                startActivity(Intent(this, GiverMainActivity::class.java))
                finish()
            } else if (DonutSharedPreferences.getUserRole() == "receiver") {
                startActivity(Intent(this, ReceiverMainActivity::class.java))
                finish()
            }
        }
    }

    private fun setGoogleLoginBtn() {
        binding.btnGoogle.setOnClickListener {
            val signInIntent = googleSignInClient.signInIntent
            googleAuthLauncher.launch(signInIntent)
        }
    }

    private fun getGoogleClient(): GoogleSignInClient {
        val googleSignInOption = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestServerAuthCode(getString(R.string.google_login_client_id), true)
            .requestEmail()
            .requestIdToken(getString(R.string.google_login_client_id))
            .build()
        return GoogleSignIn.getClient(this, googleSignInOption)
    }

/* 엔드포인트까지 직접 가서 access token 받아오는 코드
    private fun getGoogleAccessToken(completedTask: Task<GoogleSignInAccount>) {
        val account = completedTask.getResult(ApiException::class.java)
        val clientId = getString(R.string.google_login_client_id)
        val clientSecret = getString(R.string.gooogle_clinet_secret)
        val code = account.serverAuthCode
        val grantType = "authorization_code"
        val redirectUri = ""

        if (code != null) {
            viewModel.requestGoogleLogin(clientId, clientSecret, code, grantType, redirectUri)
            requestGiverSignIn()
        }
    }

    private fun requestGiverSignIn() {
        viewModel.googleLoginInfo.observe(this, Observer { data ->
            viewModel.requestGiverSignIn(data.id_token)
        })
    }
*/

    private fun setGiverUserInfo(){
        viewModel.giverSignInInfo.observe(this, Observer { data->
            DonutSharedPreferences.setAccessToken(data.data?.accesstoken)
            DonutSharedPreferences.setUserRole("giver")
            startActivity(Intent(this, GiverMainActivity::class.java))
        })
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
        askNotificationPermission()
    }

    private fun handleNetworkException() {
        viewModel.receiverSignInInfo.observe(this, Observer { data ->
            when (data.code) {
                201 -> {
                    viewModel.saveUserId(data.data?.name)
                    viewModel.saveAccessToken(data.data?.accesstoken)
                    DonutSharedPreferences.setUserRole("receiver")
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


    private fun askNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
                setFCM()
            } else {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    private fun setFCM(){
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(ContentValues.TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            val token = task.result

            Log.d(ContentValues.TAG, "FCMtoken: $token")
        })
    }
}