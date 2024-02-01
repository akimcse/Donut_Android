package org.gdsc.donut.ui.donation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.gdsc.donut.databinding.ActivityDonationDoneBinding

class DonationDoneActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDonationDoneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDonationDoneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //함수 호출
    }

}