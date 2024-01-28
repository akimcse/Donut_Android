package org.gdsc.donut.ui.sign

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.gdsc.donut.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }
}