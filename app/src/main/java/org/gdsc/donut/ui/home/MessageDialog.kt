package org.gdsc.donut.ui.home

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import org.gdsc.donut.databinding.DialogMessageBinding
import org.gdsc.donut.ui.viewModel.HomeViewModel

class MessageDialog(context: Context, messageDialogInterface: MessageDialogInterface, private val viewModel: HomeViewModel): Dialog(context) {
    private var mbinding: DialogMessageBinding? = null
    private val binding get() = mbinding!!

    private var messageDialogInterface: MessageDialogInterface? = null

    init {
        this.messageDialogInterface = messageDialogInterface
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mbinding = DialogMessageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setMsg()

        binding.btnSend.setOnClickListener {
            this.messageDialogInterface?.onSendMsgButtonClicked()
        }
    }

    private fun setMsg(){
        binding.clThanks.setOnClickListener {
            viewModel.setContent(binding.tvThanks.text.toString())
        }

        binding.clLifesaver.setOnClickListener {
            viewModel.setContent(binding.tvLifesaver.text.toString())
        }

        binding.clLove.setOnClickListener {
            viewModel.setContent(binding.tvLove.text.toString())
        }

        binding.clEtc.setOnClickListener {
            viewModel.setContent(binding.etEtc.text.toString())
        }
    }
}