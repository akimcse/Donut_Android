package org.gdsc.donut.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.gdsc.donut.databinding.FragmentWalletDetailBinding
import org.gdsc.donut.ui.ReceiverMainActivity

class WalletDetailFragment : Fragment() {
    private lateinit var binding: FragmentWalletDetailBinding
    val store = "GS25"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWalletDetailBinding.inflate(inflater, container, false)

        setButton()
        setGoogleMapIcon()

        return binding.root
    }

    private fun setButton() {
        binding.ivDots.setOnClickListener {
            binding.clReport.visibility = View.VISIBLE
        }
    }

    private fun setGoogleMapIcon() {
        val gmmIntentUri = Uri.parse("geo:0,0?q=${store}")
        binding.ivPin.setOnClickListener {
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        }
    }
}