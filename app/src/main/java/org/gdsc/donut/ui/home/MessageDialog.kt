package org.gdsc.donut.ui.home

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.core.content.ContextCompat
import org.gdsc.donut.R
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
            binding.clThanks.setBackgroundResource(R.drawable.bg_maincoral_round8)
            binding.tvThanks.setTextColor(ContextCompat.getColor(binding.root.context, R.color.main_coral))
            binding.clLifesaver.setBackgroundResource(R.drawable.bg_gray300_round8)
            binding.tvLifesaver.setTextColor(ContextCompat.getColor(binding.root.context, R.color.gray_300))
            binding.clLove.setBackgroundResource(R.drawable.bg_gray300_round8)
            binding.tvLove.setTextColor(ContextCompat.getColor(binding.root.context, R.color.gray_300))
            viewModel.setContent(binding.tvThanks.text.toString())
        }

        binding.clLifesaver.setOnClickListener {
            binding.clThanks.setBackgroundResource(R.drawable.bg_gray300_round8)
            binding.tvThanks.setTextColor(ContextCompat.getColor(binding.root.context, R.color.gray_300))
            binding.clLifesaver.setBackgroundResource(R.drawable.bg_maincoral_round8)
            binding.tvLifesaver.setTextColor(ContextCompat.getColor(binding.root.context, R.color.main_coral))
            binding.clLove.setBackgroundResource(R.drawable.bg_gray300_round8)
            binding.tvLove.setTextColor(ContextCompat.getColor(binding.root.context, R.color.gray_300))
            viewModel.setContent(binding.tvLifesaver.text.toString())
        }

        binding.clLove.setOnClickListener {
            binding.clThanks.setBackgroundResource(R.drawable.bg_gray300_round8)
            binding.tvThanks.setTextColor(ContextCompat.getColor(binding.root.context, R.color.gray_300))
            binding.clLifesaver.setBackgroundResource(R.drawable.bg_gray300_round8)
            binding.tvLifesaver.setTextColor(ContextCompat.getColor(binding.root.context, R.color.gray_300))
            binding.clLove.setBackgroundResource(R.drawable.bg_maincoral_round8)
            binding.tvLove.setTextColor(ContextCompat.getColor(binding.root.context, R.color.main_coral))
            viewModel.setContent(binding.tvLove.text.toString())
        }

        binding.clEtc.setOnClickListener {
            viewModel.setContent(binding.etEtc.text.toString())
        }
    }
}