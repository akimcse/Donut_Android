package org.gdsc.donut.ui.sign

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.gdsc.donut.data.DonutSharedPreferences
import org.gdsc.donut.databinding.ActivitySignBinding
import org.gdsc.donut.ui.GiverMainActivity

class SignActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setContinueBtn()
    }

    private fun setContinueBtn(){
        binding.btnCreate.setOnClickListener {
            startActivity(Intent(this, SignUpConfirmActivity::class.java))
        }
    }
}