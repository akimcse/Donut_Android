package org.gdsc.donut.ui.sign

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.gdsc.donut.R
import org.gdsc.donut.databinding.ActivitySignBinding
import org.gdsc.donut.databinding.ActivitySignUpConfirmBinding

class SignUpConfirmActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpConfirmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpConfirmBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }
}