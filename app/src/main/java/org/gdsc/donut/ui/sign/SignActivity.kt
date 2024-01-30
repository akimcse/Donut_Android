package org.gdsc.donut.ui.sign

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.gdsc.donut.databinding.ActivitySignBinding

class SignActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }
}