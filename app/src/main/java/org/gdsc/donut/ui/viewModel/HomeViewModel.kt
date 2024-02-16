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

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val _giverHomeInfo = MutableLiveData<ResponseHomeGiver>()
    val giverHomeInfo: LiveData<ResponseHomeGiver>
        get() = _giverHomeInfo

    fun requestGiverSignIn(accessToken: String) = viewModelScope.launch(Dispatchers.IO) {
        _giverHomeInfo.postValue(
            RetrofitBuilder.homeService.getGiverHomeInfo("Bearer $accessToken")
        )
    }
}