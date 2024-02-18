package org.gdsc.donut.ui.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.gdsc.donut.data.api.RetrofitBuilder
import org.gdsc.donut.data.remote.request.donation.RequestAssignReceiver
import org.gdsc.donut.data.remote.response.donation.ResponseAssignReceiver
import org.gdsc.donut.data.remote.response.donation.ResponseDonateGiver

class DonationViewModel(application: Application) : AndroidViewModel(application) {
    private val _assignReceiverInfo = MutableLiveData<ResponseAssignReceiver>()
    val assignReceiverInfo: LiveData<ResponseAssignReceiver>
        get() = _assignReceiverInfo

    private val _donateGiverInfo = MutableLiveData<ResponseDonateGiver>()
    val donateGiverInfo: LiveData<ResponseDonateGiver>
        get() = _donateGiverInfo

    private val sharedStoreName = MutableLiveData<String>()
    fun setStoreName(input: String) {
        sharedStoreName.value = input
    }

    fun requestAssignReceiver(accessToken: String, price: Int) =
        viewModelScope.launch(Dispatchers.IO) {
            _assignReceiverInfo.postValue(
                sharedStoreName.value?.let { RequestAssignReceiver(it, price) }?.let {
                    RetrofitBuilder.donationService.assignReceiver("Bearer $accessToken", it)
                }
            )
        }

    fun requestDonateGiver(accessToken: String, giftImage: MultipartBody.Part?, product: RequestBody, price: Int, dueDate: RequestBody, store: RequestBody) =
        viewModelScope.launch(Dispatchers.IO) {
            _donateGiverInfo.postValue(
                RetrofitBuilder.donationService.donateGiver("Bearer $accessToken", giftImage, product, price, dueDate, store)
            )
        }
}