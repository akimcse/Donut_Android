package org.gdsc.donut.ui.receive

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.gdsc.donut.databinding.FragmentReceiveStoreStartBinding
import org.gdsc.donut.ui.ReceiverMainActivity

class ReceiveStoreStartFragment : Fragment() {
    private lateinit var binding: FragmentReceiveStoreStartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReceiveStoreStartBinding.inflate(inflater, container, false)

        (activity as ReceiverMainActivity).enableFloatingButton()

        return binding.root
    }

}