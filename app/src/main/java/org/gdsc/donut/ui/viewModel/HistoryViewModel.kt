package org.gdsc.donut.ui.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.gdsc.donut.data.api.RetrofitBuilder
import org.gdsc.donut.data.remote.response.home.ResponseHistoryReceiver

class HistoryViewModel(application: Application) : AndroidViewModel(application) {
    private val _receiverHistoryInfo = MutableLiveData<ResponseHistoryReceiver>()
    val receiverHistoryInfo: LiveData<ResponseHistoryReceiver>
        get() = _receiverHistoryInfo

    fun requestReceiverHistoryInfo(accessToken: String) = viewModelScope.launch(Dispatchers.IO) {
        _receiverHistoryInfo.postValue(
            RetrofitBuilder.historyService.getReceiverHistoryInfo("Bearer $accessToken")
        )
    }
}