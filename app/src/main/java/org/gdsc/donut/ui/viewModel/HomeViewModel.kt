package org.gdsc.donut.ui.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.gdsc.donut.data.DonutSharedPreferences
import org.gdsc.donut.data.api.RetrofitBuilder
import org.gdsc.donut.data.remote.request.auth.RequestGoogleLogin
import org.gdsc.donut.data.remote.request.auth.RequestSignInReceiver
import org.gdsc.donut.data.remote.request.auth.RequestSignUpReceiver
import org.gdsc.donut.data.remote.response.auth.ResponseGoogleLogin
import org.gdsc.donut.data.remote.response.auth.ResponseSignInGiver
import org.gdsc.donut.data.remote.response.auth.ResponseSignInReceiver
import org.gdsc.donut.data.remote.response.auth.ResponseSignUpReceiver
import org.gdsc.donut.data.remote.response.home.ResponseHomeGiver
import org.gdsc.donut.data.remote.response.home.ResponseHomeReceiver
import org.gdsc.donut.data.remote.response.home.ResponseHomeReceiverBoxItem

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val _giverHomeInfo = MutableLiveData<ResponseHomeGiver>()
    val giverHomeInfo: LiveData<ResponseHomeGiver>
        get() = _giverHomeInfo

    private val _receiverHomeInfo = MutableLiveData<ResponseHomeReceiver>()
    val receiverHomeInfo: LiveData<ResponseHomeReceiver>
        get() = _receiverHomeInfo

    private val _receiverHomeBoxInfo = MutableLiveData<ResponseHomeReceiverBoxItem>()
    val receiverHomeBoxInfo: LiveData<ResponseHomeReceiverBoxItem>
        get() = _receiverHomeBoxInfo

    fun requestGiverHomeInfo(accessToken: String) = viewModelScope.launch(Dispatchers.IO) {
        _giverHomeInfo.postValue(
            RetrofitBuilder.homeService.getGiverHomeInfo("Bearer $accessToken")
        )
    }

    fun requestReceiverHomeInfo(accessToken: String) = viewModelScope.launch(Dispatchers.IO) {
        _receiverHomeInfo.postValue(
            RetrofitBuilder.homeService.getReceiverHomeInfo("Bearer $accessToken")
        )
    }

    fun requestReceiverHomeBoxInfo(accessToken: String, boxId: Long) = viewModelScope.launch(Dispatchers.IO) {
        _receiverHomeBoxInfo.postValue(
            RetrofitBuilder.homeService.getReceiverHomeBoxInfo("Bearer $accessToken", boxId)
        )
    }
}