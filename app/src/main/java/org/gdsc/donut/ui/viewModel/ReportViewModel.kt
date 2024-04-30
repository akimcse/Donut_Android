package org.gdsc.donut.ui.viewModel

import android.app.Application
import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
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
import org.gdsc.donut.data.remote.request.report.RequestReport
import org.gdsc.donut.data.remote.response.donation.ResponseAssignReceiver
import org.gdsc.donut.data.remote.response.donation.ResponseDirectDonation
import org.gdsc.donut.data.remote.response.donation.ResponseDonateGiver
import org.gdsc.donut.data.remote.response.report.ResponseReport
import org.gdsc.donut.data.remote.response.report.ResponseReportUnused
import org.gdsc.donut.util.Event
import kotlin.Exception

class ReportViewModel(application: Application) : AndroidViewModel(application) {
    private val _cheatedItemInfo = MutableLiveData<ResponseReport>()
    val cheatedItemInfo: LiveData<ResponseReport>
        get() = _cheatedItemInfo

    private val _unusedItemInfo = MutableLiveData<ResponseReportUnused>()
    val unusedItemInfo: LiveData<ResponseReportUnused>
        get() = _unusedItemInfo

    fun setCheatedItem(accessToken: String, giftId: Long) = viewModelScope.launch(Dispatchers.IO){
        _cheatedItemInfo.postValue((RetrofitBuilder.reportService.reportCheat("Bearer $accessToken", RequestReport(giftId))))
    }

    fun setUnusedItem(accessToken: String, giftId: Long) = viewModelScope.launch(Dispatchers.IO){
        _unusedItemInfo.postValue((RetrofitBuilder.reportService.reportUnused("Bearer $accessToken", giftId)))
    }
}