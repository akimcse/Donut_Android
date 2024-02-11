package org.gdsc.donut.ui.sign

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import org.gdsc.donut.databinding.ActivitySignUpDoneBinding
import org.gdsc.donut.ui.GiverMainActivity

class SignUpDoneActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpDoneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpDoneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        moveMain(1)
    }

    private fun moveMain(sec: Int) {
        Handler().postDelayed(Runnable {
            startActivity(Intent(this, GiverMainActivity::class.java))
            finish()
        }, 1000 * sec.toLong()) // sec초 정도 딜레이를 준 후 시작
    }
}