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
import org.gdsc.donut.data.remote.request.auth.RequestSignUpReceiver
import org.gdsc.donut.data.remote.response.auth.ResponseSignUpReceiver

class SignViewModel (application: Application) : AndroidViewModel(application) {
    private val _receiverSignUpInfo = MutableLiveData<ResponseSignUpReceiver>()
    val receiverSignUpInfo: LiveData<ResponseSignUpReceiver>
        get() = _receiverSignUpInfo

    fun saveUserName(token: String?){
        viewModelScope.launch(Dispatchers.IO) {
            DonutSharedPreferences.setUserId(token)
        }
    }

    fun requestSign(id:String, password:String) = viewModelScope.launch(Dispatchers.IO) {
        _receiverSignUpInfo.postValue(
            RetrofitBuilder.authService.signUpReceiver(
                RequestSignUpReceiver(id, password)
            )
        )
    }
}