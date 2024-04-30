package org.gdsc.donut.ui.receive

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.gdsc.donut.databinding.FragmentReceiveStoreStartBinding
import org.gdsc.donut.ui.ReceiverMainActivity

class ReceiveStoreStartFragment : Fragment(){
    private lateinit var binding: FragmentReceiveStoreStartBinding
    private val gmmIntentUri = Uri.parse("geo:0,0?q=convenience store")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReceiveStoreStartBinding.inflate(inflater, container, false)

        (activity as ReceiverMainActivity).disableFloatingButton()
        setFindButton()

        return binding.root
    }

    private fun setFindButton(){
        binding.btnFind.setOnClickListener {
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
            //startActivity(Intent(context, ReceiveMapsActivity::class.java))
            (activity as ReceiverMainActivity).changeFragment("receive_store")
        }
    }

}