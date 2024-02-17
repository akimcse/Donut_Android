package org.gdsc.donut.ui.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.gdsc.donut.data.api.RetrofitBuilder
import org.gdsc.donut.data.remote.request.donation.RequestAssignReceiver
import org.gdsc.donut.data.remote.response.donation.ResponseAssignReceiver
import org.gdsc.donut.data.remote.response.history.ResponseHistoryReceiver
import org.gdsc.donut.data.remote.response.ranking.ResponseNumberRanking
import org.gdsc.donut.data.remote.response.ranking.ResponsePriceRanking

class DonationViewModel(application: Application) : AndroidViewModel(application) {
    private val _assignReceiverInfo = MutableLiveData<ResponseAssignReceiver>()
    val assignReceiverInfo: LiveData<ResponseAssignReceiver>
        get() = _assignReceiverInfo

    val sharedStoreName = MutableLiveData<String>()
    fun setStoreName(input: String) {
        sharedStoreName.value = input
    }

    fun requestAssignReceiver(accessToken: String, price: Int) = viewModelScope.launch(Dispatchers.IO) {
        _assignReceiverInfo.postValue(
            sharedStoreName.value?.let { RequestAssignReceiver(it, price) }?.let {
                RetrofitBuilder.donationService.assignReceiver("Bearer $accessToken",
                    it
                )
            }
        )
    }
}