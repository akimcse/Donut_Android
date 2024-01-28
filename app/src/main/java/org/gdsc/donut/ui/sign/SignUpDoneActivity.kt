package org.gdsc.donut.ui.sign

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.gdsc.donut.databinding.ActivitySignUpDoneBinding

class SignUpDoneActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpDoneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpDoneBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }
}